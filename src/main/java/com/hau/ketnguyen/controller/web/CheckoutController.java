package com.hau.ketnguyen.controller.web;

import java.util.List;

import com.hau.ketnguyen.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hau.ketnguyen.entity.UserEntity;
import com.hau.ketnguyen.service.ICustomerService;
import com.hau.ketnguyen.service.IOrderDetailService;
import com.hau.ketnguyen.service.IOrderService;
import com.hau.ketnguyen.service.IProductService;
import com.hau.ketnguyen.service.IShopingCartService;
import com.hau.ketnguyen.service.impl.PaypalPaymentIntent;
import com.hau.ketnguyen.service.impl.PaypalPaymentMethod;
import com.hau.ketnguyen.service.impl.PaypalServiceImpl;
import com.hau.ketnguyen.service.impl.UserServiceImpl;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@Controller
public class CheckoutController {
	@Autowired
	private PaypalServiceImpl paypalService;

	public static final String SUCCESS_URL = "checkout/success";
	public static final String CANCEL_URL = "checkout/cancel";

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private IOrderService orderService;

	@Autowired
	private IOrderDetailService orderDetailService;

	@Autowired
	private IShopingCartService cartService;

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private IProductService productService;

	@GetMapping("/checkout")
	public String showCheckOut(Model model) {
		UserEntity user = userService.getCurrentlyLoggedInUser();
		if (user == null) {
			return "You must login !";
		}

		int count = 0, qty = 0;
		List<CartItemDTO> cartList = cartService.listAll(user);
		for (CartItemDTO item : cartList) {
			count += item.getPrice() * item.getQuantity();
			qty += item.getQuantity();
		}
		model.addAttribute("customer", new CustomerDTO());
		model.addAttribute("cartSize", cartList.size());
		model.addAttribute("total", count);
		model.addAttribute("qty", qty);
		return "checkout/home";
	}

	@PostMapping("/checkout")
	public String processCheckOut(@ModelAttribute(value = "customer") CustomerDTO customer, Model model) {
		UserEntity user = userService.getCurrentlyLoggedInUser();
		OrderDTO orderDTO = new OrderDTO();
		if (user == null) {
			return "You must login !";
		}

		List<CartItemDTO> cartList = cartService.listAll(user);
		double total = 0;
		for (CartItemDTO item : cartList) {
			total += item.getPrice() * item.getQuantity();
		}

		if (customer.getPayment().equals("Direct")) {
			orderDTO = getOrderDTO(customer, user, orderDTO, cartList, (float) total);
		}

		if (customer.getPayment().equals("Paypal")) {
			try {
				Payment payment = paypalService.createPayment(total, "USD", PaypalPaymentMethod.paypal,
						PaypalPaymentIntent.sale, customer.getInfo(), "http://localhost:8080/" + CANCEL_URL,
						"http://localhost:8080/" + SUCCESS_URL);
				for (Links link : payment.getLinks()) {
					if (link.getRel().equals("approval_url")) {
						orderDTO = getOrderDTO(customer, user, orderDTO, cartList, (float) total);
						return "redirect:" + link.getHref();
					}
				}

			} catch (PayPalRESTException e) {

				e.printStackTrace();
			}
		}

		return "redirect:/checkout?success";
	}

	private OrderDTO getOrderDTO(@ModelAttribute("customer") CustomerDTO customer, UserEntity user,
								 OrderDTO orderDTO, List<CartItemDTO> cartList, float total) {
		CustomerDTO cus = customerService.create(customer);
		orderDTO.setAmount(total);
		orderDTO = orderService.create(orderDTO, user, cus);
		for (CartItemDTO item : cartList) {
			OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
			orderDetailDTO.setPrice(item.getPrice());
			orderDetailDTO.setQuantity(item.getQuantity());
			orderDetailService.create(orderDetailDTO, productService.findById(item.getProductId()), orderDTO);
			cartService.removeCart(item, user);
			ProductDTO productDTO = productService.findById(item.getProductId());
			productDTO.setQuantity(productDTO.getQuantity() - item.getQuantity());
			productService.saveOrUpdate(productDTO);
		}
		return orderDTO;
	}

	@GetMapping(value = CANCEL_URL)
	public String cancelPay() {
		return "redirect:/checkout?failure";
	}

	@GetMapping(value = SUCCESS_URL)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			// System.out.println(payment.toJSON());
			if (payment.getState().equals("approved")) {
				return "redirect:/checkout?success";
			}
		} catch (PayPalRESTException e) {
			System.out.println(e.getMessage());
		}
		return "redirect:/";
	}
}

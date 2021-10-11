package com.hau.ketnguyen.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hau.ketnguyen.converter.CartItemConverter;
import com.hau.ketnguyen.dto.CartItemDTO;
import com.hau.ketnguyen.entity.CartItemEntity;
import com.hau.ketnguyen.entity.ProductEntity;
import com.hau.ketnguyen.entity.UserEntity;
import com.hau.ketnguyen.repository.ICartItemRepository;
import com.hau.ketnguyen.repository.IProductRepository;
import com.hau.ketnguyen.service.IShopingCartService;

@Service
public class ShoppingCartServiceImpl implements IShopingCartService {
	@Autowired
	private ICartItemRepository cartRepository;

	@Autowired
	private IProductRepository productRepository;
	
	@Autowired
	private CartItemConverter cartConverter;

	@Override
	public Page<CartItemDTO> listCartItems(int page,UserEntity userEntity) {
		Pageable pageable = PageRequest.of(page - 1, 2);
		Page<CartItemEntity> cartEntity = cartRepository.findByUser(userEntity,pageable);
		Page<CartItemDTO> cartDto = cartEntity.map(new Function<CartItemEntity, CartItemDTO>() {
			@Override
			public CartItemDTO apply(CartItemEntity entity) {
				return cartConverter.toDto(entity);
			}
		});
		return cartDto;
	}

	@Override
	public int addProductToCart(long productId, int qty, UserEntity userEntity) {
		int addQty = qty;
		ProductEntity productEntity = productRepository.findById(productId).get();
		CartItemEntity cartItemEntity = cartRepository.findByProductAndUser(productEntity, userEntity);
		if (cartItemEntity != null) {
			addQty = cartItemEntity.getQuantity() + qty;
			cartItemEntity.setQuantity(addQty);
		} else {
			cartItemEntity = new CartItemEntity();
			cartItemEntity.setQuantity(qty);
			cartItemEntity.setProduct(productEntity);
			cartItemEntity.setUser(userEntity);
		}

		cartRepository.save(cartItemEntity);

		return addQty;
	}

	@Override
	@Transactional
	public float updateQuantity(int quantity, long productId, long userId) {
		cartRepository.updateQuantity(quantity, productId, userId);
		ProductEntity product = productRepository.findById(productId).get();
		float total = product.getPrice() * quantity;
		return total;
	}

	@Override
	@Transactional
	public void removeProductFromCart(long userId, long productId) {
		cartRepository.deleteByUserAndProduct(userId, productId);
	}

	@Override
	public List<CartItemDTO> listAll(UserEntity userEntity) {
		List<CartItemDTO> dto = new ArrayList<CartItemDTO>();
		List<CartItemEntity> entity = cartRepository.findAllByUser(userEntity);
		for(CartItemEntity item : entity) {
			dto.add(cartConverter.toDto(item));
		}
		return dto;
	}

	@Override
	@Transactional
	public void removeCart(CartItemDTO cartItemDTO,UserEntity userEntity) {
		cartRepository.deleteAllByUser(userEntity.getId(), cartItemDTO.getId());
	}

}

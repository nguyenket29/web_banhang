package com.hau.ketnguyen.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

	@Override
	public Page<CartItemEntity> listCartItems(int page,UserEntity userEntity) {
		Pageable pageable = PageRequest.of(page - 1, 2);
		return cartRepository.findByUser(userEntity,pageable);
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
	public List<CartItemEntity> listAll(UserEntity entity) {
		return cartRepository.findAllByUser(entity);
	}

	@Override
	@Transactional
	public void removeCart(CartItemEntity cartItemEntity,UserEntity userEntity) {
		cartRepository.deleteAllByUser(userEntity.getId(), cartItemEntity.getId());
	}

}

package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.repository.OrderItemReposiroty;
import com.example.repository.OrderRepository;
import com.example.repository.OrderToppingRepository;

@Service 
@Transactional
public class ShoppingCartService {
	
	@Autowired
	private OrderItemReposiroty orderItemRepository;
	@Autowired
	private OrderRepository orderRepository;

	
	/**
	 * ショッピングカートの中身を表示させる.
	 * 
	 * @param userId
	 * @param status
	 * @return order 注文情報
	 */
	/**
	 * @param orderItemId
	 */
	public Order findByUserIdAndStatus(Integer userId, Integer status) {
		Order order = null;
		return order = orderRepository.findByUserIdAndStatus(userId, status);
	}
		
		
	/**
	 * 注文商品を削除する.
	 * 
	 * @param orderItemId
	 */
	public void deleteByOrderItemId(Integer orderItemId) {
		orderItemRepository.deleteById(orderItemId);
		
		
	}
		
		
		
	}



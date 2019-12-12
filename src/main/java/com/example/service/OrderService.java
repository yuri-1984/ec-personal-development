package com.example.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.form.OrderItemForm;
import com.example.repository.OrderItemReposiroty;
import com.example.repository.OrderRepository;
import com.example.repository.OrderToppingRepository;


/**
 * 注文関連情報の処理を行うサービスクラス.
 * 
 * @author hiraokayuri
 */
@Service
@Transactional
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemReposiroty orderItemRepository;
	@Autowired
	private OrderToppingRepository orderToppingRepository;

	/**
	 * 商品をショッピングカートに追加する.
	 * @param userId 
	 * @param orderItemForm
	 * @param model
	 */
	public void addOrder(Integer userId, OrderItemForm orderItemForm) {
		Integer status = 0;
		Integer totalPrice = 0;
		Order order = orderRepository.findByUserIdAndStatus(userId, status);
		if (order == null) {
			order = new Order();
			order.setUserId(userId);
			order.setStatus(status);
			order.setTotalPrice(totalPrice);
			orderRepository.insert(order);
		}
		OrderItem orderItem = new OrderItem();
		BeanUtils.copyProperties(orderItemForm, orderItem);
		orderItem.setOrderId(order.getId());
		orderItem = orderItemRepository.insert(orderItem);
		
		if (orderItemForm.getToppingList()!= null) {
			OrderTopping orderTopping = new OrderTopping();
			for (int toppingId : orderItemForm.getToppingList()) {
				orderTopping.setOrderItemId(orderItem.getId());
				orderTopping.setToppingId(toppingId);
				orderToppingRepository.insert(orderTopping);

			}
		}
		
		

	}
	
	
	

}

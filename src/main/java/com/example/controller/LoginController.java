package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.repository.OrderItemReposiroty;
import com.example.repository.OrderRepository;
import com.example.service.LoginService;
import com.example.service.ShoppingCartService;

@Controller
@RequestMapping("")
public class LoginController {
	@Autowired
	private LoginService service;

	@Autowired
	private HttpSession session;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private OrderItemReposiroty orderItemRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@RequestMapping("/loginAfterSuccess")

	public String login(@AuthenticationPrincipal LoginUser loginUser, Model model) {
if (session.getAttribute("userId") != null) {
			
			int status = 0;
			
			Integer sessionUserId = (Integer) session.getAttribute("userId");
			
			//②ログインユーザーのorderを取得(orderは1つあるあるいは、無い。)
			Order order = orderRepository.findByUserIdAndStatus(loginUser.getUser().getId(),status);
			
			Integer loginUserOrderId;
			
			
			if ( order == null) {
			
				//ログインユーザーのorderがなければ下記を実行
				//仮ユーザーのorderのuser_idをログインユーザーのuser_idに更新
				Integer loginUserId = loginUser.getUser().getId();
			
				orderRepository.loginUpdate(loginUserId, sessionUserId);
			} else {
				//ログインユーザーのorderがあれば、下記を実行
				loginUserOrderId = order.getId();
				//①仮ユーザーのorder_idを取得
				Order preOrder = orderRepository.findByUserIdAndStatus(sessionUserId,status);
				if(preOrder == null ) {
					return "forward:/showCartList";
				}
				Order temporalOrder = order;
				Integer temporalOrderId = temporalOrder.getId();
				//③order_itemsの仮ユーザーのorder_idをログインユーザーのorder_idに更新
				orderItemRepository.updateLogin(temporalOrderId, loginUserOrderId);
				//④仮ユーザーのorderを削除
				orderRepository.deleteById(temporalOrderId);
			}
		} 
			return "forward:/";
	}

}

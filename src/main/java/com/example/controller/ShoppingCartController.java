package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.form.OrderItemForm;
import com.example.service.OrderService;
import com.example.service.ShoppingCartService;

/**
 * ショッピングカート関連を処理するコントローラークラス.
 * 
 * @author hiraokayuri
 *
 */
@Controller
@RequestMapping("")
public class ShoppingCartController {
	@Autowired
	private HttpSession session;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ShoppingCartService shoppingCartService;

	/**
	 * カートを追加するのボタンを押したら注文情報が追加されるメソッド.
	 * 
	 * @param orderItemform
	 * @param orderform
	 * @return
	 */
	@RequestMapping("/insertOrderItem")
	public String insertOrderItem(OrderItemForm orderItemform, @AuthenticationPrincipal LoginUser loginUser) {
		Integer userId;
		if (loginUser != null) {
			userId = loginUser.getUser().getId();
		} else if (session.getAttribute("userId") != null) {
			userId = (Integer) (session.getAttribute("userId"));
		} else {
			userId = (Integer) session.getId().hashCode();
			session.setAttribute("userId", userId);
		}

		orderService.addOrder(userId, orderItemform);
		return "redirect:/showCartList";
	}

	/**
	 * ショッピングカートの中身を表示させるメソッド.
	 * 
	 * @param orderItemForm
	 * @param model
	 * @return order(中身がなければメッセージを表示させる.)
	 */
	@RequestMapping("/showCartList")
	public String showOrder(@AuthenticationPrincipal LoginUser loginUser, Model model) {
		Integer userId;
		if (loginUser != null) {
			userId = loginUser.getUser().getId();
		} else if (session.getAttribute("userId") != null) {
			userId = (Integer) (session.getAttribute("userId"));
		} else {
			userId = (Integer) session.getId().hashCode();
		}
		session.setAttribute("userId", userId);
		Order order = shoppingCartService.findByUserIdAndStatus(userId, 0);
		if (order == null || order.getOrderItemList().size() == 0) {
			model.addAttribute("message", "カートに何も入っていません");

		} else {

			model.addAttribute("order", order);
		}

		return "cart_list";
	}

	@RequestMapping("/deleteOrder")
	public String deleteOrderItem(Integer id) {
		shoppingCartService.deleteByOrderItemId(id);

		return "redirect:/showCartList";

	}

}

package com.example.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Credit;
import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.form.OrderForm;
import com.example.repository.OrderRepository;
import com.example.service.OrderService;
import com.example.service.ShoppingCartService;

/**
 * 注文内容を操作するコントローラークラス.
 * 
 * @author hiraokayuri
 *
 */
@Controller
public class OrderController {
	@Autowired
	private ShoppingCartService service;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderRepository repository;

	@ModelAttribute
	public OrderForm setUpOrderForm() {
		return new OrderForm();
	}

	/**
	 * 注文内容を表示させる.
	 * 
	 * @param loginUser
	 * @param model
	 * @return 注文確認画面.
	 */
	@RequestMapping("/orderConfirm")
	public String showOrder(@AuthenticationPrincipal LoginUser loginUser, Model model) {
		Order order = service.findByUserIdAndStatus(loginUser.getUser().getId(), 0);
		model.addAttribute("order", order);
		return "order_confirm";
	}

	/**
	 * 注文する.
	 * 
	 * @param form
	 * @param loginUser
	 * @param model
	 * @return
	 */
	@RequestMapping("/order")
	public String oder(OrderForm form,Model model, @AuthenticationPrincipal LoginUser loginUser) {
		Order order = new Order();
		BeanUtils.copyProperties(form, order);
		Order newOrder = service.findByUserIdAndStatus(loginUser.getUser().getId(), 0);
		order.setTotalPrice(newOrder.getTotalPrice() + newOrder.getTax());

		String deliveryDateTime = form.getDeliveryDate() + " " + form.getDeliveryTime() + ":00:00";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date parsedDate = null;
		try {
			parsedDate = format.parse(deliveryDateTime);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		order.setDeliveryTime(timestamp);
		if ("1".equals(form.getPaymentMethod())) {
			order.setStatus(1);
		} else {
			order.setPaymentMethod(2);
			Credit credit = new Credit();
			credit.setCardNumber(form.getCardNumber());
			credit.setCardExpYear(form.getCardExpYear());
			credit.setCardExpMonth(form.getCardExpMonth());
			credit.setCardName(form.getCardName());
			credit.setCardCvv(form.getCardCvv());

		}
		order.setUserId(loginUser.getUser().getId());
		order.setId(newOrder.getId());
		repository.update(order);

		return "redirect:/finished";
	}

	/**
	 * 注文完了画面に遷移するメソッド.
	 * 
	 * @return
	 */
	@RequestMapping("/finished")
	public String finished() {
		return "order_finished.html";
	}

}

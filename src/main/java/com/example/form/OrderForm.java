package com.example.form;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.example.domain.OrderItem;
import com.example.domain.User;

/**
 * order_confirm.htmlからリクエストパラメーターを受け取るフォームクラス.
 * 
 * @author hiraokayuri
 *
 */
public class OrderForm {

	/* ID */
	private String id;
	/* UserId */
	private Integer userId;
	/* 状態 */
	private String status;
	/* 合計金額 */
	private String totalPrice;
	/* 注文日 */
	private  String orderDate;
	/* 宛先氏名 */
	private String destinationName;
	/* 宛先Eメール */
	private String destinationEmail;
	/* 宛先郵便番号 */
	private String destinationZipcode;
	/* 宛先住所 */
	private String destinationAddress;
	/* 宛先TEL */
	private String destinationTel;
	/* 配達希望日 */
	private String deliveryDate;

	/* 配達時間 */
	private String deliveryTime;

	/* 支払方法 */
	private String paymentMethod;
	/* ユーザ */
	private User user;
	/* 注文したアイテム */
	private List<OrderItem> orderItemList;
	/** クレジットカード番号 */
	private String cardNumber;
	/** 有効期限年 */
	private String cardExpYear;
	/** 有効期限月 */
	private String cardExpMonth;
	/** カード名義 */
	private String cardName;
	/** セキュリティコード */
	private String cardCvv;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getDestinationName() {
		return destinationName;
	}
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	public String getDestinationEmail() {
		return destinationEmail;
	}
	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}
	public String getDestinationZipcode() {
		return destinationZipcode;
	}
	public void setDestinationZipcode(String destinationZipcode) {
		this.destinationZipcode = destinationZipcode;
	}
	public String getDestinationAddress() {
		return destinationAddress;
	}
	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}
	public String getDestinationTel() {
		return destinationTel;
	}
	public void setDestinationTel(String destinationTel) {
		this.destinationTel = destinationTel;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardExpYear() {
		return cardExpYear;
	}
	public void setCardExpYear(String cardExpYear) {
		this.cardExpYear = cardExpYear;
	}
	public String getCardExpMonth() {
		return cardExpMonth;
	}
	public void setCardExpMonth(String cardExpMonth) {
		this.cardExpMonth = cardExpMonth;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardCvv() {
		return cardCvv;
	}
	public void setCardCvv(String cardCvv) {
		this.cardCvv = cardCvv;
	}
	@Override
	public String toString() {
		return "OrderForm [id=" + id + ", userId=" + userId + ", status=" + status + ", totalPrice=" + totalPrice
				+ ", orderDate=" + orderDate + ", destinationName=" + destinationName + ", destinationEmail="
				+ destinationEmail + ", destinationZipcode=" + destinationZipcode + ", destinationAddress="
				+ destinationAddress + ", destinationTel=" + destinationTel + ", deliveryDate=" + deliveryDate
				+ ", deliveryTime=" + deliveryTime + ", paymentMethod=" + paymentMethod + ", user=" + user
				+ ", orderItemList=" + orderItemList + ", cardNumber=" + cardNumber + ", cardExpYear=" + cardExpYear
				+ ", cardExpMonth=" + cardExpMonth + ", cardName=" + cardName + ", cardCvv=" + cardCvv + "]";
	}

	}
package com.example.form;


import java.util.List;

import com.example.domain.Item;

/**
 * Item_deteil.htmlからリクエストパラメーターを受け取るフォームクラス.
 * 
 * @author hiraokayuri
 *
 */
public class OrderItemForm {
	private Integer itemId;
	private  Character size;
	private Integer quantity;
	private Integer  orderId;
	private Item item;
	private List<Integer> toppingList;
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Character getSize() {
		return size;
	}
	public void setSize(Character size) {
		this.size = size;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public List<Integer> getToppingList() {
		return toppingList;
	}
	public void setToppingList(List<Integer> toppingList) {
		this.toppingList = toppingList;
	}
	@Override
	public String toString() {
		return "OrderItemForm [itemId=" + itemId + ", size=" + size + ", quantity=" + quantity + ", orderId=" + orderId
				+ ", item=" + item + ", toppingList=" + toppingList + "]";
	}
	
	

}
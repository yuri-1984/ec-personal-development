package com.example.domain;

import java.util.List;

/**
 * 注文商品情報を表すドメインクラス.
 * 
 * @author hiraokayuri
 *
 */
public class OrderItem {
	/** ID */
	private Integer id;
	/** 商品ID */
	private Integer itemId;
	/** 注文ID */
	private Integer orderId;
	/** 数量 */
	private Integer quantity;
	/** サイズ */
	private Character size;
	/** 商品 */
	private Item item;
	/** 注文トッピングリスト */
	private List<OrderTopping> orderToppingList;

	/**
	 * サイズごとに小計を出す.
	 * 
	 * @return 小計
	 */
	public int getSubTotal() {
		Integer subTotal = 0;
		Integer totalPriceTopping = 0;
		if (size.equals('M')) {
			if (orderToppingList != null) {
				for (OrderTopping orderTopping : orderToppingList) {
					totalPriceTopping += orderTopping.getTopping().getPriceM();

				}
			}
			if (item != null) {
				subTotal = item.getPriceM() + totalPriceTopping * quantity;
			}

		} else {
			if (size.equals('L')) {
				if (orderToppingList != null) {
					for (OrderTopping orderTopping : orderToppingList) {
						totalPriceTopping += orderTopping.getTopping().getPriceL();

					}
				}
				if (item != null) {
					subTotal = item.getPriceL() + totalPriceTopping * quantity;
				}
			}
		}

		return subTotal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Character getSize() {
		return size;
	}

	public void setSize(Character size) {
		this.size = size;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<OrderTopping> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<OrderTopping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", item=" + item + ", orderToppingList=" + orderToppingList + "]";
	}
}

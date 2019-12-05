package com.example.domain;

/**
 * 注文トッピング情報を表すドメインクラス. 
 * 
 * @author hiraokayuri
 *
 */
public class OrderTopping {
	
	/** ID */
	private Integer toppingId;
	/** トッピングID */
	private Integer id;
	/**　注文商品ID */
	private Integer orderItemId;
	/** トッピング */
	private Topping topping;
	
	public Integer getToppingId() {
		return toppingId;
	}
	public void setToppingId(Integer toppingId) {
		this.toppingId = toppingId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}
	public Topping getTopping() {
		return topping;
	}
	public void setTopping(Topping topping) {
		this.topping = topping;
	}
	@Override
	public String toString() {
		return "OrderTopping [toppingId=" + toppingId + ", id=" + id + ", orderItemId=" + orderItemId + ", topping="
				+ topping + "]";
	}
	
	
	
	

}

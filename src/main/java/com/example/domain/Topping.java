package com.example.domain;

/**
 * トッピング情報を表すドメイン.
 * 
 * @author hiraokayuri
 *
 */

public class Topping {
	
	/**  ID	 */
	private Integer id;
	/**  トッピング名	 */
	private String name;
	/**  Mサイズの料金	 */
	private Integer priceM;
	/**  Lサイズの料金	 */
	private Integer priceL;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPriceM() {
		return priceM;
	}
	public void setPriceM(Integer priceM) {
		this.priceM = priceM;
	}
	public Integer getPriceL() {
		return priceL;
	}
	public void setPriceL(Integer priceL) {
		this.priceL = priceL;
	}
	@Override
	public String toString() {
		return "Topping [id=" + id + ", name=" + name + ", priceM=" + priceM + ", priceL=" + priceL + "]";
	}
	
	
	

}

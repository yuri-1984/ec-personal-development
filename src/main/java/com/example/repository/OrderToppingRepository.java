package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderTopping;

/**
 * OrderToppingsテーブルを操作するリポジトリ.
 * 
 * @author hiraokayuri
 */
@Repository
public class OrderToppingRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * オーダートッピング情報を登録する.
	 * 
	 * @param orderTopping
	 */
	public void insert(OrderTopping orderTopping) {
		String sql = "INSERT INTO order_toppings(topping_id,order_item_id)VALUES(:toppingId, :orderItemId)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("toppingId",orderTopping.getToppingId())
				.addValue("orderItemId",orderTopping.getOrderItemId());
		template.update(sql, param); 
		
	}
	
	
	
	
	
	

}

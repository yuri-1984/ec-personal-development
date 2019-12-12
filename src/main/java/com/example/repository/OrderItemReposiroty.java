package com.example.repository;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderItem;

/**
 * order_itemsテーブルの情報を操作するリポジトリクラス.
 * 
 * @author hiraokayuri
 *
 */
@Repository
public class OrderItemReposiroty {
	@Autowired
	private NamedParameterJdbcTemplate template;

	private SimpleJdbcInsert insert;

	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("order_items");
		insert = withTableName.usingGeneratedKeyColumns("id");

	}

	    /**
	     * 注文商品情報を登録する.
	     * @param orderitem
	     * @return orderitem と　主キー
	     */
	public OrderItem insert(OrderItem orderitem) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderitem);
		Number key = insert.executeAndReturnKey(param);
		orderitem.setId(key.intValue());

		return orderitem;

	}

	
	    /**
	     * 注文商品情報を削除する.
	     * @param id
	     */
	public void deleteById(Integer id) {
		String deleteSql = "WITH deleted AS(DELETE FROM order_items WHERE id =:id RETURNING id) DELETE FROM order_toppings WHERE order_item_id IN (SELECT id FROM deleted)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(deleteSql, param);

	}

	/**
	 * ログイン後にupdate処理を行います. 
	 * 仮IDでカートに入れた後に新規登録して、ログイン後にカートに商品を保持するよう処理。
	 * 
	 * @param loginId
	 * @param sessionId
	 */
	public void updateLogin(int loginId, int sessionId) {
		String sql = "UPDATE order_items set order_id = :orderId where order_id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", loginId).addValue("id", sessionId);
		template.update(sql, param);
	}

}

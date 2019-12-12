package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.Topping;

/**
 * ordersテーブル操作するリポジトリクラス.
 * 
 * @author hiraokayuri
 *
 */
@Repository
public class OrderRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private SimpleJdbcInsert insert;

	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("orders");
		insert = withTableName.usingGeneratedKeyColumns("id");

	}

	private static final ResultSetExtractor<List<Order>> ORDER_EXTRACTOR = (rs) -> {
		List<Order> orderList = new ArrayList<>();
		int preId = 0;
		int preOrderItemId = 0;
		Order order = null;
		List<OrderItem> orderItemList = null;
		List<OrderTopping> orderToppingList = null;
		Item item = null;
		Topping topping = null;
		while (rs.next()) {
			if (rs.getInt("o_id") != preId) {
				order = new Order();
				order.setId(rs.getInt("o_id"));
				order.setStatus(rs.getInt("o_status"));
				order.setUserId(rs.getInt("o_user_id"));
				order.setTotalPrice(rs.getInt("o_total_price"));
				order.setOrderDate(rs.getDate("o_order_date"));
				order.setDestinationName(rs.getString("o_destination_name"));
				order.setDestinationEmail(rs.getString("o_destination_email"));
				order.setDestinationZipcode(rs.getString("o_destination_zipcode"));
				order.setDestinationAddress(rs.getString("o_destination_address"));
				order.setDestinationTel(rs.getString("o_destination_tel"));
				order.setDeliveryTime(rs.getTimestamp("o_delivery_time"));
				order.setPaymentMethod(rs.getInt("o_payment_method"));
				orderItemList = new ArrayList<>();
				order.setOrderItemList(orderItemList);
				orderList.add(order);
				preId = rs.getInt("o_id");
			}

//// 最後preIdの値をオーダーIDで書き換えて次新しく作った自動生成の値と比較する。
// preid = 1 (o.id) = new o.id =2;のためオーダーが作られるとpreIDと値が同じにはならないため格納されていく。
			if (rs.getInt("oi_id") != 0 && rs.getInt("oi_id") != preOrderItemId) {

				OrderItem orderItem = new OrderItem();
				orderItem.setId(rs.getInt("oi_id"));
				orderItem.setItemId(rs.getInt("oi_item_id"));
				orderItem.setOrderId(rs.getInt("oi_order_id"));
				orderItem.setQuantity(rs.getInt("oi_quantity"));
				char[] str = (rs.getString("oi_size").toCharArray());
				orderItem.setSize(str[0]);
				orderToppingList = new ArrayList<>();
				orderItem.setOrderToppingList(orderToppingList);
				orderItemList.add(orderItem);
				

				item = new Item();
				item.setId(rs.getInt("i_id"));
				item.setName(rs.getString("i_name"));
				item.setDescription(rs.getString("i_description"));
				item.setPriceM(rs.getInt("i_price_m"));
				item.setPriceL(rs.getInt("i_price_l"));
				item.setImagePath(rs.getString("i_image_path"));
				orderItem.setItem(item);
		

				preOrderItemId = orderItem.getId();

			}
			if (rs.getInt("ot_id") != 0) {
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setId(rs.getInt("ot_id"));
				orderTopping.setOrderItemId(rs.getInt("ot_order_item_id"));
				orderTopping.setToppingId(rs.getInt("ot_topping_id"));
				orderToppingList.add(orderTopping);

				topping = new Topping();
				topping.setId(rs.getInt("t_id"));
				topping.setName(rs.getString("t_name"));
				topping.setPriceM(rs.getInt("t_price_m"));
				topping.setPriceL(rs.getInt("t_price_l"));
				orderTopping.setTopping(topping);


			}
			preId = order.getId();

		}
		return orderList;

	};
	

	/**
	 * ショッピングカート中身を全権検索するSQL.
	 * 
	 * @param userId
	 * @param status
	 * @return order
	 */
	public Order findByUserIdAndStatus(Integer userId, Integer status) {
		String sql = "SELECT o.id o_id,o.status o_status ,o.user_id o_user_id,o.total_price o_total_price,o.order_date o_order_date,o.destination_name o_destination_name,o.destination_email o_destination_email,o.destination_zipcode o_destination_zipcode,o.destination_address o_destination_address,o.destination_tel o_destination_tel,o.delivery_time o_delivery_time,o.payment_method o_payment_method,"
				+ " oi.id oi_id,oi.item_id oi_item_id,oi.order_id oi_order_id,oi.quantity oi_quantity,oi.size oi_size,"
				+ " i.id i_id,i.name i_name,i.description i_description,i.price_m i_price_m,i.price_l i_price_l,i.image_path i_image_path,"
				+ " ot.id ot_id,ot.order_item_id ot_order_item_id ,ot.topping_id ot_topping_id,"
				+ " t.id t_id,t.name t_name, t.price_m t_price_m,t.price_l t_price_l "
				+ " FROM orders o LEFT JOIN order_items oi ON o.id = oi.order_id  "
				+ " LEFT JOIN order_toppings ot ON oi.id = ot.order_item_id"
				+ " LEFT JOIN items i ON i.id = oi.item_id "
				+ " LEFT JOIN toppings t ON t.id = ot.topping_id WHERE o.user_id =:userId AND o.status =:status ORDER BY o.id DESC";
		  SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);
		  
		List<Order> orderList = template.query(sql, param, ORDER_EXTRACTOR);
		// orderListの中身が空だったらnullを返す。そうじゃなかったらオーダーリストの中身を一件返す。
		if (orderList.size() == 0) {
			return null;
		}
		return orderList.get(0);

	}

	 /**
     * 注文商品情報を登録する.
     * @param order
     * @return order と　主キー
     */
    public Order insert(Order order) {
	SqlParameterSource param = new BeanPropertySqlParameterSource(order);
	Number key = insert.executeAndReturnKey(param);
	order.setId(key.intValue());

	return order;

}
    /**
     * 注文情報を更新する.
     * 
     * @param order
     */
    public void update(Order order) {
		String sql = "update orders set status=:status,total_price =:totalPrice,order_date =:orderDate,destination_name =:destinationName,destination_email =:destinationEmail,destination_zipcode =:destinationZipcode,destination_address =:destinationAddress,destination_tel =:destinationTel,delivery_time =:deliveryTime,payment_method =:paymentMethod where id=:id";
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		template.update(sql, param);
	}

    

	// ログイン用
	public void loginUpdate(int loginId, int sessionId) {
		String sql = "update orders set user_id = :userId where user_id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", loginId).addValue("id", sessionId);
		template.update(sql, param);
	}

	/**
	 * 注文情報に入っているorderのsessionIdを更新する.
	 * 
	 * @param userId
	 */
	public void orderDeleteBysessionId(Integer userId) {
		String sql = "DELETE FROM orders WHERE o_user_id=:userId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		template.update(sql, param);

	}

	/**
	 *オーダーの中身を削除する.
	 *
	 * @param id
	 */
	public void deleteById(Integer id) {
		String deleteSql = "WITH deleted AS(DELETE FROM order_items WHERE id =:id RETURNING id) DELETE FROM order_toppings WHERE order_item_id IN (SELECT id FROM deleted)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(deleteSql, param);

	}


	

}

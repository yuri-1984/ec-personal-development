package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Topping;

/**
 * toppingsテーブルを操作するリポジトリクラス.
 * @author hiraokayuri
 *
 */
@Repository
public class ToppingRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Topping> TOPPING_ROWMAPPER=(rs,i)->{
		Topping topping = new Topping();
		topping.setId(rs.getInt("id"));
		topping.setName(rs.getString("name"));
		topping.setPriceL(rs.getInt("price_m"));
		topping.setPriceM(rs.getInt("price_l"));
		return topping;
	};
	
	/**
	 * トッピングデータを全権検索.
	 * 
	 * @return　toppingList
	 */
	public List<Topping> findAll(){
		String sql ="select id ,name,price_m,price_l from toppings";
		List<Topping> toppingList = template.query(sql, TOPPING_ROWMAPPER);
		return toppingList;
		
	}
	
	/**
	 * トッピングデータを一件検索.
	 * 
	 * @param id
	 * @return topping 
	 */
	public Topping load (Integer id) {
		String sql = "select id,name,price_m,price_l from toppings where id =:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Topping topping = template.queryForObject(sql, param,TOPPING_ROWMAPPER);
		return topping;
	}

}

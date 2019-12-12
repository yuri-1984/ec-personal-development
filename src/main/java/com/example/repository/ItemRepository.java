package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;

/**
 * itemsテーブルを操作するリポジトリ.
 * 
 * @author hiraokayuri
 *
 */
@Repository
public class ItemRepository {

	public static final RowMapper<Item> ITEM_ROWMAPPER = (rs, i) -> {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setPriceL(rs.getInt("price_l"));
		item.setPriceM(rs.getInt("price_m"));
		item.setImagePath(rs.getString("image_path"));
		item.setDescription(rs.getString("description"));
		return item;

	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 商品情報を全権検索する.
	 * 
	 * @return itemList 商品情報一覧
	 */
	public List<Item> findAll() {
		String sql = "SELECT id,name,price_l,price_m,image_path,description From items";
		List<Item> itemList = template.query(sql, ITEM_ROWMAPPER);
		return itemList;

	}

	/**
	 * 商品情報を名前で曖昧検索する.
	 * 
	 * @param name
	 * @return itemList 商品情報一覧
	 */
	public List<Item> findByName(String name) {
		String sql = "SELECT id,name,price_l,price_m,image_path,description FROM items WHERE name ILIKE :name ORDER BY id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name","%" + name + "%");
		List<Item> itemList = template.query(sql, param, ITEM_ROWMAPPER);
		return itemList;

	}

	public Item load(Integer id) {
		String sql = "SELECT id,name,price_l,price_m,image_path,description FROM items WHERE id=:id ORDER BY id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Item item = template.queryForObject(sql, param, ITEM_ROWMAPPER);
		return item;

	}

}

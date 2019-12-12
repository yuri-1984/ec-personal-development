package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.User;

/**
 * usersテーブルを操作するリポジトリクラス.
 * 
 * @author hiraokayuri
 *
 */
@Repository
public class UserRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	public static final RowMapper<User> USER_ROWMAPPER = (rs, i) -> {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setZipcode(rs.getString("zipcode"));
		user.setAddress(rs.getString("address"));
		user.setTelephone(rs.getString("telephone"));

		return user;

	};

	/**
	 * ユーザ情報を登録するメソッド.
	 * 
	 * @param user
	 */
	public void Insert(User user) {
		String sql = "INSERT INTO users (name,email,password,zipcode,address,telephone)values(:name,:email,:password,:zipcode,:address,:telephone)";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("name", user.getName())
				.addValue("email", user.getEmail())
				.addValue("password", user.getPassword())
				.addValue("zipcode", user.getZipcode())
				.addValue("address", user.getAddress())
				.addValue("telephone", user.getTelephone());
		template.update(sql, param);

	}

	public User findByAddressAndPassward(String address, String  password ) {
		String sql ="select id,name,password,zipcode,address,telephone from users where address=:address AND passward =:password";
		SqlParameterSource param = new MapSqlParameterSource().addValue("address",address).addValue("passward", password);
		List<User> userList = template.query(sql,param,USER_ROWMAPPER);
		if(userList.size() == 0) {
			return null;
		}
		return userList.get(0);
		
	}
	
	public List<User> findByAddress(String email) {
		String sql ="select id,name,email,password,zipcode,address,telephone from users where email=:email ";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email",email);
		List<User> userList = template.query(sql,param,USER_ROWMAPPER);
		if(userList.size() == 0) {
			return null;
		}
		return userList;
		
	}
		
		
		
		
	}
	



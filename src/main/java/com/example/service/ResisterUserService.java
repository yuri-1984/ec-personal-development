package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;

/**
 * ユーザ登録情報を操作するサービスクラス.
 *
 * @author hiraokayuri
 *
 */
@Service
@Transactional
public class ResisterUserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * ユーザ情報を登録する.
	 * 
	 * @param user
	 */
	public void resisterUserService(User user) {
		// パスワードをハッシュ化してセット
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.Insert(user);
	}

	public List<User> findByAddress(String email) {
		List<User> userList = userRepository.findByAddress(email);
		return userList;
	}

}

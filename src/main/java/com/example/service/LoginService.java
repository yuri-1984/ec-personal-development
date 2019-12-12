package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.repository.UserRepository;

@Service
public class LoginService {

	@Autowired
	private UserRepository repository;

	/**
	 * ログインをします.
	 * 
	 * @param email メールアドレス
	 * @param password    パスワード
	 * @return ユーザー情報 存在しない場合はnullが返ります
	 */
	public User login(String email, String passward) {
		User user = repository.findByAddressAndPassward(email, passward);
		return user;
	}
}

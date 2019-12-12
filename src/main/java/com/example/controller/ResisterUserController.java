package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.RegisterUserForm;
import com.example.service.ResisterUserService;

/**
 * ユーザ登録情報を処理するコントローラークラス.
 * 
 * @author hiraokayuri
 *
 */
@Controller
@RequestMapping("")
public class ResisterUserController {
	@ModelAttribute
	public  RegisterUserForm setupRegisterUserForm() {
		return new RegisterUserForm();
	}
	@Autowired
	private ResisterUserService resisterUserService;

	/**
	 * ユーザ登録画面へ遷移する.
	 * 
	 * @return resister_user.html 登録画面.
	 */
	@RequestMapping("/showRegisterUserPage")
	public String showRegisterUserPage( ) {
		return "register_user";
	}

	/**
	 *ユーザー情報を登録する.
	 * @param form ユーザ情報用フォーム
	 * 
	 * @return ログイン画面へリダイレクト.
	 */
	@RequestMapping("/registerUser")
	public String registerUser(@Validated RegisterUserForm form, BindingResult result,Model model) {
		//メールアドレスが重複していないかチェック
		if(resisterUserService.findByAddress(form.getAddress())!= null) {
			result.rejectValue("email","null","*既に登録されているメールアドレスです");
		}
		// パスワード確認
		if (!(form.getPassword().equals(form.getConfirmationPassword()))) {
			result.rejectValue("confirmationPassword","null", "*入力されたパスワードと異なります");

		}
		// 条件3：上記以外にエラーがないかチェック
		if(result.hasErrors()) {
			return showRegisterUserPage();
		}
		// 条件4：何もエラーがない場合
		User user = new User();
		BeanUtils.copyProperties(form, user);
		resisterUserService.resisterUserService(user);
		
		return "redirect:/toLoginPage";
		
		

	}

}

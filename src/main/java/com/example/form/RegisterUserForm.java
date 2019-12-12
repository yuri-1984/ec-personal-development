package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * ユーザ情報登録時に使用するフォーム.
 * 
 * @author hiraokayuri
 *
 */
public class RegisterUserForm {
	/** 名前 */
	@NotBlank(message = "名前を入力してください")
	private String name;
	/** メールアドレス */
	@Email(message = "メールアドレスの形式ではありません")
	@NotBlank(message = "メールアドレスを入力してください")
	private String  email;
	/** パスワード */
	@Pattern(regexp = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9])(?=.*[!\\x22\\#$%&@'()*+,\\-./_])[A-Za-z0-9!\\x22\\#$%&@'()*+,\\-./_]{8,}$",
			message="*パスワードは英大文字・小文字、数字、記号の4種を含む8桁以上で設定してください")
	@NotBlank(message="*パスワードを入力して下さい")
	private String password;
	/** 確認用パスワード */
	@NotBlank(message="*確認用パスワードを入力して下さい")
	private String confirmationPassword;
	/** 郵便番号 */
	@NotBlank(message="*入力は必須です")
	@Pattern(regexp = "^\\d{3}\\-?\\d{4}$",message="*郵便番号の形式が不正です")
	private String zipcode;
	/** 住所 */
	@NotBlank(message = "住所を入力してください")
	private String address;
	/**	電話番号 */
	@Pattern(regexp = "^[0-9]*$",message="*半角数字で入力してください")
	@NotBlank(message="*電話番号を入力して下さい")
	private String telephone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmationPassword() {
		return confirmationPassword;
	}

	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}

package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

//登入成功 得到的憑證資料

@AllArgsConstructor
@Getter
@ToString
public class UserCert {
	private Integer userId;//使用者 Id
	private String username;
	private String role;
	
	
}

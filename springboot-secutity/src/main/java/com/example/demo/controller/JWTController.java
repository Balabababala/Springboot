package com.example.demo.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.study.security.KeyUtil;

import com.nimbusds.jwt.JWTClaimsSet;



@RestController
@RequestMapping("/jwt")
public class JWTController {

	private static String signingSecret ="12345678901234567890123456789012";
	
	
	
	@PostMapping("/issue")
	public String issueToken(@RequestBody Map<String ,String> req) throws Exception{
		String username = req.get("username");
		String password = req.get("password");
		String phone = req.get("phone");
		String email = req.get("email");
		
		//1.判斷 user password
		
		// JWT: 建立資料主體(之後要進行簽名的部分)
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
				.subject("報名 Java") // 主題
				.issuer("https://edu.cdri.org.tw") // 發行單位
				.claim("name", username) // 額外的資料
				.claim("phone", phone) // 額外的資料
				.claim("email", email) // 額外的資料
				.build();
		
		
		System.out.println("Payload: " + claimsSet);
		
		// 3. 進行簽名
		// 將 claimsSet + 簽名 = token
		String token = KeyUtil.signJWT(claimsSet, signingSecret);
		System.out.println("Token(JWT): " + token);
		
		return token;
	}
	@GetMapping("/verify")
	// Authorization: Bearer xxxx.xxxx.xxxx
	public Map<String, String> verifyToken(@RequestHeader("Authorization") String authHeader) throws Exception {
		String token = authHeader.substring(7);
		// 驗證 Token(JWT)
		if(KeyUtil.verifyJWTSignature(token, signingSecret)) {
			System.out.println("驗證成功");
			// 讀取 Token(JWT) 中 Claims(Payload) 的資料
			JWTClaimsSet claims = KeyUtil.getClaimsFromToken(token);
			return Map.of("status", "success", 
						  "subject", claims.getSubject(),
						  "issuer", claims.getIssuer(),
						  "name", claims.getStringClaim("name"),
						  "phone", claims.getStringClaim("phone"),
						  "email", claims.getStringClaim("email")
						  );
		} else {
			System.out.println("驗證失敗");
			return Map.of("status", "哈哈哈 驗證fail loser");
		}
	}
	
}

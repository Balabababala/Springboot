package com.example.demo.study.mac;

import java.util.Arrays;

import javax.crypto.SecretKey;

import com.example.demo.study.security.KeyUtil;

//MAC訊息驗證碼
public class MACExample {
	
		public static void main(String[] arge) throws Throwable {
			// 1.定義訊息
			String message="上半年獎金每人加發200個月";
			
			
			//2.搞金鑰
			SecretKey macKey= KeyUtil.generateKeyForHmac(); //預設 HmacSHA256
			//3.利用此密鑰(macKey) +訊息(message) 生成 MAC 值
			byte[] macValue = KeyUtil.generateMac(macKey, message);
			System.out.printf("原MAC值: %s%n",Arrays.toString(macValue));
			//4.macValue 轉 16 進位 印出
			String macHexValue =KeyUtil.bytesToHex(macValue);
			System.out.printf("16進位 MAC值: %s %n",macHexValue);
			
			
			//5. 比對 確任完整性
			String receivedMessage =message; 	//收到資料
			byte[] computeMacValue = KeyUtil.generateMac(macKey, receivedMessage);
			String computeMacHexValue =KeyUtil.bytesToHex(computeMacValue);
			
			//6.比對 是否相同
			if(macHexValue.equals(computeMacHexValue)) {
				System.out.printf("驗證成功 %s = %s %n",macHexValue,computeMacHexValue);
				System.out.printf("資料是: %s %n",receivedMessage);
			}else {
				System.out.printf("驗證失敗 哈哈哈 %n");
			}
			
		}
}

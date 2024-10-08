package com.jung.Ssave.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Encrypt {

	public String getSalt() {
		
		//1. Random, byte 객체 생성
		SecureRandom  secureRandom = new SecureRandom ();
		byte[] salt = new byte[20];
		
		//2. 난수 생성
		secureRandom.nextBytes(salt);
		
		//3. byte To String (10진수의 문자열로 변경)
		StringBuffer stringBuffer = new StringBuffer();
		for(byte byteSalt : salt) {
			stringBuffer.append(String.format("%02x", byteSalt));
		};
		
		return stringBuffer.toString();
		
	}
	
	
	
	public String getEncrypt(String password, String salt) {
		
		String result = "";
		try {
			//1. SHA256 알고리즘 객체 생성
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			
			//2. 비밀번호와 salt 합친 문자열에 SHA 256 적용
			messageDigest.update((password+salt).getBytes());
			byte[] pwdsalt = messageDigest.digest();
			
			//3. byte To String (10진수의 문자열로 변경)
			StringBuffer stringBuffer = new StringBuffer();
			for (byte bytePasswordSalt : pwdsalt) {
				stringBuffer.append(String.format("%02x", bytePasswordSalt));
			}
			
			result = stringBuffer.toString();
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		
		}
		
		return result;
	
	}
	
	
	
	
}

package com.jung.Ssave.kakaologin.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jung.Ssave.common.Encrypt;
import com.jung.Ssave.kakaologin.domain.KakaoUser;
import com.jung.Ssave.kakaologin.repository.KakaoUserRepository;

@Service
public class KakaoLoginService {

	@Value("${kakao.client_id}")
	private String client_id;
	private KakaoUserRepository kakaoUserRepository;
	
	public KakaoLoginService(KakaoUserRepository kakaoUserRepository) {
		this.kakaoUserRepository = kakaoUserRepository;
		
	}
	
	public String kakaoConnect() {
		
		StringBuffer url = new StringBuffer();
		url.append("https://kauth.kakao.com/oauth/authorize?");
		url.append("client_id=" + client_id);
		url.append("&redirect_uri=http://localhost:8080/kakao/callback");
		url.append("&response_type=code");
		
		return "redirect:" + url.toString();
		
	}
	
	
	
	public String getToken(String code) throws Exception {
		
		String access_Token= "";
		// Endpoint URL = API가 서버에서 자원에 접근할 수 있도록 하는 URL
		final String requestUrl = "https://kauth.kakao.com/oauth/token";
		// 토큰을 요청할 URL 객체 생성
		URL url = new URL(requestUrl);
		
		//Http연결 설정
		HttpURLConnection httpUrlConnection = (HttpURLConnection)url.openConnection();
		httpUrlConnection.setRequestMethod("POST");
		httpUrlConnection.setDoOutput(true);
		
		// 서버로 요청 보내기
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpUrlConnection.getOutputStream()));
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("grant_type=authorization_code");
		stringBuilder.append("&client_id="+client_id);
		stringBuilder.append("&redirect_uri=http://localhost:8080/kakao/callback");
		stringBuilder.append("&code="+code); 
		// code의 redirect를 통해서 바로 "로그인을 할시"에 code=?를 뜨게 만들고 이로써 '?'의 값은 위에 code의 값으로 작용하고  getToken()메소드의 파라미터 code로 작용하게 됨
		// 로그인을 하고 동의절차에서 동의를 누르는 순간(즉, 동의 버튼이 'code의 값'을 요청값으로 보내게됨)
		bufferedWriter.write(stringBuilder.toString());
		bufferedWriter.flush();
		
		// 서버의 응답 데이텉 가져옴
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
		String line="";
		String result="";
		
		//result에 토큰이 포함된 응답데이터를 한줄씩 저장
		while((line = bufferedReader.readLine()) != null) {
			
			result += line;
			
		}
		  // 발급된 토큰 확인 로그 추가
		 System.out.println("Token response: " + result); // 응답 전체 출력
		
		// 값 추출을 위해 파싱한 데이터 JsonElement로 변환
		JsonElement jsonElement = JsonParser.parseString(result);
		
		access_Token = jsonElement.getAsJsonObject().get("access_token").getAsString();
		
		System.out.println("access_token : " + access_Token);  // 액세스 토큰만 출력
		
		bufferedReader.close();
		bufferedWriter.close();
		
		return access_Token;
		
	}
	
	
	
	public boolean validateToken(String accessToken) throws Exception {
	    
		final String requestUrl = "https://kapi.kakao.com/v1/user/access_token_info";
		
	    URL url = new URL(requestUrl);
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    connection.setRequestMethod("GET");
	    connection.setRequestProperty("Authorization", "Bearer " + accessToken);

	    int responseCode = connection.getResponseCode();
	    return responseCode == 200;  // 200 응답 시 유효한 토큰
	
	}
	
	
	public KakaoUser getUserInfo(String access_Token)throws Exception{
		
		final String requestUrl = "https://kapi.kakao.com/v2/user/me";
		
		URL url = new URL(requestUrl);
		
		HttpURLConnection httpUrlConnection = (HttpURLConnection)url.openConnection();
		httpUrlConnection.setRequestMethod("GET");
		httpUrlConnection.setRequestProperty("Authorization", "Bearer " + access_Token);
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
		String line = "";
		String result = "";
		
		while((line = bufferedReader.readLine()) != null) {
			
			result += line;
			
		}
		
		System.out.println("userInfo response: " + result); // 응답 전체 출력
		
		JsonElement jsonElement = JsonParser.parseString(result);
		System.out.println("jsonElement response: " + jsonElement);
		
		JsonObject properties = jsonElement.getAsJsonObject().get("properties").getAsJsonObject();
		JsonObject kakao_account = jsonElement.getAsJsonObject().get("kakao_account").getAsJsonObject();
		
		System.out.println("properties response: " + properties);
		System.out.println("kakao_account response: " + kakao_account);
		
		String thumbnail_image = properties.getAsJsonObject().get("thumbnail_image").getAsString();
        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
        String email = kakao_account.getAsJsonObject().get("email").getAsString();
		
        System.out.println("thumbnail_image response: " + thumbnail_image);
		System.out.println("ninkname: " + nickname);
		System.out.println("email: " + email);
		
		KakaoUser kakaoUser = kakaoUserRepository.findByNickName(nickname);
		
		if(kakaoUser == null) {
			
			Encrypt encrypt = new Encrypt();
			String salt = encrypt.getSalt();
			String encryptEmail = encrypt.getEncrypt(email, salt);
			
			KakaoUser newKakaoUser = KakaoUser.builder()
								           .nickName(nickname)
								           .email(encryptEmail)
								           .thumbnail_image(thumbnail_image)
								           .salt(salt)
								           .build();
			kakaoUser = kakaoUserRepository.save(newKakaoUser);
			
			return kakaoUser;
			
		} else {
			
			Encrypt encrypt = new Encrypt();
			String encryptEmail = encrypt.getEncrypt(email, kakaoUser.getSalt());
			kakaoUser = kakaoUserRepository.findByEmail(encryptEmail);
			
			return kakaoUser;
			
		}
		
	}
	
	
	
	
	
}

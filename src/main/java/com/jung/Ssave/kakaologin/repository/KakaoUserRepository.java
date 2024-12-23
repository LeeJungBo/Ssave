package com.jung.Ssave.kakaologin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jung.Ssave.kakaologin.domain.KakaoUser;

public interface KakaoUserRepository extends JpaRepository<KakaoUser, Integer> {

	public KakaoUser findByNickName(String nickName);
	
	public KakaoUser findByEmail(String email);
	
	
}

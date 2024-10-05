package com.jung.Ssave.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {

	public int insertUser(
			@Param("loginId") String loginId
			, @Param("name") String name
			, @Param("password") String password
			, @Param("phoneNumber") String phoneNumber);
	
	
	
}

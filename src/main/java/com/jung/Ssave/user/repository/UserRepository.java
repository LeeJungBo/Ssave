package com.jung.Ssave.user.repository;



import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jung.Ssave.user.domain.User;

@Mapper
public interface UserRepository {

	public int insertUser(
			@Param("loginId") String loginId
			, @Param("name") String name
			, @Param("password") String encryptPassword
			, @Param("phoneNumber") String phoneNumber);
	
	public User selectUser(
			@Param("loginId") String loginId
			, @Param("password") String password);
	
	public int selectCountByLoginId(@Param("loginId") String loginId);
	
}

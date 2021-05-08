package com.nett.work.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nett.work.dto.User;

@Mapper
public interface UserDao {

	@Select("select * from user ")
	List<User> queryAll();

	@Insert("insert user(userName,uid) values(#{userName},#{uid})")
	int insert(User user);

	@Select("select * from user where uid = #{uid}")
	User findUserById(int uid);

	@Update("UPDATE USER SET username = CASE WHEN (#{userName} != NULL) AND (#{userName} != '') THEN #{userName},PASSWORD = CASE WHEN (#{passWord} != NULL) AND (#{passWord} != '') THEN #{passWord},salary = CASE WHEN (#{salary} != 0) THEN #{salary} WHERE uid = #{uid}")
	int updateUser(@Param("user") User user);

	@Delete("delete from user where uid = #{uid}")
	int deleteUserById(int id);
}

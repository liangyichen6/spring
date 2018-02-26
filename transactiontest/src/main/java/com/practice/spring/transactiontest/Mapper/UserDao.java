package com.practice.spring.transactiontest.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.practice.spring.transactiontest.domain.User;

@Mapper
public interface UserDao {
	
	@Select("select * from user")
	public List<User> listUser();
	
	@Insert("insert into user (username, gender, password) values (#{username}, #{gender}, #{password})")
	@SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = int.class)
	public int addUser(User u);
	
	@Update("update user set username = #{username}, gender = #{gender} , password = #{password} where id = #{id}")
	public int updateUser(User u);
}

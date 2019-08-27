package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.vo.SysUserDeptVo;

@Mapper
public interface SysUserDao {
	
	int updatePassword(
			@Param("password")String password,
			@Param("salt")String salt,
			@Param("id")Integer id
			);
	
	
	
	@Select("select * from sys_users where username=#{username}")
	SysUser findUserByUserName(String username); 

	int updateObject(SysUser entity);
	
	SysUserDeptVo findObjectById(Integer id);
	
	int insertObject(SysUser entity);
	
	List<SysUserDeptVo> findPageObjects(
			@Param("username") String username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	int getRowCount(@Param("username") String username);

	int validById(
			@Param("id")Integer id,
			@Param("valid")Integer valid,
			@Param("modifiedUser")String modifiedUser);


}

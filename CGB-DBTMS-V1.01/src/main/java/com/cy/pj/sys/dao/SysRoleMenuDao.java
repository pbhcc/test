package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysRoleMenuDao {
	
	List<Integer> findMenuIdByRoleIds(@Param("roleIds")Integer[] roleIds);
	
	@Select("select menu_id from sys_role_menus where role_id=#{id}")
	int findMenuIdsByRoleId(Integer id);
	
	@Delete("delete from sys_role_menus where role_id=#{id}")
	int deleteObjectsByRoleId(Integer roleId);
	
	int insertObjects(
			@Param("roleId")Integer roleId,
			@Param("menuIds")Integer[] menuIds);

}

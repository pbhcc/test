package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.vo.SysRoleMenuVo;

@Mapper
public interface SysRoleDao {
	
	SysRoleMenuVo findObjectById(Integer id);
	
	List<SysRole> findPageObjects(
			@Param("name")String name,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);

	int getRowCount(@Param("name")String name);
	
	@Delete("delete from sys_roles where id=#{id}")
	int deleteObject(Integer id);
	
	int insertObject(SysRole entity);
}

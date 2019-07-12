package com.cy.pj.sys.service;

import java.util.Map;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.vo.SysUserDeptVo;

public interface SysUserService {
	
	int updateObject(SysUser entity,Integer[] roleIds);
	
	Map<String,Object> findObjectById(Integer userId);
	
	int saveObject(SysUser entity,Integer[] roleIds);
	
	PageObject<SysUserDeptVo> findPageObjects(String username,Integer pageCurrent);
	
	public int validById(Integer id,Integer valid,String modifiedUser);


}

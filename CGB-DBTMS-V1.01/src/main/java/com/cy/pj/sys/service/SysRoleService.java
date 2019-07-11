package com.cy.pj.sys.service;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.vo.SysRoleMenuVo;

public interface SysRoleService {
	SysRoleMenuVo findObjectById(Integer id);
	
	PageObject<SysRole> findPageObjects(String name,Integer pageCurrent);
	
	int deleteObject(Integer id);
	
	int saveObject(SysRole entity,Integer[] menuIds);
}

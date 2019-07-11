package com.cy.pj.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.service.SysRoleService;
import com.cy.pj.sys.vo.SysRoleMenuVo;
@Service
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	@Override
	public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent) {
		if(pageCurrent==null||pageCurrent<1) 
			throw new IllegalArgumentException("当前页码不存在");
		int rowCount = sysRoleDao.getRowCount(name);
		if(rowCount==0)
			throw new ServiceException("记录不存在");
		int pageSize = 3;
		int startIndex = (pageCurrent-1)*pageSize;
		List<SysRole> records = sysRoleDao.findPageObjects(name, startIndex, pageSize);		
		return new PageObject<>(pageCurrent, pageSize, rowCount, records);
	}

	@Override
	public int deleteObject(Integer id) {
		if(id==null||id<1)
			throw new ServiceException("id的值不正确，id="+id);
		sysRoleMenuDao.deleteObjectsByRoleId(id);
		sysUserRoleDao.deleteObjectsByRoleId(id);
		int row = sysRoleDao.deleteObject(id);
		return row;
	}

	@Override
	public int saveObject(SysRole entity, Integer[] menuIds) {
	  	//1.合法性验证
    	if(entity==null)
    		throw new ServiceException("保存数据不能为空");
    	if(StringUtils.isEmpty(entity.getName()))
    	throw new ServiceException("角色名不能为空");
    	if(menuIds==null||menuIds.length==0)
    		throw new ServiceException("必须为角色赋予权限");
    	//2.保存数据
    	int rows=sysRoleDao.insertObject(entity);
    	sysRoleMenuDao.insertObjects(entity.getId(),menuIds);
    	//3.返回结果
    	return rows;
	}

	@Override
	public SysRoleMenuVo findObjectById(Integer id) {
		if(id==null||id<=0)
			throw new ServiceException("id值不合法");
		SysRoleMenuVo result = sysRoleDao.findObjectById(id);
		if(result==null)
			throw new ServiceException("此记录已经不存在");
		return result;
	}

}

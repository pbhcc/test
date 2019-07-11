package com.cy.pj.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.service.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService{
	@Autowired
	private SysMenuDao sysMenuDao;
	@Override
	public List<Map<String, Object>> findObjects() {
		List<Map<String, Object>> list = sysMenuDao.findObjects();
		if(list==null||list.size()==0) 
			throw new ServiceException("没有找到菜单记录");
		
		return list;
	}
	@Override
	public List<Node> findZtreeMenuNodes() {
		return sysMenuDao.findZtreeMenuNodes();
	}
	@Override
	public int saveObject(SysMenu entity) {
		if(entity==null) 
			throw new ServiceException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("菜单名不能为空");
		int row = sysMenuDao.insertObject(entity);
		return row;
	}
	@Override
	public int updateObject(SysMenu entity) {
		if(entity==null) 
			throw new ServiceException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("记录仪可能不存在");
		int row = sysMenuDao.updateObject(entity);
		return row;
		
	}

}

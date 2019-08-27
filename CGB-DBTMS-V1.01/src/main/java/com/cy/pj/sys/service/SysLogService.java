package com.cy.pj.sys.service;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysLog;

public interface SysLogService {
	
	int insertObject(SysLog entity);

	/**
	 * 基于当前页码值以及用户名查询当前页记录
	 * @param username 页面上输入的用户名
	 * @param pageCurrent 页码值
	 * @return
	 */
	PageObject<SysLog> findPageObjects(
			String username,
			Integer pageCurrent);
	
	int deleteObjects(Integer...ids);
}

package com.cy.pj.sys.service.impl;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;
import com.cy.pj.sys.service.exception.ServiceException;

import lombok.extern.slf4j.Slf4j;
/**
 * 业务逻辑层对象
 * 1)核心业务(日志分页查询,日志删除)
 * 2)扩展业务(权限控制,事务控制,...)
 */
@Slf4j
@Service
public class SysLogServiceImpl implements SysLogService {
	//private Logger log=LoggerFactory.getLogger(SysLogServiceImpl.class);
	@Autowired
	private SysLogDao sysLogDao;
	@Override
	public PageObject<SysLog> findPageObjects(
			String username, Integer pageCurrent) {
		//1.判定参数有效性,合法性
		if(pageCurrent==null||pageCurrent<1) {
		throw new IllegalArgumentException("当前页码值无效");
		}
		//2.基于用户名查询总记录数,并进行判定.
		int rowCount=sysLogDao.getRowCount(username);
		log.info("总记录数:"+rowCount);
		if(rowCount==0)//return null;
		throw new ServiceException("没有找到记录");
		//3.查询当前页记录
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysLog> records=
		sysLogDao.findPageObjects(username,
				startIndex, pageSize);
		//4.对查询结果进行封装并返回(返回给控制层)
//		PageObject<SysLog> po=new PageObject<>();
//		po.setRowCount(rowCount);
//		po.setRecords(records);
//		po.setPageSize(pageSize);
//		po.setPageCurrent(pageCurrent);
//		int pageCount=rowCount/pageSize;
//		if(rowCount%pageSize!=0) {
//			pageCount++;
//		}
	   //po.setPageCount((rowCount-1)/pageSize+1);
		return new PageObject<>(pageCurrent, pageSize, rowCount, records);
	}
	@Override
	public int deleteObjects(Integer... ids) {
		//验证参数合法
		if(ids==null||ids.length==0) {
			throw new IllegalArgumentException("请选择一个");
		}
		//执行删除操作
		int rows;
		try {
			rows = sysLogDao.deleteObjects(ids);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new ServiceException("系统故障，正在恢复中...");
		}
		//结果验证
		if(rows==0) {
			throw new ServiceException("记录可能不存在");
		}
		//返回结果
		return rows;
	}
}


























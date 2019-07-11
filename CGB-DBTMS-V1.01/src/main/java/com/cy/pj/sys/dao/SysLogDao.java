package com.cy.pj.sys.dao;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.sys.entity.SysLog;
@Mapper
public interface SysLogDao {
	
	 /**
	  * 按照查询条件统计总记录数
	  * @param username
	  * @return
	  */
	 int getRowCount(@Param("username")String username);
	 /**
	  * 查询当前页要呈现的记录
	  * @param username 查询条件
	  * @param startIndex 当前页的起始位置
	  * @param pageSize 页面大小
	  * @return 当前页要呈现的记录
	  */
	 List<SysLog> findPageObjects(
			 String username,
			 Integer startIndex,
			 Integer pageSize);
	
	 @Delete("delete from sys_logs where id=#{id}")
	 int deleteObject(Integer id);
	 /**
	  * 基于多个id执行删除操作
	  * @param ids
	  * @return
	  */
	 int deleteObjects(@Param("ids")Integer...ids);

}

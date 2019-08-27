package com.cy.pj.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.entity.SysMenu;
@Mapper
public interface SysMenuDao {

	List<String> findPermissions(
			@Param("menuIds")
			Integer[] menuIds);



	List<Map<String,Object>> findObjects();

	List<Node> findZtreeMenuNodes();

	int insertObject(SysMenu entity);

	int updateObject(SysMenu entity);

}

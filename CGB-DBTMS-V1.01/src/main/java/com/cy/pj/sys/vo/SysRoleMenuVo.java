package com.cy.pj.sys.vo;
import java.util.List;
import lombok.Data;

@Data
public class SysRoleMenuVo {
	private Integer id;
	private String name;
	private String note;
	private List<Integer> menuIds;

}

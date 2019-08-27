package com.cy.pj.sys.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;
@Data
public class SysUser implements Serializable{
	private static final long serialVersionUID = -7447006575577551655L;
	private Integer id;
	@NotBlank(message="username not be null")
	private String username;
	private String password;
	private String salt;//盐值
	private String email;
	private String mobile;
	private Integer valid=1;
	private Integer deptId;
	private Date createdTime;
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;


}

package com.cy.pj.sys.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 在controller负责所有页面的呈现
 */
@Controller
@RequestMapping("/")
public class PageController {
	@RequestMapping("doLoginUI")
	public String doLoginUI() {
		return "login";
	}
	
	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		return "starter";
	}
	@RequestMapping("doPageUI")
	public String doPageUI() {
		return "common/page";
	}	
	@RequestMapping("{module}/{page}")//rest
	public String doMuduleUI(@PathVariable String page) {
		return "sys/"+page;
	}

}







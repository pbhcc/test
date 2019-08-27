package com.cy.pj.common.config;

import java.util.LinkedHashMap;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringShiroConfig {
	/** 会话时长配置 */
	public DefaultWebSessionManager newSessionManager() {
		 DefaultWebSessionManager sManager=
				 new DefaultWebSessionManager();
		 sManager.setGlobalSessionTimeout(60*60*1000);
		 return sManager;
	 }

	//@Bean
	public CookieRememberMeManager newRememberMeManager() {
		CookieRememberMeManager cManager = new CookieRememberMeManager();
		cManager.setCookie(newCookie());
		return cManager;
	}
	
	public SimpleCookie newCookie() {
		SimpleCookie c = new SimpleCookie("rememberMe");
		c.setMaxAge(10*60);
		return c;
	}
	
	/** Shiro框架提供的内置缓存 */
	@Bean
	public CacheManager newCacheManager() {
		return new MemoryConstrainedCacheManager();
	}
	
	@Bean
	public AuthorizationAttributeSourceAdvisor newAuthorizationAttributeSourceAdvisor (
			@Autowired SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor adivsor = new AuthorizationAttributeSourceAdvisor();
		adivsor.setSecurityManager(securityManager);
		return adivsor;
	}
	
	@Bean("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator newDefaultAdvisorAutoProxyCreator() {
		return new DefaultAdvisorAutoProxyCreator();
	}
	
	@Bean("lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor newLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	
	@Bean
	public SecurityManager newSecurityManager(@Autowired Realm realm,
			@Autowired CacheManager chcheManager) {
		DefaultWebSecurityManager sManager = new DefaultWebSecurityManager();
		sManager.setRealm(realm);
		sManager.setCacheManager(chcheManager);
		sManager.setRememberMeManager(newRememberMeManager());
		sManager.setSessionManager(newSessionManager());
		return sManager;
	}
	
	@Bean("ShrioFilterFactory")
	public ShiroFilterFactoryBean newShiroFilterFactoryBean(@Autowired SecurityManager securityManager) {
		 ShiroFilterFactoryBean sfBean = new ShiroFilterFactoryBean();
		 sfBean.setSecurityManager(securityManager);
		 sfBean.setLoginUrl("/doLoginUI");
		 //定义map指定请求过滤规则(哪些资源允许匿名访问,哪些必须认证访问)
		 LinkedHashMap<String,String> map = new LinkedHashMap<>();
		 //静态资源允许匿名访问:"anon"
		 map.put("/bower_components/**","anon");
		 map.put("/build/**","anon");
		 map.put("/dist/**","anon");
		 map.put("/plugins/**","anon");
		 map.put("/user/doLogin","anon");
		 map.put("/doLogout","logout");
		 //除了匿名访问的资源,其它都要认证("authc")后访问
		 map.put("/**", "authc");
		 map.put("/**","user");//authc
		 sfBean.setFilterChainDefinitionMap(map);
		 return sfBean;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}























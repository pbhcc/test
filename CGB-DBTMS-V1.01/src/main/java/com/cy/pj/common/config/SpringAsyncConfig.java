package com.cy.pj.common.config;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAsyncConfig {
	private int corePoolSize = 10;
	private int maximumPoolSize = 20;
	private long keepAliveTime = 30;
	private int queueCapacity = 20;
	private ThreadFactory threadFactory = new ThreadFactory() {
		private AtomicLong number = new AtomicLong();
		@Override
		public Thread newThread(Runnable r) {			
			return new Thread(r, "async-thread"+number);
		}
	};
	@Bean("asyncThreadPool")
	public ThreadPoolExecutor newThreadPoolExecutor() {
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize,
				maximumPoolSize, 
				keepAliveTime, 
				TimeUnit.SECONDS, 
				new LinkedBlockingDeque<>(queueCapacity),
				threadFactory);
		return threadPool;
	}
}

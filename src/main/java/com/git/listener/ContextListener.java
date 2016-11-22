package com.git.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.git.utils.FTPTaskService;

public class ContextListener implements ServletContextListener{
	public static final Logger logger = Logger.getLogger(ContextListener.class);
	public void contextInitialized(ServletContextEvent sce) {
		FTPTaskService ftpTaskService = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()).getBean(FTPTaskService.class);
		ftpTaskService.init();
		logger.error("启动服务器");
		
	}

	public void contextDestroyed(ServletContextEvent sce) {
		logger.error("关闭服务器");
	}

}

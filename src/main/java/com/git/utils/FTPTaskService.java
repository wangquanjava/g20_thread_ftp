package com.git.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.git.domain.FtpEntity;
import com.git.service.FtpService;

@Service
public class FTPTaskService{
	static final ExecutorService executorServicePool = Executors.newFixedThreadPool(4);
	static final List<Runnable> cancelList = Collections.synchronizedList(new ArrayList<Runnable>());
	@Autowired
	private FtpService ftpService;
	
	public boolean put(String taskId){
		try {
			executorServicePool.execute(new FTPThread(taskId));
			System.out.println("提交了"+taskId+"号任务");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 初始化方法，把数据库中加载到队列中
	 */
	public void init(){
		List<FtpEntity> ftpEntities = this.ftpService.getAll();
		for (FtpEntity ftpEntity : ftpEntities) {
			this.put(ftpEntity.getTaskId());
			this.ftpService.delete(ftpEntity);
		}
	}
	
	/**
	 * 关闭服务器的时候，回收任务
	 */
	public void destroyFTPUtils(){
		//这里收集还未执行的
		FTPThread ftpThread = null;
		List<Runnable> notDoList = executorServicePool.shutdownNow();
		for (Runnable runnable : notDoList) {
			ftpThread = (FTPThread) runnable;
			
			this.ftpService.add(new FtpEntity(ftpThread.getTaskId()));
			System.out.println(ftpThread.getTaskId()+"没有执行");
		}
		//这里收集强制被中断的
		for (Runnable runnable : cancelList) {
			ftpThread = (FTPThread) runnable;
			this.ftpService.add(new FtpEntity(ftpThread.getTaskId()));
			System.out.println(ftpThread.getTaskId()+"强制被中断");
		}
	}
}

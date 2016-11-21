package com.git.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FTPTaskService{
	static final ExecutorService executorService = Executors.newFixedThreadPool(4);
	static final List<Runnable> cancelList = Collections.synchronizedList(new ArrayList<Runnable>());
	
	public static boolean put(String id){
		try {
			executorService.execute(new FTPThread(id));
			System.out.println("提交了"+id+"车");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 不载提交任务，没有新任务以后
	 */
	public static void destroyFTPUtils(){
		//这里收集还未执行的
		FTPThread ftpThread = null;
		for (Runnable runnable : executorService.shutdownNow()) {
			ftpThread = (FTPThread) runnable;
			System.out.println(ftpThread.getId()+"没有执行");
		}
		//这里收集强制被中断的
		for (Runnable runnable : cancelList) {
			ftpThread = (FTPThread) runnable;
			System.out.println(ftpThread.getId()+"强制被中断");
		}
	}
}

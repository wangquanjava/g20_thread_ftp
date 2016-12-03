package com.git.utils;

import java.io.FileInputStream;

public class FTPThread implements Runnable {
	
	private String taskId;
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public FTPThread(String taskId) {
		super();
		this.taskId = taskId;
	}

	@Override
	public void run() {
		//进行查询和发送文件
		try {
			//执行上传任务
			boolean upload = new FtpUtil().upload("/", taskId+".png", new FileInputStream("C:\\Users\\tdp\\Desktop\\download_all.png"));
			if (!upload) {
				throw new Exception();
			}
			
			Thread.sleep(3000);
			System.out.println(taskId+"执行结束");
		} catch (Exception e) {
			if (FTPTaskService.executorServicePool.isShutdown()) {
				//如果是因为线程池.showdownNow()导致，就写入list
				FTPTaskService.cancelList.add(this);
			}else{
				//如果执行错误，就直接输出
				System.out.println(taskId+"出现异常");
			}
		}
	}
}

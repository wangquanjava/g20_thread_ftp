package com.git.utils;

import java.io.FileInputStream;

public class FTPThread implements Runnable {
	
	private String id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public FTPThread(String id) {
		super();
		this.id = id;
	}

	@Override
	public void run() {
		//进行查询和发送文件
		try {
			boolean upload = new FtpUtil().upload("/", id+".png", new FileInputStream("C:\\Users\\tdp\\Desktop\\download_all.png"));
			Thread.sleep(3000);
			if (!upload) {
				throw new Exception();
			}
			System.out.println(id+"已经走完路");
		} catch (Exception e) {
			if (FTPTaskService.executorServicePool.isShutdown()) {
				//如果是因为线程池.showdownNow()导致，就写入list
				FTPTaskService.cancelList.add(this);
				System.out.println(id+"强制取消");
			}else{
				//如果执行错误，就直接输出
				System.out.println(id+"翻车");
			}
			//删除临时文件
			new FtpUtil().deleteTmp("/", id+".png");
		}
	}
}

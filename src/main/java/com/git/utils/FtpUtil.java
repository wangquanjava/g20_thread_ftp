package com.git.utils;

import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FtpUtil {
	private static final String ip = "127.0.0.1";
	private static final Integer port = 21;
	private static final String username = "wang";
	private static final String password = "123456";
	
	/**
	 * 上传
	 * @param path
	 * @param filename
	 * @param inputStream
	 * @return
	 */
	public boolean upload(String path,String filename,InputStream inputStream){
		try {
			FTPClient ftpClient = getFtpClient();
			
			//切换到上传目录
			ftpClient.changeWorkingDirectory(path);
			
			//开始上传，使用临时文件名
			ftpClient.storeFile(filename+".tmp", inputStream);
			//上传完，修改文件名
			ftpClient.rename(filename+".tmp", filename);
			
			//退出登陆，断开连接
			ftpClient.logout();
			ftpClient.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 用于出现了异常，删除这个临时文件
	 * @param path
	 * @param filename
	 * @return
	 */
	public boolean deleteTmp(String path,String filename){
		try {
			//切换到上传目录
			FTPClient ftpClient = this.getFtpClient();
			ftpClient.changeWorkingDirectory(path);
			
			ftpClient.deleteFile(filename+".tmp");
			//退出登陆，断开连接
			ftpClient.logout();
			ftpClient.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 因为频率不高，所以这里每次都重新连接一次
	 * @return
	 * @throws Exception
	 */
	private FTPClient getFtpClient() throws Exception{
		FTPClient ftpClient = new FTPClient();
		//不让服务器校验客户端ip
		ftpClient.setRemoteVerificationEnabled(false);
		
		//连接登录
		ftpClient.connect(ip, port);
		ftpClient.login(username, password);
		
		//激活被动模式
		ftpClient.enterLocalPassiveMode();
		
		//设置文件格式、编码
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		ftpClient.setControlEncoding("utf-8");
		return ftpClient;
	}
    
}
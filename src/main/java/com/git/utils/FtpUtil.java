package com.git.utils;

import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;

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
			ftpClient.changeWorkingDirectory(path);
			ftpClient.storeFile(filename, inputStream);
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
		ftpClient.connect(ip, port);
		
		ftpClient.login(username, password);
		
		ftpClient.enterLocalPassiveMode();
		
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		ftpClient.setControlEncoding("UTF-8");
		return ftpClient;
	}
    
}
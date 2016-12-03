package com.git.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.git.domain.AjaxJson;
import com.git.utils.FTPTaskService;

/**
 * 这里实现了ftp的异步发送
 * 功能：
 *    1.开机自动扫描ftp_task表中的数据，加入到队列中
 *    2.使用异步方式添加ftp任务
 *    3.关闭服务器的时候，会
 *
 */
@Controller
public class FtpController {
	@Autowired
	private FTPTaskService ftpTaskService;
	
	@RequestMapping("add")
	public ResponseEntity<AjaxJson> add(){
		for (int i = 0; i < 20; i++) {
//			添加任务
			this.ftpTaskService.put(i+"");
		}
		return ResponseEntity.status(200).body(new AjaxJson(true,"成功",null));
	}
	
	@RequestMapping("destroy")
	//模拟关机，销毁线程。可以放到listener中的destroy中
	public ResponseEntity<AjaxJson> destroy(){
		this.ftpTaskService.destroyFTPUtils();
		
		return ResponseEntity.status(200).body(new AjaxJson(true,"成功",null));
	}
	
	
	
}

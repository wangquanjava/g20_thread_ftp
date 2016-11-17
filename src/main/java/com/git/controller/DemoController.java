package com.git.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.git.domain.AjaxJson;
import com.git.service.DemoService;
import com.git.utils.FTPTaskService;

@Controller
public class DemoController {
	@Autowired
	private DemoService demoService;
	
	@RequestMapping("add")
	public ResponseEntity<AjaxJson> add(){
		for (int i = 0; i < 20; i++) {
			FTPTaskService.put(i+"");
		}
		return ResponseEntity.status(200).body(null);
	}
	@RequestMapping("destroy")
	public ResponseEntity<AjaxJson> destroy(){
		FTPTaskService.destroyFTPUtils();
		return ResponseEntity.status(200).body(new AjaxJson(true,"成功",null));
	}
	
	
	
}

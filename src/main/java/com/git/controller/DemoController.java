package com.git.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.git.domain.AjaxJson;
import com.git.service.DemoService;

@Controller
public class DemoController {
	@Autowired
	private DemoService demoService;
	
	@RequestMapping("get")
	public ResponseEntity<AjaxJson> get(){
		return ResponseEntity.status(200).body(null);
	}
	
}

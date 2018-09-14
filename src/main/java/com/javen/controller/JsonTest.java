package com.javen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javen.model.User;

/**
 * json数据交互测试
 * @author 龙伟
 * 2018年9月12日
 */

@Controller
public class JsonTest {
	
	
		//请求json，响应json
		@RequestMapping(value="requestJson", produces = {"application/json"})
		public @ResponseBody User requestJson(@RequestBody User user) {
			
			//@ResponseBody将user转换成json输出
			return user;
		}
		
			
		//请求key/value，响应json
		@RequestMapping("responseJson")
		@ResponseBody
		public  User responseJson(User user) {
			
			//@ResponseBody将user转换成json输出
			return user;
		}
}

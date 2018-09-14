package com.javen.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户登录验证
 * @author 龙伟
 * 2018年9月12日
 */

@Controller
public class LoginController {

	//登录
	@RequestMapping("/login")
	public String Login(HttpSession session,String userName,String pwd) throws Exception{
		
		//调用service进行验证
		//...
		
		//在session中保存用户身份信息
		session.setAttribute("userName", userName);
		//重定向到主页面
		return "redirect:/JsonTest.jsp";
		
	}
	
	//退出
	@RequestMapping("/loginOut")
	public String LoginOut(HttpSession session,String userName,String pwd) throws Exception{
		
		//调用service进行验证
		//...
		
		//在session中保存用户身份信息
		session.invalidate();
		//重定向到登录界面
		return "redirect:/login.jsp";
		
	}
	
}

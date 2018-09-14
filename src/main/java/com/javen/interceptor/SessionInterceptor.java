package com.javen.interceptor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class SessionInterceptor extends HandlerInterceptorAdapter {

	//进入Handler方法之前执行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws IOException, ServletException {
		//获取请求的url
		String url = request.getRequestURI();
		//判断是否为公共地址（一般放在配置文件中）
		if(url.indexOf("login")>=0) {
			return true;
		}		
		//从session中获取信息
		
		System.out.println("进入了session拦截器");
		String userName = request.getParameter("userName");
		System.out.println("name======="+userName);
		System.out.println("handler======="+handler);
				 
		if(userName!=null) {
			System.out.println("放行");
			return true;
		}else {
			System.out.println("拦截");
			//request.getRequestDispatcher("index.jsp").forward(request,response); 
			return true;
		}
}
	//进入Handler方法之后，返回modelAndView之前执行
	 public void postHandler(HttpServletRequest request, HttpServletResponse response, 
			 Object handler, ModelAndView modelAndView) throws Exception {
		 
	    }
    
	//进入Handler完成执行此方法
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception
            ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
    

}

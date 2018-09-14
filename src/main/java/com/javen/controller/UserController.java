package com.javen.controller;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javen.model.User;
import com.javen.service.IUserService;
  
  
@Controller  
@RequestMapping("/user")  
// /user/**
public class UserController {  
	private static Logger log=LoggerFactory.getLogger(UserController.class);
	 @Resource  
	 private IUserService userService;     
    
    // /user/test?id=1
    @RequestMapping(value="/test",method=RequestMethod.GET)  
    public String test(HttpServletRequest request,Model model){  
        int userId = Integer.parseInt(request.getParameter("id"));  
        System.out.println("userId:"+userId);
        User user=null;
        if (userId==1) {
        	 user = new User();  
        	 user.setAge(11);
        	 user.setId(1);
        	 user.setPassword("123");
        	 user.setUserName("javen");
		}
       
        log.debug(user.toString());
        model.addAttribute("user", user);  
        return "index";  
    }  
    
    
    // /user/showUser?id=1
    @RequestMapping(value="/showUser",method=RequestMethod.GET)  
    public String toIndex(HttpServletRequest request,Model model){  
        int userId = Integer.parseInt(request.getParameter("id"));  
        System.out.println("userId:"+userId);
        User user = this.userService.getUserById(userId);  
        log.debug(user.toString());
        model.addAttribute("user", user);  
        return "showUser";  
    }  
    
 // /user/showUser2?id=1
    @RequestMapping(value="/showUser2",method=RequestMethod.GET)  
    public String toIndex2(@RequestParam("id") String id,Model model){  
        int userId = Integer.parseInt(id);  
        System.out.println("userId:"+userId);
        User user = this.userService.getUserById(userId);  
        log.debug(user.toString());
        model.addAttribute("user", user);  
        return "showUser";  
    }  
    
    //url模板模式映射（RESTful格式的url）
    // /user/showUser3/{id}
    @RequestMapping(value="/showUser3/{id}",method=RequestMethod.GET)  
    public String toIndex3(@PathVariable("id")String id,Map<String, Object> model){  
        int userId = Integer.parseInt(id);  
        System.out.println("userId:"+userId);
        User user = this.userService.getUserById(userId);  
        log.debug(user.toString());
        model.put("user", user);  
        return "showUser";  
    }  
    
 // /user/{id}
    @RequestMapping(value="{id}",method=RequestMethod.GET)  
    public @ResponseBody User getUserInJson(@PathVariable String id,Map<String, Object> model){  
        int userId = Integer.parseInt(id);  
        System.out.println("userId:"+userId);
        User user = this.userService.getUserById(userId);  
        log.info(user.toString());
        return user;  
    }  
    
    // /user/{id}
    @RequestMapping(value="/jsontype/{id}",method=RequestMethod.GET)  
    public ResponseEntity<User>  getUserInJson2(@PathVariable String id,Map<String, Object> model){  
        int userId = Integer.parseInt(id);  
        System.out.println("userId:"+userId);
        User user = this.userService.getUserById(userId);  
        log.info(user.toString());
        return new ResponseEntity<User>(user,HttpStatus.OK);  
    } 
    
    //文件上传、
    @RequestMapping(value="/upload")
    public String showUploadPage(){
    	return "user_admin/file";
    }
    
    @RequestMapping("/doUpload")
    public String doUploadFile(
    		HttpServletRequest request,
    		MultipartFile items_pic   //接收商品图片
    		) throws IOException{
//    	if (!file.isEmpty()) {
//			log.info("Process file:{}",file.getOriginalFilename());
//		}
//    	FileUtils.copyInputStreamToFile(file.getInputStream(), new File("E:\\",System.currentTimeMillis()+file.getOriginalFilename()));
    	
    	//原始名称
    	String originalFilename = items_pic.getOriginalFilename();
    	//上传图片
    	if(items_pic!=null && originalFilename!=null &&originalFilename.length()>0) {
    		//存储图片的物理路径
    		String pic_path="E:\\Upload\\temp\\";
    		//新的图片名称
    		String newFileName = UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));
    		
    		System.out.println("newFileName:"+newFileName);
    		//新图片
    		File newFile = new File(pic_path+newFileName);
    		//将内存中的数据写入磁盘
    		System.out.println("newFile:"+newFile);
    		items_pic.transferTo(newFile);
    		//将新图片名称写到itemsCustomer
    		//itemsCustomer.setPic(newFileName);
    		System.out.println("items_pic:"+items_pic);
    		request.setAttribute("items_pic",newFileName);
    	}
    	return "user_admin/file";
    }
    
    @RequestMapping("/query")  
   // @ResponseBody
    public String query(@RequestParam("userName") String userName,@RequestParam("pwd") String pwd) {
    	
    	 System.out.println("前端传过来的数据：userName:"+userName+",pwd:"+pwd);
    	 User user = this.userService.getUserByName(userName); 
    	 log.info(user.toString());
    	 System.out.println("查询之后的数据："+"userName："+user.getUserName()+",pwd："+user.getPassword());
    	 if(user.getUserName()==null) {
    		 System.out.println("用户名错误！");
    		 return "1";
    	 }else if(user.getPassword()==pwd) {
    		 System.out.println("密码错误！");
    		 return "1";
    	 }
    	 return "index";
    	
    }
    @RequestMapping(value="/login")
    public String showPage(){
    	return "1";
    }
}  
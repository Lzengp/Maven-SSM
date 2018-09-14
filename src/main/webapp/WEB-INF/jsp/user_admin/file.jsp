<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>上传文件</h1>
	<!-- enctype 属性规定在发送到服务器之前应该如何对表单数据进行编码。
		application/x-www-form-urlencoded	在发送前编码所有字符（默认）
		multipart/form-data	不对字符编码。在使用包含文件上传控件的表单时，必须使用该值。
		text/plain	空格转换为 "+" 加号，但不对特殊字符编码
	 -->
	<form method="post" action="${pageContext.request.contextPath }/user/doUpload" enctype="multipart/form-data">
	<input type="file" name="items_pic"/>
	<input type="submit" value="上传文件"/><br/>
	图片:
	<c:if test="${items_pic != null }">
	<img src="/pic/${items_pic }" width=100 height=100/>
	</c:if>
	</form>
	当前用户：${userName }
	<c:if test="${userName != null }">
	<a href="${pageContext.request.contextPath }/loginOut">退出</a>
	</c:if>
	
</body>
</html>












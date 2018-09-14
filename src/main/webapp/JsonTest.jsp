<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Json交互测试</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.min.js"></script>
<script type="text/javascript">
//请求json，响应json
function requestJson(){
	$.ajax({
		type:'post',
		url:'${pageContext.request.contextPath }/requestJson',
		contentType:'application/json;charset=utf-8',
		//数据格式是json串，用户信息
		data:'{"name":"zhang","age":20}',
		success:function(data){//返回json结果
			alert(data.age);
		}
	});
}

//请求key/value，响应json
function responseJson(){
	$.ajax({
		type:'post',
		url:'${pageContext.request.contextPath }/responseJson',
		//默认请求key/value格式，也就是application/x-www-form-urlencoded
		//contentType:'application/json;charset=utf-8',
		//数据格式是json串，用户信息
		data:'name=zhang&age=20',
		success:function(data){//返回json结果
			alert(data.age);
		}	
	});
}
</script>
</head>
<body>
<input type="button" onclick="requestJson()" value="传入json数据"/>
<input type="button" onclick="responseJson()" value="传入key/value数据"/>
</body>
</html>
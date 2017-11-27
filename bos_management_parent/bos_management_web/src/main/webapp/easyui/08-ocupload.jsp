<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ocupload-一键上传</title>
<!-- 引入easyui资源文件 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
<!-- 引入ztree的资源文件 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ocupload-1.1.2.js"></script>
</head>
<body>
	<!-- 传统文件上传
		target:配置界面刷新位置的属性，_self：当前窗口刷新，默认值；_blank：在新窗口刷新；其他容器的name属性
	 -->
	<!-- <form target="targetFile" id="uploadForm" action="abc.action" method="post" enctype="multipart/form-data">
		<input type="file" name="uploadfile" onchange="document.getElementById('uploadForm').submit();"/>
	</form>
	<iframe name="targetFile" style="display:none"></iframe> -->
	
	<a id="uploadBtn" class="easyui-linkbutton">上传</a>
	<script type="text/javascript">
	$(function(){
		$('#uploadBtn').upload({
			 action: 'CourseXMLFileUploadHander.ashx', //相当于form中的action属性，提交请求地址
             name: 'xml', //相当于传统文件上传输入框的name属性，后台接收文件的变量名称
             onComplete: function (data) {
            	 alert(data);
             }  
		});
	});
	</script>
</body>
</html>
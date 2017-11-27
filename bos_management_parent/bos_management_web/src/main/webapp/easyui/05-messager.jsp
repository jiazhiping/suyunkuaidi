<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EasyUI-messager-消息提示控件</title>
<!-- 引入easyui资源文件 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
<!-- 引入ztree的资源文件 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<script type="text/javascript">
		//messager：alert，简单提示框
		//参数1：标题；参数2：提示内容；参数3：提示级别，info、warning、error
		/* $.messager.alert('提示信息','上课不要开小差！','error'); */
		//messager:confirm，确认框
		//参数1：标题；参数2：确认内容；参数3：回调函数，r==true，点击的是确定，r==false，点击取消
		/* $.messager.confirm('确认信息','你确定要上课开小差么？',function(r){
			alert(r);
		}); */
		//messager：show，欢迎框
		window.setTimeout(function(){
			$.messager.show({
				title:'欢迎信息',
				msg:'欢迎某某登录系统！',
				timeout:3000,
				showType:'show'
			});
		}, 3000);//延迟执行函数
	</script>
</body>
</html>
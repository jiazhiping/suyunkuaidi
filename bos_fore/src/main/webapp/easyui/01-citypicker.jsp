<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EasyUI-layout-布局管理器</title>
<!-- 引入easyui资源文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<link href="${pageContext.request.contextPath}/js/citypicker/css/city-picker.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/citypicker/js/city-picker.data.js"></script>
<script src="${pageContext.request.contextPath}/js/citypicker/js/city-picker.js"></script>
</head>
<body>
	<!-- 两种创建方式 -->
	<!-- html方式 -->
	<div style="position: relative;">
		<input type="text" readonly data-toggle="city-picker">
	</div>
	<!-- js方式 -->
	<div style="position: relative;">
		<input id="citypicker1" type="text" readonly />
		<br/>
		<br/>
		<br/>
		<input onclick="doReset();" type="button" value="重置"/>
		<input onclick="doDefault();" type="button" value="默认值"/>
		<script type="text/javascript">
		$(function(){
			$('#citypicker1').citypicker();
		});
		function doReset(){
			$('#citypicker1').citypicker('reset');//重置三级联动框内容
		}
		function doDefault(){
			//注意：citypicker如果设置默认值必须先执行reset和destroy，重新初始化设置默认值
			$('#citypicker1').citypicker('reset');
			$('#citypicker1').citypicker('destroy');
			$('#citypicker1').citypicker({
				province:'北京市',
				city:'北京市',
				district:'东城区'
			});
		}
		</script>
	</div>
</body>
</html>
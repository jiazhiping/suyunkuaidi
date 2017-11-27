<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>待办事项</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/default.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({
			visibility : "visible"
		});

		// 数据表格
		$('#grid')
				.datagrid(
						{
							iconCls : 'icon-forward',
							fit : true,
							border : true,
							striped : true,
							url : "${pageContext.request.contextPath}/daiBanAction_pageQuery.action",
							idField : 'id',
							columns : columns,
							onDblClickRow : doDblClickRow,
							singleSelect : true
						//开启表格单选功能
						});

		// 定义列
		var columns = [ [ {
			field : 'id',
			title : '编号',
			width : 80,
			align : 'center',
			checkbox : true
		}, {
			field : 'priority',
			title : '优先级',
			width : 100,
			align : 'center'
		}, {
			field : 'state',
			title : '状态',
			width : 100,
			align : 'center'
		}, {
			field : 'type',
			title : '单据类型',
			width : 100,
			align : 'center'
		}, {
			field : 'sender',
			title : '发送人',
			width : 100,
			align : 'center'
		}, {
			field : 'sendDateString',
			title : '发送日期',
			width : 100,
			align : 'center'
		} ] ];

	});
</script>

</head>




<body class="easyui-datagrid" style="visibility:hidden;">
	<table id="grid"></table>  
</body>
</html>
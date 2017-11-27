<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>easyui-datagrid-edit</title>
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
	<table id="grid"></table>
	<script type="text/javascript">
	$(function(){
		$('#grid').datagrid({
			url:'${pageContext.request.contextPath}/data/courier.json',
			columns:[[
			          {title:'编号', field:'id',checkbox:true},
			          {title:'姓名', field:'name',editor:{
			        	  type:'validatebox',
			        	  options:{
			        		  required:true
			        	  }
			          }},
			          {title:'电话', field:'telephone',editor:{
			        	  type:'validatebox',
			        	  options:{
			        		  required:true
			        	  }
			          }}
			          ]],
			toolbar:[
			         {text:'增加',iconCls:'icon-add',handler:function(){
			        	 //添加第一空行
			        	 $('#grid').datagrid('insertRow',{
			        			index: 0,	// 索引从0开始
			        			row: {}
			        	 });
			        	 //开启第一行的编辑状态
			        	 $('#grid').datagrid('beginEdit', 0);
			         }},
			         {text:'删除',handler:function(){
			        	 //1.获取要删除的数据集合
			        	 var rows = $('#grid').datagrid('getSelections');
			        	 //2.循环删除选中行
			        	 for(var i=0; i<rows.length; i++){
			        		 //根据行记录获取该行的行号
			        		 var index = $('#grid').datagrid('getRowIndex', rows[i]);
			        		 //根据行号删除行
			        		 $('#grid').datagrid('deleteRow', index);
			        	 }
			         }},
			         {text:'修改',handler:function(){
			        	 //1.获取要修改的数据集合
			        	 var rows = $('#grid').datagrid('getSelections');
			        	 //2.循环打开选中行编辑状态
			        	 for(var i=0; i<rows.length; i++){
			        		 //根据行记录获取该行的行号
			        		 var index = $('#grid').datagrid('getRowIndex', rows[i]);
			        		 //根据行号开启行编辑状态
			        		 $('#grid').datagrid('beginEdit', index);
			        	 }
			         }},
			         {text:'保存',handler:function(){
			        	//1.获取要保存的数据集合
			        	 var rows = $('#grid').datagrid('getSelections');
			        	 //2.循环关闭选中行编辑状态
			        	 for(var i=0; i<rows.length; i++){
			        		 //根据行记录获取该行的行号
			        		 var index = $('#grid').datagrid('getRowIndex', rows[i]);
			        		 //根据行号关闭行编辑状态
			        		 $('#grid').datagrid('endEdit', index);
			        	 }
			         }}
			         ],
			 pagination:true,
			 pageList:[10,20,30],
			 onAfterEdit:function(rowIndex, rowData, changes){
				 alert(rowIndex+'--'+rowData.name+'--'+changes.name);
			 }
		});
	});
	</script>
</body>
</html>
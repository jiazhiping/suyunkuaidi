<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ztree-树</title>
<!-- 引入easyui资源文件 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
<!-- 引入ztree的资源文件 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.all-3.5.js"></script>
</head>
<body class="easyui-layout">
	<div title="XX管理系统" data-options="region:'north'" style="height:100px">北部区域</div>
	<div title="菜单管理" data-options="region:'west'" style="width:200px">
		<!-- 折叠面板：accordion 
			fit:自动填充满父容器
		-->
		<div class="easyui-accordion" fit="true">
			<div title="面板1" data-options="iconCls:'icon-help'">
			<a onclick="doAdd();" class="easyui-linkbutton">动态添加选项卡</a>
			<script type="text/javascript">
				function doAdd(){
					//动态添加选项卡
					$('#tt').tabs('add',{    
					    title:'动态选项卡',    
					    content:'这是一个动态添加的选项卡！',    
					    closable:true,    
					    tools:[{    
					        iconCls:'icon-mini-refresh',    
					        handler:function(){    
					            alert('refresh');    
					        }    
					    }]    
					});
				}
			</script>
			</div>
			<div title="面板2">
			<!-- 1.基于标准json构建ztree（了解） -->
			<!-- 第一步：提供容器：ul标签 -->
			<ul id="ztree1" class="ztree"></ul>
			<script type="text/javascript">
			$(function(){
				//第二步：定义全局变量，setting
				var setting = {};//如果使用的是标准json构建ztree，setting可以是{}
				//第三步：定义节点数据变量
				var nodes = [
				             {name:'系统管理'},
				             {name:'用户管理',children:[
				                                    {name:'用户添加'},
				                                    {name:'用户修改'},
				                                    {name:'用户删除'}
				                                    ]},
				             {name:'权限管理'}
				             ]
				//第四步：初始化树
				$.fn.zTree.init($("#ztree1"), setting, nodes);
			});
			</script>
			</div>
			<div title="面板3">
			<!-- 2.基于简单json构建ztree（掌握） -->
			<!-- 第一步：提供容器：ul标签 -->
			<ul id="ztree2" class="ztree"></ul>
			<script type="text/javascript">
			$(function(){
				//第二步：定义全局变量，setting
				var setting = {
						data:{
							simpleData:{
								enable:true//开启简单json数据功能
							}
						}
				};//如果使用的是标准json构建ztree，setting可以是{}
				//第三步：定义节点数据变量
				var nodes = [
				             {id:'1', pId:'0', name:'系统管理'},
				             {id:'2', pId:'0', name:'用户管理'},
				             {id:'21', pId:'2', name:'用户添加'},
				             {id:'22', pId:'2', name:'用户修改'},
				             {id:'23', pId:'2', name:'用户删除'},
				             {id:'3', pId:'0', name:'权限管理'}
				             ]
				//第四步：初始化树
				$.fn.zTree.init($("#ztree2"), setting, nodes);
			});
			</script>
			</div>
			<div title="面板4">
			<!-- 3.基于ajax请求动态加载树节点数据（掌握） -->
			<!-- 第一步：提供容器：ul标签 -->
			<ul id="ztree3" class="ztree"></ul>
			<script type="text/javascript">
			$(function(){
				//第二步：定义全局变量，setting
				var setting = {
						data:{
							simpleData:{
								enable:true//开启简单json数据功能
							}
						},
						callback:{
							onClick:function(event, treeId, treeNode){
								var page = treeNode.page;
								if(undefined != page){
									//说明是子节点，可以添加选项卡
									//先判断当前要添加的选项卡是否存在
									var b = $('#tt').tabs('exists', treeNode.name);
									if(b){
										//存在，选中
										$('#tt').tabs('select', treeNode.name);
									} else {
										//不存在，添加新的
										$('#tt').tabs('add',{    
										    title:treeNode.name,    
										    content:'<iframe src="${pageContext.request.contextPath}/'+page+'" style="height:100%;width:100%" frameborder="0"></iframe>',    
										    closable:true,    
										    tools:[{    
										        iconCls:'icon-mini-refresh',    
										        handler:function(){    
										            alert('refresh');    
										        }    
										    }]    
										});
									}
								}
							}
						}
				};//如果使用的是标准json构建ztree，setting可以是{}
				//第三步：基于ajax请求加载节点数据
				var url = "${pageContext.request.contextPath}/data/menu.json";
				$.post(url,{},function(data){
					//第四步：初始化树
					$.fn.zTree.init($("#ztree3"), setting, data);
				},'json');
			});
			</script>
			</div>
		</div>
	</div>
	<div data-options="region:'center'">
		<!-- 选项卡面板：tabs 
			fit:自动填充满父容器
		-->
		<div id="tt" class="easyui-tabs" fit="true">
			<div title="面板1" data-options="iconCls:'icon-help',closable:true">1</div>
			<div title="面板2">2</div>
			<div title="面板3">3</div>
			<div title="面板4">4</div>
		</div>
	</div>
	<div data-options="region:'east'" style="width:100px">东部区域</div>
	<div data-options="region:'south'" style="height:100px">南部区域</div>
</body>
</html>
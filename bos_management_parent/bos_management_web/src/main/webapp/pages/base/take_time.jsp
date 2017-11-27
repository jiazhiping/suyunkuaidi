<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>收派时间管理</title>
		<!-- 导入jquery核心类库 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
		<!-- 导入easyui类库 -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/ext/portal.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/ext/jquery.portal.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/ext/jquery.cookie.js"></script>
		<script src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function(){
				// 先将body隐藏，再显示，不会出现页面刷新效果
				$("body").css({visibility:"visible"});
				
				// 收派时间管理信息表格
				$('#grid').datagrid( {
					iconCls : 'icon-forward',
					fit : true,
					border : false,
					rownumbers : true,
					striped : true,
					pageList: [30,50,100],
					pagination : true,
					toolbar : toolbar,
					url : "${pageContext.request.contextPath}/takeTimeAction_pageQuery.action",
					idField : 'id',
					columns : columns
				});
				//添加工作时间窗口
				$('#addWindow').window({
					title: '添加工作时间',
			        width: 800,
			        modal: true,
			        shadow: true,
			        closed: true,
			        height: 400,
			        resizable:false
				});
			});	
			
			//工具栏
			var toolbar = [ {
				id : 'button-add',
				text : '增加',
				iconCls : 'icon-add',
				handler : function(){
					$('#addWindow').window("open");
					
				}
			}, {
				id : 'button-edit',
				text : '修改',
				iconCls : 'icon-edit',
				handler : function(){
					alert('修改');
				}
			},{
				id : 'button-delete',
				text : '删除',
				iconCls : 'icon-cancel',
				handler : function(){
					alert('删除');
				}
			} ];
			
			// 定义列
			var columns = [ [ {
				field : 'id',
				checkbox : true,
			},{
				field : 'name',
				title : '时间名称',
				width : 120,
				align : 'center'
			}, {
				field : 'normalWorkTime',
				title : '平时上班时间',
				width : 120,
				align : 'center'
			}, {
				field : 'normalDutyTime',
				title : '平时休息时间',
				width : 120,
				align : 'center'
			}, {
				field : 'satWorkTime',
				title : '周六上班时间',
				width : 120,
				align : 'center'
			}, {
				field : 'satDutyTime',
				title : '周六休息时间',
				width : 120,
				align : 'center'
			}, {
				field : 'sunWorkTime',
				title : '周日上班时间',
				width : 120,
				align : 'center'
			}, {
				field : 'sunDutyTime',
				title : '周日休息时间',
				width : 120,
				align : 'center'
			}, {
				field : 'status',
				title : '状态',
				width : 120,
				align : 'center'
			}, {
				field : 'company',
				title : '所属单位',
				width : 120,
				align : 'center'
			} , {
				field : 'operator',
				title : '操作人',
				width : 120,
				align : 'center'
			}, {
				field : 'operatingTime',
				title : '操作时间',
				width : 120,
				align : 'center'
			}, {
				field : 'operatingCompany',
				title : '操作单位',
				width : 120,
				align : 'center'
			} ] ];
			
			
		
		
		</script>
	</head>
	<body class="easyui-layout" style="visibility:hidden;">
		<div region="center" border="false">
			<table id="grid"></table>
		</div>
		
		<div class="easyui-window" title="对工作时间进行添加或者修改" id="addWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
			<div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
				<div class="datagrid-toolbar">
					<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
					<script type="text/javascript">
					$(function(){
						$('#save').click(function(){
							var v = $('#addTakeTimeForm').form('validate');
							if(v){
								$('#addTakeTimeForm').submit();
							}
						});
					});
					</script>
				</div>
			</div>

			<div region="center" style="overflow:auto;padding:5px;" border="false">
				<form id="addTakeTimeForm" method="post"
					action="${pageContext.request.contextPath }/takeTimeAction_add.action">
					<table class="table-edit" width="80%" align="center">
						<tr class="title">
							<td colspan="4">工作时间信息</td>
						</tr>
						<tr>
							<td>时间名称</td>
							<td>
								<input type="text" name="name" class="easyui-validatebox" required="true" />
							</td>
							<td>平时上班时间</td>
							<td>
								<input type="text" name="normalWorkTime" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>平常下班时间</td>
							<td>
								<input type="text" name="normalDutyTime" class="easyui-validatebox" required="true" />
							</td>
							<td>周六上班时间</td>
							<td>
								<input type="text" name="satWorkTime" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>周六下班时间</td>
							<td>
								<input type="text" name="satDutyTime" class="easyui-validatebox" required="true" />
							</td>
							<td>周日上班时间</td>
							<td>
								<input type="text" name="sunWorkTime" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>周日下班时间</td>
							<td>
								<input type="text" name="sunDutyTime" class="easyui-validatebox" required="true" />
							</td>
							<td>状态</td>
							<td>
								<input type="text" name="status" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>所属公司</td>
							<td>
								<input type="text" name="company" class="easyui-validatebox" required="true" />
							</td>
							<td>操作时间</td>
							<td>
								<input type="text" name="operatingTime" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>操作员</td>
							<td>
								<input type="text" name="operator" class="easyui-validatebox" required="true" />
							</td>
							<td>操作单位</td>
							<td>
								<input type="text" name="operatingCompany" class="easyui-validatebox" required="true" />
							</td>
						</tr>	
					</table>
				</form>
			</div>
		</div>
		
		
	</body>
</html>

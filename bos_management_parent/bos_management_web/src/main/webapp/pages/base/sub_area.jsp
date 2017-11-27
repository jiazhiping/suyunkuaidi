<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>管理分区</title>
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
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts/highcharts.js"></script>
		<script type="text/javascript">
			function doAdd(){
				$('#addWindow').window("open");
			}
			
			function doEdit(){
			//1.获取客户选中的数据
				var rows = $('#grid').datagrid('getSelections');
				if(rows.length != 1){
					//2.如果客户未选中或这选中了多条，提示客户只能选择一条
					$.messager.alert('提示信息','请您选择一条数据！','warning');
				} else {
					//3.如果客户选中了一条，打开窗口
					$('#editWindow').window('open');
					//4.回显数据
					$('#subareaForm1').form('load', rows[0]);
			 		$('#areaId').combobox('select',rows[0].area.id);
				}
			}
				
				function doDelete(){
					var rows = $('#grid').datagrid('getSelections');
					if(rows.length <= 0){
						//2.判断是否选中，如果未选中，提示客户选择数据
						$.messager.alert('提示信息','请您选择要要删除的数据！','warning');
					} else {
						//3.如果选中，让客户确认是否删除，
						$.messager.confirm('确认信息','您确定要删除这些数据么？',function(r){
							if(r){
								//4.当客户确定删除时，获取要删除数据的id，通过请求发送到后台
								var idsArr = new Array();
								for(var i=0; i<rows.length; i++){
									idsArr.push(rows[i].id);
								}
								var ids = idsArr.join(',');//将数据中的数据拼接成字符串，例如：12,34
								//5.通过请求发送到后台
								window.location.href="${pageContext.request.contextPath}/subArea_delete.action?ids="+ids;
							}
						});
					}
			}
			
			
			function doSearch(){
				$('#searchWindow').window("open");
			}
			
			//导出按钮绑定的事件
			function doExport(){
				//1.获取客户选中的数据
				var rows = $('#grid').datagrid("getSelections");
				if(rows.length > 0){
					//2.选中数据，拼接成字符串
					var subareaIdArrr = new Array();
					for(var i=0;i<rows.length;i++){
						subareaIdArrr.push(rows[i].id);
					}
					var ids = subareaIdArrr.join(',');
					//3.通过请求将数据发送到后台
					//ajax请求不支持文件下载
					//没有表单，所以不能提交表单
					location.href="${pageContext.request.contextPath}/subareaAction_exportXls.action?ids="+ids;
				} else {
					//4.未选中，直接查询所有数据
					location.href="${pageContext.request.contextPath}/subareaAction_exportXls.action"
				}
			}
			
			function doImport(){
				alert("导入");
			}
			
			function doShowChart(){
				//1.打开分区分布图窗口
				$('#showChartsWindow').window('open');
				//2.发送ajax请求，查询区域分区分布图数据，转换成json数组，返回到界面，显示成饼状图
				var url = "${pageContext.request.contextPath}/subareaAction_findGroupedSubareas.action";
				$.post(url,{},function(data){
					//初始化highcharts饼状图
					$('#container').highcharts({
						chart:{
							type:'pie',//设置饼状图
							height:300,
							width:600
						},
						series:[
						        {
						        	data:data
						        }
						        ]
					});
				},'json');
			}
			//工具栏
			var toolbar = [ {
				id : 'button-search',	
				text : '查询',
				iconCls : 'icon-search',
				handler : doSearch
			}, {
				id : 'button-add',
				text : '增加',
				iconCls : 'icon-add',
				handler : doAdd
			}, {
				id : 'button-edit',	
				text : '修改',
				iconCls : 'icon-edit',
				handler : doEdit
			},{
				id : 'button-delete',
				text : '删除',
				iconCls : 'icon-cancel',
				handler : doDelete
			},{
				id : 'button-import',
				text : '导入',
				iconCls : 'icon-redo',
				handler : doImport
			},{
				id : 'button-export',
				text : '导出',
				iconCls : 'icon-undo',
				handler : doExport
			},{
				id : 'button-showChart',
				text : '分区分布图',
				iconCls : 'icon-search',
				handler : doShowChart
			}];
			// 定义列
			var columns = [ [ {
				field : 'id',
				checkbox : true,
			}, {
				field : 'showid',
				title : '编号',
				width : 120,
				align : 'center',
				formatter : function(data,row ,index){
					return row.id;
				}
			},{
				field : 'area.province',
				title : '省',
				width : 120,
				align : 'center',
				formatter : function(data,row ,index){
					if(row.area != null ){
						return row.area.province;
					}
					return "" ;
				}
			}, {
				field : 'area.city',
				title : '市',
				width : 120,
				align : 'center',
				formatter : function(data,row ,index){
					if(row.area != null ){
						return row.area.city;
					}
					return "" ;
				}
			}, {
				field : 'area.district',
				title : '区',
				width : 120,
				align : 'center',
				formatter : function(data,row ,index){
					if(row.area != null ){
						return row.area.district;
					}
					return "" ;
				}
			}, {
				field : 'keyWords',
				title : '关键字',
				width : 120,
				align : 'center'
			}, {
				field : 'startNum',
				title : '起始号',
				width : 100,
				align : 'center'
			}, {
				field : 'endNum',
				title : '终止号',
				width : 100,
				align : 'center'
			} , {
				field : 'assistKeyWords',
				title : '辅助关键字',
				width : 100,
				align : 'center'
			} ] ];
			
			$(function(){
				// 先将body隐藏，再显示，不会出现页面刷新效果
				$("body").css({visibility:"visible"});
				
				// 分区管理数据表格
				$('#grid').datagrid( {
					iconCls : 'icon-forward',
					fit : true,
					border : true,
					rownumbers : true,
					striped : true,
					pageList: [30,50,100],
					pagination : true,
					toolbar : toolbar,
					url : "${pageContext.request.contextPath}/subareaAction_pageQuery.action",
					idField : 'id',
					columns : columns,
					onDblClickRow : doDblClickRow
				});
				
				// 添加、修改分区
				$('#addWindow').window({
			        title: '添加修改分区',
			        width: 600,
			        modal: true,
			        shadow: true,
			        closed: true,
			        height: 400,
			        resizable:false
			    });
				$('#editWindow').window({
			        title: '添加修改分区',
			        width: 600,
			        modal: true,
			        shadow: true,
			        closed: true,
			        height: 400,
			        resizable:false
			    });
				
				$('#showChartsWindow').window({
			        width: 700,
			        modal: true,
			        shadow: true,
			        closed: true,
			        height: 400,
			        resizable:false
			    });
				
				// 查询分区
				$('#searchWindow').window({
			        title: '查询分区',
			        width: 400,
			        modal: true,
			        shadow: true,
			        closed: true,
			        height: 400,
			        resizable:false
			    });
				$("#btn").click(function(){
					alert("执行查询...");
				});
			});
		
			function doDblClickRow(){
			/* 	//1.获取客户选中的数据
				var rows = $('#grid').datagrid("getSelections");
				alert(rows);
				var ids = rows.id;
			 	//3.通过请求将数据发送到后台
				//ajax请求不支持文件下载
				//没有表单，所以不能提交表单
				location.href="${pageContext.request.contextPath}/subareaAction_findByID.action?ids="+ids;
				  */
				alert("双击查询...");
			}
		</script>
	</head>

	<body class="easyui-layout" style="visibility:hidden;">
		<div region="center" border="false">
			<table id="grid"></table>
		</div>
		<!-- 添加 修改分区 -->
		<div class="easyui-window" title="分区添加修改" id="addWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
			<div style="height:31px;overflow:hidden;" split="false" border="false">
				<div class="datagrid-toolbar">
					<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
					<script type="text/javascript">
					$(function(){
						$('#save').click(function(){
							var v = $('#subareaForm').form('validate');
							if(v){
								$('#subareaForm').submit();
							}
						});
					});
					</script>
				</div>
			</div>

			<div style="overflow:auto;padding:5px;" border="false">
				<form id="subareaForm" method="post" action="${pageContext.request.contextPath}/subareaAction_save.action">
					<table class="table-edit" width="80%" align="center">
						<tr class="title">
							<td colspan="2">分区信息</td>
						</tr>
						<tr>
							<td>分区编号</td>
							<td>
								<input  class="easyui-validatebox" name="id"
									 data-options="required:true"  />
							</td>
						</tr>
						<tr>
							<td>选择区域</td>
							<td>
								<input class="easyui-combobox" name="area.id"
									 data-options="valueField:'id',textField:'name',
									 url:'${pageContext.request.contextPath}/areaAction_findAll.action',editable:false" />
							</td>
						</tr>
						<tr>
							<td>关键字</td>
							<td>
								<input type="text" name="keyWords" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>辅助关键字</td>
							<td>
								<input type="text" name="assistKeyWords" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>起始号</td>
							<td>
								<input type="text" name="startNum" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>终止号</td>
							<td>
								<input type="text" name="endNum" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>位置信息</td>
							<td>
								<input type="text" name="address" class="easyui-validatebox" required="true" style="width:250px;" />
							</td>
							
						</tr>
					</table>
				</form>
			</div>
		</div>
			<!-- 查询分区 -->
		<div class="easyui-window" title="查询分区窗口" id="searchWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
			<div style="overflow:auto;padding:5px;" border="false">
				<form>
					<table class="table-edit" width="80%" align="center">
						<tr class="title">
							<td colspan="2">查询条件</td>
						</tr>
						<tr>
							<td>省</td>
							<td>
								<input type="text" name="province" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>市</td>
							<td>
								<input type="text" name="city" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>区（县）</td>
							<td>
								<input type="text" name="district" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>定区编码</td>
							<td>
								<input type="text" name="decidedzone.id" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>关键字</td>
							<td>
								<input type="text" name="addresskey" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td colspan="2"><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> </td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		
		<!-- 查询分区 -->
		<div class="easyui-window" title="查询分区窗口" id="showChartsWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
			<div id="container"></div>
		</div>
			<!-- 添加 修改分区 -->
		<div class="easyui-window" title="分区添加修改" id="editWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
			<div style="height:31px;overflow:hidden;" split="false" border="false">
				<div class="datagrid-toolbar">
					<a id="edit" icon="icon-edit" href="#" class="easyui-linkbutton" plain="true">保存</a>
					<script type="text/javascript">
					$(function(){
						$('#edit').click(function(){
							//1.校验数据是否合法
							var v = $('#subareaForm1').form('validate');
							if(v){
								//2.如果合法，提交表单
								$('#subareaForm1').submit();
							}
						});
					});
					</script>
				</div>
			</div>

			<div style="overflow:auto;padding:5px;" border="false">
				<form id="subareaForm1" method="post" action="${pageContext.request.contextPath}/subareaAction_save.action">
					<table class="table-edit" width="80%" align="center">
						<tr class="title">
							<td colspan="2">分区信息</td>
						</tr>
						  <tr>
							<!--  <td>分区编号</td>  -->
							<td>
								<input  type="hidden" class="easyui-validatebox" name="id"
									 data-options="required:true" />
							</td>
						</tr>  
						<tr>
							<td>选择区域</td>
							<td>
								<input class="easyui-combobox" name="area.id" id="areaId"
									 data-options="valueField:'id',textField:'name',
									 url:'${pageContext.request.contextPath}/areaAction_findAll.action',editable:false" />
							</td>
						</tr>
						<tr>
							<td>关键字</td>
							<td>
								<input type="text" name="keyWords" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>辅助关键字</td>
							<td>
								<input type="text" name="assistKeyWords" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>起始号</td>
							<td>
								<input type="text" name="startNum" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>终止号</td>
							<td>
								<input type="text" name="endNum" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>位置信息</td>
							<td>
								<input type="text" name="address" class="easyui-validatebox" required="true" style="width:250px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>


</html>
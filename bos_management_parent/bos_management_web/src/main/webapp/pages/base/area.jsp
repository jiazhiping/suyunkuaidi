
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>区域设置</title>
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
<script
	src="${pageContext.request.contextPath}/js/jquery.ocupload-1.1.2.js"
	type="text/javascript"></script>
<script type="text/javascript">
	function doAdd() {
		$('#addWindow').window("open");

	}

	function doView() {
		var rows = $('#grid').datagrid('getSelections');
		if(rows.length==1){
			$('#editWindow').window("open");
			$('#editForm').form('load',rows[0]);
			
		}else{
			$.messager.alert('提示信息','请选择一条数据','info')
		
			
		}

	}

	function doDelete() {
		alert("删除...");
	}

	//工具栏
	var toolbar = [
			{
				id : 'button-edit',
				text : '修改',
				iconCls : 'icon-edit',
				handler : doView
			},
			{
				id : 'button-add',
				text : '增加',
				iconCls : 'icon-add',
				handler : doAdd
			},
			{
				id : 'button-delete',
				text : '删除',
				iconCls : 'icon-cancel',
				handler : doDelete
			},
			{
				id : 'button-import',
				text : '导入',
				iconCls : 'icon-redo'
			},
			{
				id : 'button-import',
				text : '导出',
				iconCls : 'icon-redo',
				handler : function() {
					//1.发送导出请求，查询所有区域数据，放到pdf文件中，返回到界面导出
					location.href = "${pageContext.request.contextPath}/areaAction_exportPDF.action";
				}
			}, {
				id : 'button-search',
				text : '查询',
				iconCls : 'icon-search',
				handler : function() {
					//打开查询窗口
					$('#searchWindow').window('open');
				}
			} ];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	}, {
		field : 'province',
		title : '省',
		width : 120,
		align : 'center'
	}, {
		field : 'city',
		title : '市',
		width : 120,
		align : 'center'
	}, {
		field : 'district',
		title : '区',
		width : 120,
		align : 'center'
	}, {
		field : 'postcode',
		title : '邮编',
		width : 120,
		align : 'center'
	}, {
		field : 'shortcode',
		title : '简码',
		width : 120,
		align : 'center'
	}, {
		field : 'citycode',
		title : '城市编码',
		width : 200,
		align : 'center'
	} ] ];

	$(function() {
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({
			visibility : "visible"
		});

		// 区域管理数据表格
		$('#grid')
				.datagrid(
						{
							iconCls : 'icon-forward',
							fit : true,
							border : false,
							rownumbers : true,
							striped : true,
							pageList : [ 30, 50, 100 ],
							pagination : true,
							toolbar : toolbar,
							url : "${pageContext.request.contextPath}/areaAction_pageQuery.action",
							idField : 'id',
							columns : columns,
							onDblClickRow : doDblClickRow
						});

		// 添加、修改区域窗口
		$('#addWindow').window({
			title : '添加区域',
			width : 400,
			modal : true,
			shadow : true,
			closed : true,
			height : 400,
			resizable : false
		});
		
		$('#editWindow').window({
			title : '修改区域',
			width : 400,
			modal : true,
			shadow : true,
			closed : true,
			height : 400,
			resizable : false
		});

		$('#button-import')
				.upload(
						{
							action : '${pageContext.request.contextPath}/areaAction_importXls.action',
							name : 'areaFile',
							onComplete : function(data) {
								if ("1" == data) {
									//成功，提示成功
									$.messager.alert('提示信息', '上传成功！', 'info');
								} else {
									//失败，提示失败
									$.messager.alert('提示信息', '上传失败！', 'error');
								}
							}
						});
	});

	function doDblClickRow() {
		alert("双击表格数据...");
	}
</script>
</head>

<body class="easyui-layout" style="visibility: hidden;">
	<div region="center" border="false">
		<table id="grid"></table>
	</div>
	<!-- 区域添加 -->
	<div class="easyui-window" title="区域添加" id="addWindow"
		collapsible="false" minimizable="false" maximizable="false"
		style="top: 20px; left: 200px">
		<div region="north" style="height: 31px; overflow: hidden;"
			split="false" border="false">
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton"
					plain="true">保存</a>
			</div>
		</div>
		<script type="text/javascript">
			$(function() {
				// 点击保存
				$('#save').click(function() {
					//1.校验表单
					var v = $('#areaForm').form('validate');
					//2.校验通过，提交表单
					if (v) {
						$('#areaForm').submit();
					}
				});
			});
		</script>
		<div region="center" style="overflow: auto; padding: 5px;"
			border="false">
			<form id="areaForm" method="post"
				action="${pageContext.request.contextPath}/areaAction_add.action">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">区域信息</td>
					</tr>
					<tr>
						<td>省</td>
						<td><input type="text" name="province"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>市</td>
						<td><input type="text" name="city" class="easyui-validatebox"
							required="true" /></td>
					</tr>
					<tr>
						<td>区</td>
						<td><input type="text" name="district"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>邮编</td>
						<td><input type="text" name="postcode"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>简码</td>
						<td><input type="text" name="shortcode"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>城市编码</td>
						<td><input type="text" name="citycode"
							class="easyui-validatebox" required="true" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- 区域修改 -->
	<div class="easyui-window" title="区域修改" id="editWindow"
		collapsible="false" minimizable="false" maximizable="false"
		style="top: 20px; left: 200px">
		<div region="north" style="height: 31px; overflow: hidden;"
			split="false" border="false">
			<div class="datagrid-toolbar">
				<a id="edit" icon="icon-save" href="#" class="easyui-linkbutton"
					plain="true">保存</a>
			</div>
		</div>
		<script type="text/javascript">
			$(function() {
				// 点击保存
				$('#edit').click(function() {
					//1.校验表单
					var v = $('#editForm').form('validate');
					//2.校验通过，提交表单
					if (v) {
						$('#editForm').submit();
					}
				});
			});
		</script>
		<div region="center" style="overflow: auto; padding: 5px;"
			border="false">
			<form id="editForm" method="post"
				action="${pageContext.request.contextPath}/areaAction_edit.action">
				<input type="hidden" name="id">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">区域信息</td>
					</tr>
					<tr>
						<td>省</td>
						<td><input type="text" name="province"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>市</td>
						<td><input type="text" name="city" class="easyui-validatebox"
							required="true" /></td>
					</tr>
					<tr>
						<td>区</td>
						<td><input type="text" name="district"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>邮编</td>
						<td><input type="text" name="postcode"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>简码</td>
						<td><input type="text" name="shortcode"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>城市编码</td>
						<td><input type="text" name="citycode"
							class="easyui-validatebox" required="true" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<!-- 查询区域-->
	<div class="easyui-window" title="查询区域窗口" closed="true"
		id="searchWindow" collapsible="false" minimizable="false"
		maximizable="false" style="width: 400px; top: 40px; left: 200px">
		<div style="overflow: auto; padding: 5px;" border="false">
			<form id="searchForm">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">查询条件</td>
					</tr>
					<tr>
						<td>省份</td>
						<td><input type="text" name="province" /></td>
					</tr>
					<tr>
						<td>城市</td>
						<td><input type="text" name="city" /></td>
					</tr>
					<tr>
						<td>区（县）</td>
						<td><input type="text" name="district" /></td>
					</tr>
					<tr>
						<td colspan="2"><a id="searchBtn" href="#"
							class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
							<script type="text/javascript">
								$(function() {
									//给表单绑定序列化函数
									$.fn.serializeJson = function() {
										var serializeObj = {};
										var array = this.serializeArray();
										var str = this.serialize();
										$(array)
												.each(
														function() {
															if (serializeObj[this.name]) {
																if ($
																		.isArray(serializeObj[this.name])) {
																	serializeObj[this.name]
																			.push(this.value);
																} else {
																	serializeObj[this.name] = [
																			serializeObj[this.name],
																			this.value ];
																}
															} else {
																serializeObj[this.name] = this.value;
															}
														});
										return serializeObj;
									};
									$('#searchBtn').click(
											function() {
												var data = $('#searchForm')
														.serializeJson();
												$('#grid').datagrid('load',
														data);
												$('#searchWindow').window(
														'close');
											});
								});
							</script></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>

</html>
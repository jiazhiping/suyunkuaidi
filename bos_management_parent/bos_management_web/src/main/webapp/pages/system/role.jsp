<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>角色列表页面</title>
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
		<!-- 导入ztree类库 -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/js/ztree/zTreeStyle.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.all-3.5.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function(){
				// 数据表格属性
				$("#grid").datagrid({
					toolbar : [
						{
							id : 'add',
							text : '添加角色',
							iconCls : 'icon-add',
							handler : function(){
								location.href='role_add.jsp';
							}
						},
						{
							id : 'edit',
							text : '修改角色',
							iconCls : 'icon-edit',
							handler : function(){
								location.href='';
							}
						}
					],
					url : '${pageContext.request.contextPath}/roleAction_pageQuery.action',
					columns : [[
						{
							field : 'id',
							title : '编号',
							width : 200
						},
						{
							field : 'name',
							title : '名称',
							width : 200
						}, 
						{
							field : 'keyword',
							title : '关键字',
							width : 200
						}, 
						{
							field : 'description',
							title : '描述',
							width : 200
						} 
					]],
					onDblClickRow: onDblClickRow,
					pagination:true,
					fit:true
				});
				
				
				//修改角色窗口   -- 设置页面加载后编辑窗口不显示
				$('#editWindow').window({
			        title: '修改角色',
			        width: 800,
			        modal: true,
			        shadow: true,
			        closed: true,
			        height: 400,
			        resizable:false
			    });
				
				
				
				//var nodes = null;
				//2.设置菜单树的全局变量
				var setting = {
					data : {
						key : {
							title : "t"
						},
						simpleData : {			//开启简单数据
							enable : true,
						}
					},
					check : {					//开启菜单复选框
						enable : true,
						 
					 	chkboxType: {"Y" : "p", "N" : "s"} //勾选时影响子节点，取消勾选时影响父子节点
					}
				};	
				

				//双击行事件
				function onDblClickRow(rowIndex, rowData){
					
					//权限复选框置空
					$('#permissionTd').html("");
					
					//打开修改窗口
					$('#editWindow').window("open");
					
					//双击行普通数据回显
					$("#editRoleForm").form("load",rowData);
					
					

					/*权限复选框            --start */
					
					
					//1.权限复选框: 页面加载完成后,发送ajax请求,查询所有的权限数据,将数据转化为json,在页面中展示为复选框形式
					
					//查询全部权限数据的ajax URL
					var url =  '${pageContext.request.contextPath}/permissionAction_findAll.action';
					
					//根据角色id查询对应权限数据的ajax URL
					var urlRoleId = "${pageContext.request.contextPath}/permissionAction_findPermissionByRoleId.action"
					
					$.post(url,{},function(data){
						
						//根据角色id查询查询对应权限ajax
						$.post(urlRoleId,{"id" : rowData.id},function(showPermissionData){
							
							//1.查询全部权限的回调数据遍历
							for(var i = 0;i<data.length;i++){
								
									//2.获取权限id
									var id = data[i].id;
									//alert(id);
									//3.获取权限名称name
									var name = data[i].name;
							
									//4.获取权限多选框所在的位置,拼装权限的多选框选项
									$('#permissionTd').append('<input type="checkbox" name="permissionIds" value="'+id+'" /> '+name);
							
							
									//2.通过roleId查询权限的回调数据遍历
									for(var j = 0;j<showPermissionData.length;j++){
										
										var pId= showPermissionData[j].id;
										
										//alert("pId  :"+pId);
										
										if(pId==id){
											
											$('input:checkbox').eq(i).attr("checked",'true');
											
											continue;//结束本层本次循环
										}
									}
							}
							
						},'json');
						
					},'json');
			
					/*权限复选框            --end */
					
					
				/*回显菜单树数据             --start*/
					
					
					
					//根据角色id 使用ajax远程加载角色关联的json菜单数据
					$.post("${pageContext.request.contextPath}/menuAction_findMenuByRoleId.action", {
						"id" : rowData.id
					}, function(data) {
						//alert(data);
						treeNode = data;
					},'json');	
						
					
					//3.ajax动态查取菜单树全部节点数据(ztreeNodes)即:data
					$.ajax({
						url : '${pageContext.request.contextPath}/menuAction_findAll.action',
						type : 'POST',
						dataType : 'json',
						success : function(data) {
							
							//4.初始化ztree
							$.fn.zTree.init($("#menuTree"), setting, data);
							
							//当角色关联的菜单数据长度大于0时,遍历角色关联的菜单数据
							if (treeNode.length > 0) {
								
								//获取ztree对象
								var treeObj = $.fn.zTree.getZTreeObj("menuTree");
								
								//遍历勾选角色关联的菜单数据
								for (var i = 0; i < treeNode.length; i++) {
									
									//根据角色菜单节点数据的属性搜索，获取与完整菜单树完全匹配的节点JSON对象集合
									var nodes = treeObj.getNodesByParam("id", treeNode[i].id, null);
										
									//勾选当前选中的节点
									treeObj.checkNode(nodes[0],true,true);
									
								};
							};
						},
						error : function(msg) {
							alert('树加载异常!');
						}
					});
			
					/*回显菜单树             --end*/
		
				}
				
				
				// 点击更新
				$('#updateRole').click(function(){

					if($("#editRoleForm").form("validate")){
						var treeObj = $.fn.zTree.getZTreeObj("menuTree");//获取菜单树对象.
						var nodes = treeObj.getCheckedNodes(true);//在提交表单之前将选中的checkbox收集
						var array = new Array();
						for(var i=0;i<nodes.length;i++){
							array.push(nodes[i].id);
						}
						var menuIds = array.join(",");
						$("input[name=menuIds]").val(menuIds);//将获取的菜单选项赋值给隐藏域,从而将数据传到后台
						$("#editRoleForm").submit();
					}
				});
				
			});
		</script>
	</head>

	<body class="easyui-layout">
		<div data-options="region:'center'">
			<table id="grid"></table>
		</div>
		
		<!--修改角色信息    start-->		
		
		<div class="easyui-window" title="对角色进行修改" id="editWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
			<div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
				<div class="datagrid-toolbar">
					<a id="updateRole" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">更新</a>
				</div>
			</div>

			<div region="center" style="overflow:auto;padding:5px;" border="false">
				<form id="editRoleForm" action="${pageContext.request.contextPath}/roleAction_add.action" method="post">
					<table class="table-edit" width="80%" align="center">
						<tr class="title">
							<td colspan="2">角色信息
							<!-- 设置隐藏域,从而将选中菜单数据传到后台 -->
							<input type="hidden" name="menuIds">
							<input type="hidden" name="id">
							</td>
						</tr>
						<tr>
							<td>名称</td>
							<td>
								<input type="text" name="name" class="easyui-validatebox" data-options="required:true" />
							</td>
						</tr>
						<tr>
							<td>关键字</td>
							<td>
								<input type="text" name="keyword" class="easyui-validatebox" data-options="required:true" />
							</td>
						</tr>
						<tr>
							<td>描述</td>
							<td>
								<textarea name="description" rows="4" cols="60"></textarea>
							</td>
						</tr>
						<tr>
							<td>权限选择</td>
							<td id="permissionTd">
								 
							</td>
						</tr>
						<tr>
							<td>菜单授权</td>
							<td><!-- 1.定义ztree树标签 -->
								<ul id="menuTree" class="ztree"></ul>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>		
		
<!--修改快递员信息    end -->		
	</body>
	

</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>实现文件上传功能</title>
<script type="text/javascript" href="./Content/jquery-easyui-1.7.6/jquery.easyui.min.js"></script>

<link rel="stylesheet" type="text/css" href="./Content/jquery-easyui-1.7.6/themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="./Content/jquery-easyui-1.7.6/themes/icon.css">   
<script type="text/javascript" src="./Content/jquery-easyui-1.7.6/jquery.min.js"></script>   
<script type="text/javascript" src="./Content/jquery-easyui-1.7.6/jquery.easyui.min.js"></script>  

</head>
<body>
	<h1>文件上传</h1>
	<div>
		<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" id="username" name="username"></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" id="password" name="password"></td>
			</tr>
			<tr>
				<td>用户名：</td>
				<td><input type="text" id="username" name="username"></td>
			</tr>
		</table>
	</div>
	<div class="easyui-dialog" style="width:400px;height:200px"
		data-options="title:'My Dialog',collapsible:true,iconCls:'icon-ok',onOpen:function(){}">
		dialog content.
	</div>
	<div id="pp" class="easyui-pagination" data-options="total:2000,pageSize:10" style="background:#efefef;border:1px solid #ccc;"></div>  
</body>
</html>
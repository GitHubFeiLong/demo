<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>实现文件上传功能</title>
</head>
<script type="text/javascript" src="./Content/jquery-3.4.0.js"></script>   
<script type="text/javascript" src="./Content/jquery-easyui-1.7.6/jquery.min.js"></script>   
<script type="text/javascript" src="./Content/jquery-easyui-1.7.6/jquery.easyui.min.js"></script>  
<script type="text/javascript" href="./Content/jquery-easyui-1.7.6/jquery.easyui.min.js"></script>  
<link rel="stylesheet" type="text/css" href="./Content/jquery-easyui-1.7.6/themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="./Content/jquery-easyui-1.7.6/themes/icon.css">
<body>
	<div>
		<jsp:include page="./head.jsp"></jsp:include>
	</div>
	<div>
		<h2>文件上传</h2>
    <form action="upload.do" enctype="multipart/form-data" method="post">
        <table>
            <tr>
                <td>文件描述:</td>
                <td><input type="text" name="description"></td>
            </tr>
            <tr>
                <td>请选择文件:</td>
                <td><input type="file" name="file"></td>
            </tr>
            <tr>
                <td><input type="submit" value="上传"></td>
            </tr>
        </table>
    </form>
	</div>
	
</body>
<script type="text/javascript">
      $(window).ready(function(){/* 
    	  alert("${pageContext.request.contextPath}"); */
    	 
      })
   </script>
</html>
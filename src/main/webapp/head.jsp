<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<div class="timebox">
        	当前时间：<span class="time"></span>
    </div>
	<div class="topbox">
		<div class="wrap wrap1">
			<div id="div_select_1" class="btnbox" onclick="window.location.href='${APP_PATH}/dy.do'">数 据</div>
			<div id="div_select_2" class="btnbox" onclick="window.location.href='${APP_PATH}/getWadeRight.do'">涉 权</div>
			<div id="div_select_3" class="btnbox" onclick="window.location.href='${APP_PATH}/superviseCount.do'">督 办</div>
		</div>
		<div class="wrap">
			<div id="div_select_4" class="btnbox" onclick='window.location.href="search.jsp"'>查 询</div>
			<div class="btnbox kbj"></div>
			<div class="btnbox kbj"></div>
		</div>
	</div>
	
 <form  id="myForm" name="myForm" method="POST">
        <input type="hidden" id="IDNumber" name="IDNumber">
        <input type="hidden" id="cdid" name="cdid">
 </form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html lang="en">

<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <meta http-equiv="X-UA-Compatible" content="ie=edge">
   <title>Document</title>
   <link rel="stylesheet" href="Content/css/easyui.css">
   <link rel="stylesheet" href="Content/css/icon.css">
   <script src="Content/js/jquery.min.js"></script>
   <script src="Content/js/jquery.easyui.min.js"></script>
   <script src="Content/js/easyui-lang-zh_CN.js"></script>
   <style>
      
   </style>
</head>

<body>
   <div class="head">
      <div>项目信息查询汇总表</div>
      <div class="btn">
         <button onclick="history.back()">返回</button>
      </div>
   </div>
   <div id="table"></div>
   <!-- 新建一个form将请求得到的参数传递到下一个页面 -->
   
   <script>
     $.ajax({
    	 url:"/ssm/getPage.do",
    	 /* data:"", */
    	 type:"post",
    	 dataType:"json",
    	 success:function(data){
    		 console.log(data);
    	 }
    	 
     });
      $("#table").datagrid({
         url: '/ssm/getPage.do',//完整格式为{total:35,rows:[{a:1,b:2,c:3},{a:1,b:2,c:3},{a:1,b:2,c:3}],footer:[{'a':'统计',b:'',c:''}]},,,total为总共多少条数据，rows为表格要显示的数组对象，footer为行尾，默认不显示，如统计合计
         width:"100%",
         height: 500,//高度
         pagination: true,//显示分页组件,
         pageSize: 50,//一页显示多少条，排序，post返回给后台 page:'1',rows:'10'
         showFooter: true,//显示行尾
         pageList: [10, 20, 50,100,200,500,1000,2000,5000],//下拉菜单选择一页显示多少条，，同pageSize相关联
         fitColumns: true,//为true时table自适应，和columns里的单元格宽度配合使用
         scrollbarSize: 0,//隐藏表格滚动条位置，必须同fitColumns:true一起使用
         columns: [[
            {
               field: 'id',
               title: '序号',
               width: 30,
               halign: 'center',
               align: 'center',
                formatter: function (value, row, index) {
                  //value,该字段的值，row所有字段，index下标
                
                  if(row.footer){
                     return "合计";        
                  }else{
                     return index+1
                  }
               },
            },
            {
                field: 'name',
                title: '单位id',
                width: 110,
                halign: 'center',
             },
             {
                 field: 'age',
                 title: '项目id',
                 width: 110,
                 halign: 'center',
              },
         ]],
      })
      
   </script>
</body>
</html>
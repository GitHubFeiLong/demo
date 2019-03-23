<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>涉权</title>
<script src="Content/js/jquery-1.12.3.min.js"></script>
<script src="Content/js/echarts.js"></script>
<!-- 新建的js函数 -->
<script src="Content/js/publicTools.js"></script>

<style>
* {
	margin: 0;
	padding: 0;
}

.kbj {
	visibility: hidden;
}

body {
	position: relative;
	width: 100%;
	height: 100vh;
	min-width: 800px;
	min-height: 300px;
	background: url('Content/img/bg.png') no-repeat 0 0/100% 100%;
	background-attachment: fixed;
}

img.title {
	position: absolute;
	width: 100%;
}

.timebox {
	position: absolute;
	top: 1em;
	right: 2em;
	color: #fff;
}

.topbox {
	position: relative;
	top: 3em;
	display: flex;
	justify-content: space-between;
}

.topbox .wrap {
	width: 25%;
	display: flex;
	justify-content: space-around;
}

.topbox .btnbox {
	float: left;
	font-size: 1.2em;
	width: 5em;
	user-select: none;
	cursor: pointer;
	text-align: center;
	color: #364b64;
	font-weight: bolder;
	line-height: 2em;
	background: url('Content/img/topbtn.png') no-repeat 0 0/100% 100%;
}

.topbox .btnbox.selected {
	color: #55e9f8;
}

.bodybox {
	display: flex;
	justify-content: space-between;
	position: relative;
	top: 5em;
	width: 100%;
	height: calc(100% - 8em);
}

.bodybox .leftbody {
	position: relative;
	float: left;
	width: 65%;
}

#cqmap {
	width: 100%;
	height: 100%;
}

.mingpai {
	position: absolute;
	min-width: 12em;
	height: 5em;
	top: 0;
	left: 2em;
	background: url('Content/img/mingpai.png') no-repeat 0 0/100% 100%;
}

.mingpai .title {
	font-size: 20px;
	color: #55e9f8;
	letter-spacing: 10px;
	padding-left: 10px;
}

.mingpai .shuzi {
	font-size: 10px;
	padding-right: 10px;
	margin-top: 10px;
	color: #fff;
	text-align: right;
}

.mingpai .shuzi span {
	font-size: 25px;
	background: #1d8eed;
	margin-right: 5px;
	padding: 0 3px;
}

.bodybox .rightbody {
	float: left;
	width: 30%;
}

.rightbody ul {
	display: flex;
	justify-content: space-between;
	flex-direction: column;
	width: 100%;
	height: 100%;
}

.rightbody ul li {
	display: flex;
	justify-content: space-between;
	background: url('Content/img/rightbtnde.png') no-repeat 0 0/100% 100%;
	box-sizing: border-box;
	width: 100%;
	height: 5em;
	line-height: 3em;
	padding: 0 4em;
	color: #55e9f8;
	user-select: none;
	cursor: pointer;
}

.rightbody ul li:last-child {
	padding-right: 3em;
}

.rightbody ul li.selected {
	background: url('Content/img/rightbtnse.png') no-repeat 0 0/100% 100%;
	color: #092356;
}

.rightbody div {
	font-size: 20px;
}

.rightbody .shuzi {
	font-size: 15px;
}

.rightbody .shuzi span {
	font-size: 25px;
	background: #092356;
	color: #55e9f8;
	margin-right: 3px;
	padding: 0 1px;
}
 

</style>
</head>
<body>
	<img class="title" src="Content/img/title.png" alt="">
	<jsp:include page="head.jsp"></jsp:include>
	<div class="bodybox">
		<div class="leftbody">
			<div id="cqmap"></div>
			<div class="mingpai" onmouseover="this.style.cursor='pointer'" onmouseout="this.style.cursor='normal'" onclick='window.location.href = "${pageContext.request.contextPath}/getWadeRightAllArea.do"'>
			</div>
		</div>
		<div class="rightbody">
			<ul>
				<li><div class='title'>涉权总数</div>
					<div class='shuzi'>
						<span id="wadeRight">${wadeRightMain1.wadeRight }</span>人次
					</div></li>
				<li><div class='title'>交办</div>
					<div class='shuzi'>
						<span id="assign">${wadeRightMain1.assign }</span>人次
					</div></li>
				<li><div class='title'>核查</div>
					<div class='shuzi'>
						<span id="inspect">${wadeRightMain1.inspect }</span>人次
					</div></li>
				<li><div class='title'>公示</div>
					<div class='shuzi'>
						<span id="publicity">${wadeRightMain1.publicity }</span>人次
					</div></li>
				<li><div class='title'>不合规处理</div>
					<div class='shuzi'>
						<span id="illegal">${wadeRightMain1.illegal }</span>人次
					</div></li>
				<li>
					<div class='title' id="div_1">说明</div>
					<div class='shuzi'>
						<span id="mocratic">${wadeRightMain1.mocratic }</span>人次
					</div>
				</li>
			</ul>
		</div>
	</div>
	<script>
		
        var dom = document.getElementById("cqmap");
        var myChart = echarts.init(dom);

        var data=[];
        
        function randomData() {
            return Math.round(Math.random()*1000);
        }
        
        option = null;
        myChart.showLoading();
       function showTime(){
            nowtime=new Date();
            year=nowtime.getFullYear();
            month=nowtime.getMonth()+1;
            date=nowtime.getDate();
            $('.time').html(year+"年"+month+"月"+date+"日"+nowtime.toLocaleTimeString())
        }

        function map(){
            


    
$.ajax({
    type: "GET",
    url: "Content/json/cq50.json",
    dataType: "json",
    async:false,
    success:function(geoJson){
        myChart.hideLoading();
    echarts.registerMap('重庆', geoJson);
    
    geoJson.features.forEach(function(e,i){
        data.push({
            name:e.properties.name,
            value:randomData(),
            label:{
                    show:false,
                },
        })
    });
    myChart.setOption(option = {
        title: {
            text: '',
            
        },
        toolbox: {
            show: false,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        // legend:{
        //     type:'plain',
        // },
        visualMap: {
            type:'piecewise',
            min: 0,
            max: 1500,
            right:0,
            text:['High','Low'],
            realtime: false,
            calculable: true,
            inRange: {

                color: ['#e0ffff', '#006edd']
            }
        },
        layoutSize:50,
        series: [
            {
                name: '重庆市地图',
                type: 'map',
                mapType: '重庆', // 自定义扩展图表类型
                itemStyle:{
                    normal:{label:{show:false}},
                    emphasis:{label:{show:true}}
                },
                left:60,
                right:60,
                top:20,
                bottom:20,
                data:
                [
                    {name: '奉节县', value: randomData()},
                    {name: '巫溪县', value: randomData()},
                    {name: '开州区', value: randomData()},
                    {name: '酉阳土家族苗族自治县', value: randomData()},
                    {name: '彭水苗族土家族自治县', value: randomData()},
                    {name: '云阳县', value: randomData()},
                    {name: '万州区', value: randomData()},
                    {name: '城口县', value: randomData()},
                    {name: '江津区', value: randomData()},
                    {name: '石柱土家族自治县', value: randomData()},
                    {name: '巫山县', value: randomData()},
                    {name: '涪陵区', value: randomData()},
                    {name: '丰都县', value: randomData()},
                    {name: '武隆区', value: randomData()},
                    {name: '南川区', value: randomData()},
                    {name: '秀山土家族苗族自治县', value: randomData()},
                    {name: '黔江区', value: randomData()},
                    {name: '合川区', value: randomData()},
                    {name: '綦江区', value: randomData()},
                    {name: '忠县', value: randomData()},
                    {name: '梁平县', value: randomData()},
                    {name: '巴南区', value: randomData()},
                    {name: '潼南区', value: randomData()},
                    {name: '永川区', value: randomData()},
                    {name: '垫江县', value: randomData()},
                    {name: '渝北区', value: randomData()},
                    {name: '长寿区', value: randomData()},
                    {name: '大足区', value: randomData()},
                    {name: '铜梁区', value: randomData()},
                    {name: '荣昌区', value: randomData()},
                    {name: '璧山区', value: randomData()},
                    {name: '北碚区', value: randomData()},
                    {name: '万盛区', value: randomData()},
                    {name: '九龙坡区', value: randomData()},
                    {name: '沙坪坝区', value: randomData()},
                    {name: '南岸区', value: randomData()},
                    {name: '江北区', value: randomData()},
                    {name: '大渡口区', value: randomData()},

                    {name: '渝中区', value: randomData()},

                ],
                // 自定义名称映射

            }
        ]
    });

if (option && typeof option === "object") {
    myChart.setOption(option, true);
}                }
})
        }
        
        $(window).resize(function(){
            console.log(1)
            myChart.resize();
            
        })
       
        $(window).ready(function(){
            map()
            setInterval(showTime,500)
            $('.mingpai').html($('.rightbody ul li').eq(0).html())
            $('.wrap1 .btnbox').click(function(){
               // $(this).addClass('selected').siblings('.btnbox').removeClass('selected')

            })
            $('.rightbody ul li').click(function(){
                
                $(this).addClass('selected').siblings('.rightbody ul li').removeClass('selected')
                $('.mingpai').html($(this).html())

            })
        })
        // 按钮变色
        window.onload = function(){
        	selected(2);
        }
        
    </script>
</body>
</html>
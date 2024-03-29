<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=VUR55hBFz2tmn6IFDkHbaZh0V0iLgnQZ"></script>
	<title>多个标注点沿折线的轨迹运动</title>
</head>
<body>
	<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	map.centerAndZoom(new BMap.Point(111.41, 40.48), 15);
	map.enableScrollWheelZoom();   
	map.enableContinuousZoom();    
	var bounds = null;
	var linesPoints = null;
	var spoi1 = new BMap.Point(111.66,40.81);    // 点1
	var spoi2 = new BMap.Point(111.77452777777778,40.4889);    // 点2
	var epoi  = new BMap.Point(111.78212777777777,40.49297222222222);    // 点3
	function initLine(){
		bounds = new Array();
		linesPoints = new Array();
		map.clearOverlays();                                                    
		var driving3 = new BMap.WalkingRoute(map,{onSearchComplete:drawLine});  
			driving3.search(spoi1, spoi2);                                       
		var driving4 = new BMap.WalkingRoute(map,{onSearchComplete:drawLine});  
			driving4.search(spoi2, epoi);                                       
	}
	function run(){
		for(var m = 0;m < linesPoints.length; m++){
			var pts = linesPoints[m];
			var len = pts.length;
		
		}
		
	
		
	}
	function drawLine(results){
		var opacity = 0.45;
		var planObj = results.getPlan(0);
		var b = new Array();
		var addMarkerFun = function(point,imgType,index,title){
			//var url;
			var width;
			var height
			var myIcon;
			// imgType:1的场合，为起点和终点的图；
			if(imgType == 1){
				url = "http://lbsyun.baidu.com/jsdemo/img/dest_markers.png";
				width = 42;
				height = 34;
				myIcon = new BMap.Icon(url,new BMap.Size(width, height),{offset: new BMap.Size(14, 32),imageOffset: new BMap.Size(0, 0 - index * height)});
			}else{
				url = "http://lbsyun.baidu.com/jsdemo/img/trans_icons.png";
				width = 22;
				height = 25;
				var d = 25;
				var cha = 0;
				var jia = 0
				if(index == 2){
					d = 21;
					cha = 5;
					jia = 1;
				}
				myIcon = new BMap.Icon(url,new BMap.Size(width, d),{offset: new BMap.Size(10, (11 + jia)),imageOffset: new BMap.Size(0, 0 - index * height - cha)});
			}
			
			//var marker = new BMap.Marker(point, {icon: myIcon});
			if(title != null && title != ""){
				marker.setTitle(title);
			}
			// 起点和终点放在最上面
			if(imgType == 1){
				marker.setTop(true);
			}
			map.addOverlay(marker);
		}
		var addPoints = function(points){
			for(var i = 0; i < points.length; i++){
				bounds.push(points[i]);
				b.push(points[i]);
			}
		}	
		// 绘制线路
		for (var i = 0; i < planObj.getNumRoutes(); i ++){
			var route = planObj.getRoute(i);
			if (route.getDistance(false) <= 0){continue;}
			addPoints(route.getPath());
			// 线路
	
			if(route.getRouteType() == BMAP_ROUTE_TYPE_DRIVING){
				map.addOverlay(new BMap.Polyline(route.getPath(), {strokeColor: "#0030ff",strokeOpacity:opacity,strokeWeight:6,enableMassClear:true}));
			}else{
			// 线路有可能为0
				map.addOverlay(new BMap.Polyline(route.getPath(), {strokeColor: "#30a208",strokeOpacity:0.75,strokeWeight:4,enableMassClear:true}));
			}
		}	
		map.setViewport(bounds);	
		// 终点
		addMarkerFun(results.getEnd().point,1,1);
		// 开始点
		addMarkerFun(results.getStart().point,1,0);
		linesPoints[linesPoints.length] = b;
	}
	initLine();
	setTimeout(function(){
		run();
	},1500);
	var myDrag = new BMapLib.RectangleZoom(map, {
		followText: "拖拽鼠标进行操作"
	});
	
</script>

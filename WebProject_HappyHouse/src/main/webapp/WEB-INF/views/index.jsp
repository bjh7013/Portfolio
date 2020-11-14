<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ include file="/WEB-INF/views/init/el.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/init/stylesheet.jsp"%>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script  src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1c6663d964c18e9a75c1047d69aadaf1"></script>		


<script>

$(document).ready(function(){
	var lat = 37.5665734;
	var lng = 126.978179;
	var container = document.getElementById('main_map');
	var options = {
		center : new kakao.maps.LatLng(lat, lng),
		level : 3
	};
	var map = new kakao.maps.Map(container, options); //make map
	var mapTypeControl = new kakao.maps.MapTypeControl(); //make typecontrol
	map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
	var zoomControl = new kakao.maps.ZoomControl();
	map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
	
	var markers = [];
	var multi = new kakao.maps.LatLng(37.5665734, 126.978179); 
	
//-------------지도용 함수-----------------------------------
	function closeOverlay() {
    	overlay.setMap(null);     
    }
	//마커 추가 ㅡㅡ 
	var overlay
	function addMarker(lat, lng, shopname,address) {
		//console.log(lat + ' ' + lng+':'+shopname);
		
		var marker = new kakao.maps.Marker({
			map : map,
			position : new kakao.maps.LatLng(lat, lng),
			clickable : true
		});
		var content = '<div class="mapoverlay"><div class="customoverlay">' +
		'<a href="https://map.kakao.com/link/map/'+shopname+','+lat+','+lng+'" target="_blank">'+
	    '    <span class="customtitle">'+shopname+'</span>' +
	    '  </a>' +
	    '</div></div>';
        
       
     
       
        overlay = new kakao.maps.CustomOverlay({
        	yAnchor: 0.3,
            content: content,
            map: map,
         	position: marker.getPosition(),  
         	 clickable: true

        });
        
        markers.push(marker);
		setCenter(lat,lng); //화면 이동
	}
	
	//마커 설정
	function setMarkers(map) {

		for (var i = 0; i < markers.length; i++) {
			markers[i].setMap(map);
			 
		}
	}
	//마커있는데로 이동
	function setCenter(lat,lng) {            
	    var moveLatLon = new kakao.maps.LatLng(lat, lng);
	    map.setCenter(moveLatLon);
	}
	
	//기본 위치 설정
	addMarker(lat,lng,'서울특별시청','서울특별시청');
	
// ----------------------------------------------------------------
	$.get("${pageContext.request.contextPath}/api/map/mapsearch"
			,{command:"sido"}
			,function(data, status){
				$.each(data, function(index, vo) {
					$("#sido").append("<option value='"+vo.sido_code+"'>"+vo.sido_name+"</option>");
				});//each
			}//function
			, "json"
		);//get
	$("#sido").change(function() {
		$.get("${pageContext.request.contextPath}/api/map/mapsearch"
				,{command:"gugun", sido:$("#sido").val()}
				,function(data, status){
					$("#gugun").empty();
					$("#gugun").append('<option value="0">선택</option>');
					$.each(data, function(index, vo) {
						$("#gugun").append("<option value='"+vo.gugun_code+"'>"+vo.gugun_name+"</option>");
					});//each
				}//function
				, "json"
		);//get
	});//change
	$("#gugun").change(function() {
		$.get("${pageContext.request.contextPath}/api/map/mapsearch"
				,{command:"dong", gugun:$("#gugun").val()}
				,function(data, status){
					$("#dong").empty();
					$("#dong").append('<option value="0">선택</option>');
					$.each(data, function(index, vo) {
						$("#dong").append("<option value='"+vo.dong+"'>"+vo.dong+"</option>");
					});//each
				}//function
				, "json"
		);//get
	});//change
	$("#dong").change(function() {
		$.get("${pageContext.request.contextPath}/api/map/mapsearch"
				,{command:"comm", dong:$("#dong").val()}
				,function(data, status){
					
					$("tbody").empty();
					$(".mapoverlay").empty();
					
					if(data.length==0){
						$("tbody").append("<tr><td>해당 지역의 상권 정보가 없습니다</td></tr>");
					}
					else{
					$.each(data, function(index, vo) {
						let str = "<tr class='hihi'>"
						+ "<td>" + vo.codename + "</td>"
						+ "<td id='"+vo.lat+"/"+vo.lng+"'>"+  vo.shopname + "	</td>"
						+ "<td>" + vo.address + "</td>"
						+ "</td></tr>"
						$("tbody").append(str);
					//	$("#searchResult").append(vo.dong+" "+vo.shopname+" "+vo.codename+"<br>");
					});//each
					}
					geocode(data);
				}//function
				, "json"
		);//get
	});//change

	
	function geocode(jsonData) { // 상가 좌표 찍기
	//	console.log(markers);
		setMarkers(null); //마커 지우기 
		markers = [];
		$.each(jsonData, function(index, vo) {
			 addMarker(replaceAll(vo.lat, '"', ''), replaceAll(vo.lng, '"', ''), vo.shopname,vo.address);
		});//each
		setMarkers(map);
	}
	function replaceAll(str, searchStr, replaceStr) {
		return str.split(searchStr).join(replaceStr);
	}
	
	$(document).on("click",".hihi",function(){
		console.log("hi");
		var tr = $(this);
		var td = $(this).find('td').eq(1);
		console.log(td.attr("id"));
		var location =	td.attr("id").split("/");
	  	setCenter(location[0],location[1]);
	});
});//ready
  

	
</script>
</head>
<body>
	<div id="wrap">
		<%@include file="/WEB-INF/views/header.jsp"%>
		<section>
			<div id="sido_gu_dong" >
				
				시도 : <select id="sido" >
				<option value="0">선택</option>
				</select>
				구군 : <select id="gugun">
				<option value="0">선택</option>
				</select>
				읍면동 : <select id="dong">
				<option value="0">선택</option>
				</select>
			</div>
			<div id="map_content">
				<table class="table mt-2" id="map_house">
				<thead>
					<tr>
						<th>분류</th>
						<th>이름</th>
						<th>주소</th>
						
					</tr>
				</thead>
				<tbody>
				</tbody>
				</table>
				<div id="main_map">
				</div>
			</div>
		</section>
		<%@include file="/WEB-INF/views/footer.jsp"%>
	</div>

</body>
</html>
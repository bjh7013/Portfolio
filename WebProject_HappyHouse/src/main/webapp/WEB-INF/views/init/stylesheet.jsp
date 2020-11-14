<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>

<style>
a:link {
	color: black;
	text-decoration: none;
}

a:visited {
	color: black;
	text-decoration: none;
}

header a:hover {
	color: white;
	text-decoration: none;
}

html {
	height: 100%;
}

#wrap {
	height: 100%;
	position: relative;
}

header {
	
	height: 200px;
	background: #dce6ff;
	
}

#menu_home {
	height:30px;
	text-align: center;
	width: 20%;
}

#menu_bar {
	height:30px;
	text-align: center;
	width: fit-content;
	margin: auto;
}

#menu_login {
	height:30px;
	width: 12%;
	text-align: center;
}
#menu_logout:hover{
	color:red;
}

section {
	padding: 20px;
	min-height: 780px;
	margin: 20px auto;
	width: 80%;
	text-align: center;
}
footer {
 font-family: 'Poppins', sans-serif;
    line-height: 100px;;
	width: 100%;
	height: 100px;
	opacity: 0.5;
}

footer .footer-menu li{
	display: inline-block;
}	

footer .footer-menu li a{
	margin: 0px 10px;
	text-decoration: none;
}

footer .fa{
    font-size:30px;
}
img {
	border: 1px black solid;
	display: block;
	margin: 0 auto;
	height: 300px;
	width: 300px;
}

#pwmsg {
	height: 150px;
	line-height: 10;
}

.col-lg-6 {
	margin-top: 40px;
	border-radius: 40px;
	padding: 30px;
	background: #e8e8e8;
}

h3, h4 {
	margin: auto;
	text-align: center;
}

#map_content {
	margin-top: 30px;
	overflow: scroll;
}

#main_map {
	float: right;
	height: 500px;
	background: green;
	width: 45%;
}

#map_house  tbody {
	display: block;
	height: 500px;
	overflow: auto;
}

#map_house thead, #map_house tbody tr {
	display: table;
	width: 100%;
	table-layout: fixed;
}

#map_house {
	width: 50%;
	height: 400px;
	float: left;
}

#searchdeal {
	margin: 0 auto;
	width: 80%;
}

#pagenavi {
	margin: auto;
	margin-top: 100px;
}

.member_base {
	font-size: 15px;
	text-align: right;
}

#environmentlist {
	width: 55%;
	height: 500px;
	float: right;
}

#environmentlist thead, #environmentlist tbody tr {
	display: table;
	width: 100%;
	table-layout: fixed;
}

#environmentlist tbody {
	display: block;
	overflow: scroll;
	height: 450px;
}

.customoverlay {
	position: relative;
	bottom: 85px;
	border-radius: 6px;
	border: 1px solid #ccc;
	border-bottom: 2px solid #ddd;
	float: left;
}

.customoverlay:nth-of-type(n) {
	border: 0;
	box-shadow: 0px 1px 2px #888;
}

.customoverlay a {
	display: block;
	text-decoration: none;
	color: #000;
	text-align: center;
	border-radius: 6px;
	font-size: 14px;
	font-weight: bold;
	overflow: hidden;
	background: #d95050;
	background: #d95050
		url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
		no-repeat right 14px center;
}

.customoverlay .customtitle {
	display: block;
	text-align: center;
	background: #fff;
	margin-right: 35px;
	padding: 10px 15px;
	font-size: 14px;
	font-weight: bold;
}

.customoverlay:after {
	content: '';
	position: absolute;
	margin-left: -12px;
	left: 50%;
	bottom: -12px;
	width: 22px;
	height: 12px;
	background:
		url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')
}
</style>
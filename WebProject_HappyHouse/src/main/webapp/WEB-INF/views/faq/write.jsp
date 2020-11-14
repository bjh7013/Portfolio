	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ include file="/WEB-INF/views/init/el.jsp"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>공지사항 작성</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function writeNotice() {
		if (document.getElementById("faqtype").value == "") {
			alert("유형을 선택해주세요.");
			return;
		} else {
			document.getElementById("writeform").action = "${root}/api/faq/writefaq.do";
			document.getElementById("writeform").submit();
		}
	}
</script>
<%@include file="/WEB-INF/views/init/stylesheet.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/views/header.jsp"%>
	<section>
		<c:if test="${userinfo!=null}">
			<div class="container" align="center">
				<div class="col-lg-8" align="center">
					<h2 align="left">FAQ 작성</h2>
					<br>
					<div class="form-group" align="left">
						<label for="content">유형 : </label> <select name="faqtype"
							id="faqtype">
							<option value="">유형선택</option>
							<option value="사이트이용">사이트이용</option>
							<option value="부동산검색">부동산검색</option>
							<option value="게시판사용">게시판이용</option>
						</select>
					</div>
					<form id="writeform" method="post" action="">
						<div class="form-group" align="left">
							<label for="title">제목 : </label> <input type="text"
								class="form-control" id="faqtitle" name="faqtitle">
						</div>
						<div class="form-group" align="left">
							<label for="content">내용 : </label>
							<textarea class="form-control" rows="15" id="faqcontent"
								name="faqcontent"></textarea>
						</div>
						<div style="margin-top:40px;">
						<button type="button" class="btn btn-primary"
							onclick="javascript:writeNotice();">작성</button>
						<button type="reset" class="btn btn-warning">초기화</button>
						<a href="${root}/api/user/usermenu.do" class="btn btn-secondary">뒤로</a>
					</div>
					</form>
				</div>
			</div>
		</c:if>
	</section>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ include file="/WEB-INF/views/init/el.jsp"%>

<%
	int ch=0;
%>

<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
		<script type="text/javascript">
		function movewrite() {
			location.href="${root}/api/faq/writefaq.do";
		}
		function pageMove(pg) {
			document.getElementById("pg").value = pg;
			document.getElementById("searchform").action = "${root}/api/faq/faqlist.do";
			document.getElementById("searchform").submit();
		}
		
		function search() {
			if (document.getElementById("word").value == "") {
				alert("검색어를 입력하지 않아 모든 게시글을 조회합니다.");
			}
			document.getElementById("pg").value = 1;
			document.getElementById("searchform").action = "${root}/api/faq/faqlist.do";
			document.getElementById("searchform").submit();
		}
		</script>
		
		<%@ include file="/WEB-INF/views/init/stylesheet.jsp" %>
	</head>
	<body>	
	<%@include file="/WEB-INF/views/header.jsp"%>
	<section>
	<div class="container" align="center">
	  <div class="col-lg-10" align="center">
	  <h2 align="left">자주 묻는 질문</h2>  
	  <c:if test="${userinfo.usertype == 'manager' }">
	  <%
	  	ch=1;
	  %>
	  <table class="table table-borderless">
	  	<tr>
	  		<td align="right"><button type="button" class="btn btn-link" onclick="javascript:movewrite();">작성</button></td>
	  	</tr>
	  
	  </table>
	  </c:if>
	  
	  
	  <c:forEach var="faq" items= "${faqs}">
	  <table class="table table-active">
	    <tbody>
	      <tr class="table-info">
	      <td width="400px">Q : <a href="${root}/api/faq/detailfaq.do/${faq.faqno}">${faq.faqtitle}</a></td>
	      </tr>
	    </tbody>
	  </table>
	  </c:forEach>
	  
	  <c:if test="${faqs == null }">
	  <table class="table table-active">
	    <tbody>
	      <tr class="table-info" align="center">
	        <td>작성된 글이 없습니다.</td>
	      </tr>
	    </tbody>
	  </table>
	  </c:if>
	  <form id="searchform" method="get" class="form-inline" action="" style="display: block; margin-top:20px;">
				<input type="hidden" name="act" value="list"> 
				<input type="hidden" name="pg" id="pg" value="">
				<input type="text" placeholder="검색어를 입력하세요" name="word" id="word" value="${words}" >
	<button type="button" class="btn btn-primary" onclick="javascript:search();">검색</button>
		</form>
	  		<table id="pagenavi">
				<tr>
					<td>
						<!-- page 출력부분 --> ${navigation.navigator}
					</td>
				</tr>
			</table>
	  </div>
	</div>
	</section>
	<%@include file="/WEB-INF/views/footer.jsp"%>
	</body>
</html>
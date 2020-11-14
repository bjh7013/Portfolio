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
			location.href="${root}/notice.do?act=mvwrite";
		}
		
		</script>
	</head>
	<body>	
	<section>
	<div class="container" align="center">
	  <div class="col-lg-8" align="center">
	  <h2>공지사항</h2>  
	  <c:if test="${userinfo.type == 'manager' }">
	  <%
	  	ch=1;
	  %>
	  <table class="table table-borderless">
	  	<tr>
	  		<td align="right"><button type="button" class="btn btn-link" onclick="javascript:movewrite();">작성</button></td>
	  	</tr>
	  
	  </table>
	  </c:if>
	  
	  
	  <c:forEach var="notice" items= "${searchsnotice}">
	  <table class="table table-active">
	    <tbody>
	      <tr class="table-info">
	        <td>작성자 : ${notice.membername }</td>
	        <td align="right">작성일 : ${notice.regtime}</td>
	      </tr>
	      <tr>
	        <td colspan="2" class="table-danger"><strong>${notice.articleno}. ${notice.subject}</strong></td>
	      </tr>
	      <tr>
	        <td colspan="2">내용 : ${notice.content}
	        	<br>
	        	<%if(ch==1){%>
	        		<a href="${root}/notice.do?act=mvmodify&num=${notice.num}">수정</a>
					<a href="${root}/notice.do?act=delete&num=${notice.num}">삭제</a>
				<%}%>
	        </td>
	      </tr>
	      
	    </tbody>
	  </table>
	  </c:forEach>
	  
	  <c:if test="${searchsnotice == null }">
	  <table class="table table-active">
	    <tbody>
	      <tr class="table-info" align="center">
	        <td>작성된 글이 없습니다.</td>
	      </tr>
	    </tbody>
	  </table>
	  </c:if>
	  </div>
	</div>
	</section>
	</body>
</html>
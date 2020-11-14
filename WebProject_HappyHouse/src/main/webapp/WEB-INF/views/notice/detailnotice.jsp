<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ include file="/WEB-INF/views/init/el.jsp"%>

<%
	int ch = 0;
%>

<!DOCTYPE html>
<html lang="ko">
<head>
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
	function movewrite() {
		location.href = "${root}/board/writenotice.do";
	}
</script>
<%@ include file="/WEB-INF/views/init/stylesheet.jsp"%>
<style>
#modifycomplete{
	display:none;
}
#basiccomment{
}
#updatecomment{
	display:none;
}
</style>
</head>
<body>
	<script type="text/javascript">
		function writeComment() {
			if (document.getElementById("comment").value == "") {
				alert("댓글을 입력해 주세요");
				return;
			} else {
				document.getElementById("writeform").action = "${root}/api/comment/createcomment.do";
				document.getElementById("writeform").submit();
			}
		};
		
		
		$("#modifyBtn").click(function() {
			alert("사용 가능한 아이디입니다.");
			/* if($("#modifyBtn").text()=="수정"){
				$("#modifyBtn").text("수정취소");
			}else{
				$("#modifyBtn").text("수정");
			} */
			/* $.ajax({
				type:'GET',
				url:'/happyhouse/api/user/usersearch.do/'+$("#userid").val(),
				dataType:'text',
				success:function(data){
					if(data=="yes"){
						alert("사용 가능한 아이디입니다.");
					}else if(data=="no"){
						alert("아이디가 중복입니다.");					
					}
					$("#doubleid").val(data);
				},
		        error: function(errorThrown) {
		            alert(errorThrown.statusText);
		        }
	        }); */
		});
	</script>
	<%@include file="/WEB-INF/views/header.jsp"%>
	<section>
	<div class="container" align="center">
		<div class="col-lg-10" align="center">
			<c:if test="${userinfo.usertype == 'manager' }">
				<%
					ch = 1;
				%>
			</c:if>
			<c:if test="${notice!=null}">
				<table class="table table-active">
					<tbody>
						<tr class="table-info">
							<td width="400px">제목 : ${notice.subject}</td>
							<td width="200px">작성자 : ${notice.username}</td>
							<td width="400px">작성일 : ${notice.regtime}</td>
							<td width="200px">조회수 : ${notice.clicked}</td>
					</tbody>
				</table>
				<textarea rows="15" cols="100" readonly>${notice.content}</textarea>
				<br>
			</c:if>
			<%
				if (ch == 1) {
			%>
			<a href="${root}/api/board/modifynotice.do/${notice.articleno}">수정</a>
			<a href="${root}/api/board/deletenotice.do/${notice.articleno}">삭제</a>
			<%
				}
			%>
			<c:if test="${comments!=null}">
				<c:forEach var="comment" items="${comments}">
					<c:if test="${comment.userid!=userinfo.userid}">
						<table class="table table-active">
							<tbody>
								<tr>
									<td>${comment.username}</td>
								</tr>
								<tr>
									<td>${comment.comment}</td>
								</tr>
								<tr>
									<td>${comment.commenttime}</td>
								</tr>
							</tbody>
						</table>
					</c:if>
					<c:if test="${userinfo!=null}">
						<c:if test="${comment.userid==userinfo.userid}">
							<table class="table table-active">
								<tbody>
									<tr>
										<td>${comment.username}</td>
									</tr>
									<tr>
										<td id="basiccomment">${comment.comment}</td>
										<td id="updatecomment"><input type="text"/></td>
									</tr>
									<tr>
										<td>${comment.commenttime}</td>
									</tr>
								</tbody>
							</table>
							<a href="${root}/api/comment/deletecomment.do/${comment.articleno}/${comment.commentno}">삭제</a>
						</c:if>
					</c:if>
				</c:forEach>
			</c:if>
			<c:if test="${userinfo!=null}">
				<form id="writeform" method="post" action="">
					<input type="hidden" class="form-control" id="articleno"
						name="articleno" value="${notice.articleno}"> <input
						type="hidden" class="form-control" id="username" name="username"
						value="${userinfo.username}"> <input type="hidden"
						class="form-control" id="userid" name="userid"
						value="${userinfo.userid}"> <input type="hidden"
						name="act" value="write">
					<div class="form-group" align="left">
						<label for="content">댓글내용 : </label>
						<textarea class="form-control" rows="15" id="comment"
							name="comment"></textarea>
					</div>
					<button type="button" class="btn btn-primary"
						onclick="javascript:writeComment();">댓글작성</button>
					<button type="reset" class="btn btn-warning">초기화</button>
				</form>
			</c:if>
			<c:if test="${notice == null }">
				<table class="table table-active">
					<tbody>
						<tr class="table-info" align="center">
							<td>작성된 글이 없습니다.</td>
						</tr>
					</tbody>
				</table>
			</c:if>
			<a href="${root}/api/board/notice.do">뒤로</a>
		</div>
	</div>
	</section>
	<%@include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>
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
	function writeComment() {
		if (document.getElementById("replyContent").value == "") {
			alert("답변을 입력해 주세요");
			return;
		} else {
			document.getElementById("writeform").action = "${root}/api/qnam/modifycomment.do";
			document.getElementById("writeform").submit();
		}
	};
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
	<%@include file="/WEB-INF/views/header.jsp"%>
	<section>
	<div class="container" align="center">
		<div class="col-lg-10" align="center">
			<c:if test="${userinfo.usertype == 'manager' }">
				<%
					ch = 1;
				%>
			</c:if>
			<c:if test="${qna!=null}">
				<table class="table table-active">
					<tbody>
						<tr class="table-info">
							<td width="400px">제목 : ${qna.qnaTitle}</td>
							<td width="400px">작성일 : ${qna.qnaDatetime}</td>
						</tr>
					</tbody>
				</table>
				<textarea rows="15" cols="100" readonly>${qna.qnaContent}</textarea>
				<br>
			</c:if>
			<a href="${root}/api/qnam/deleteqna.do/${qna.qnaNo}">삭제</a>
			<c:if test="${qna.replyCheck!=null}">
						<table class="table table-active">
							<tbody>
								<tr>
									<td>${qna.replyDatetime}</td>
								</tr>
							</tbody>
						</table>
					<textarea rows="15" cols="100" readonly>${qna.replyContent}</textarea>
			</c:if>
			<c:if test="${userinfo!=null}">
				<form id="writeform" method="post" action="">
					<input type="hidden" class="form-control" id="qnaNo"
						name="qnaNo" value="${qna.qnaNo}"><input type="hidden"
						name="act" value="write">
					<div class="form-group" align="left">
						<label for="replyContent">답변내용 : </label>
						<textarea class="form-control" rows="15" id="replyContent"
							name="replyContent"></textarea>
					</div>
					<button type="button" class="btn btn-primary"
						onclick="javascript:writeComment();">답변작성</button>
					<button type="reset" class="btn btn-warning">초기화</button>
				</form>
			</c:if>
			<a href="${root}/api/qnam/totallist.do">뒤로</a>
		</div>
	</div>
	</section>
	<%@include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>
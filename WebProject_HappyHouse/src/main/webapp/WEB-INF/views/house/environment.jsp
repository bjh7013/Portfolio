<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<div id="environmentlist">
	
		<table
			style="border: 1px solid black; margin: auto; text-align: center; width: 100%">
			<thead>
				<tr style="border-bottom: 5px double black;">
					<th>환경 정보</th>
					<th>주 소</th>
					<th>우편번호</th>
					

				</tr>
			</thead>
			<tbody>
				<c:forEach var="environmentinfo" items="${environmentinfos}">

					<tr style="border-bottom: 1px solid black;">
						<td>${environmentinfo.name }</td>
						<td>${environmentinfo.address }</td>
						<td>${environmentinfo.dongcode }</td>
						


					</tr>


				</c:forEach>
			</tbody>
		</table>
	
		<c:choose>
			<c:when test="${environmentinfos==null }">
				<div style="margin: 100px;">불러올 환경 정보가 없습니다.</div>
			</c:when>
			<c:when test="${environmentinfos.size()==0 }">
				<div style="margin: 100px;">불러올 환경 정보가 없습니다.</div>
			</c:when>

		</c:choose>


	</div>


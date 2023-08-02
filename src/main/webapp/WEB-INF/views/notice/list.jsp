<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항 목록 조회</title>
		<style>
			table{
				widht : 800px;
				border : 1px solid black;
				border-collapse : collapse;
			}
			th, td {
				border : 1px solid black;
			}
		</style>
	</head>
	<body>
		<h1>공지사항 목록</h1>
		<table>
			<colgroup>
				<col wigth="10%">
				<col wigth="35%">
				<col wigth="10%">
				<col wigth="15%">
				<col wigth="10%">
			</colgroup>
			<thead>
				<tr>
					<th>글번호</th>
					<th>글제목</th>
					<th>글쓴이</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
<!-- 			for(Notice notice : nList) -->
				<c:forEach var="notice" items ="${requestScope.nList }">  <!-- requestScope는 생략 가능 -->
					<tr>
<!-- 					notice.getNoticeNo() -->
						<td>${notice.noticeNo }</td>
<!--	 				notice.getNoticeSubject() -->
						<td><a href="/notice/detail.do?noticeNo=${notice.noticeNo }">${notice.noticeSubject }</a></td>
<!-- 					폼태그가 없으면 무조건 get으로 전달 받음 -->
<!--	 				notice.getNoticeWriter() -->
						<td>${notice.noticeWriter }</td>
						<td>${notice.noticeDate }</td>
						<td>${notice.viewCount }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>
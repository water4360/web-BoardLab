<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시글 목록</h1>
	<hr>
	<h3>${ user.name }님
		환영합니다! <a href="logout.do">로그아웃</a>
	</h3>

	<!-- 글제목을 클릭했을 때 넘어가도록. -->
	<!-- 글번호로 찾았을때도 넘어갈 수 있도록. -->
	<table border="1">
		<tr>
			<td>글번호</td>
			<td>제목</td>
			<td>글쓴이</td>
			<td>등록일</td>
			<td>조회수</td>
		</tr>
		<c:forEach var="board" items="${boardList}">
			<tr>
				<td>${board.seq}</td>
				<td><a href="getBoard.do?seq=${board.seq}">${board.title}</a></td>
				<td>${board.writer}</td>
				<td>${board.regDate}</td>
				<td>${board.hit}</td>
			</tr>
		</c:forEach>
		<tr>
			<a href="insertBoard.html">글쓰기</a>
		</tr>
		<form action="searchBoard.do" method="post">
			<table>
<!-- 				뭘로 찾느냐에 따라 name을 writer로 바꾸고 controller에서 writer 등으로 바꾸면 됨 -->
				<td><input type="text" name="writer" /></td>
				<td><input type="submit" value="검색" /></td>
			</table>
		</form>
	</table>
</body>
</html>
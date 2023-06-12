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
	<h1>글 상세보기👀</h1>
	<hr>
	<form action="updateBoard.do" method="post">
		<input name="seq" type="hidden" value="${board.seq}" />

		<h3>${ user.name }님
			환영합니다! <a href="logout.do">로그아웃</a>
		</h3>

		<!-- 글제목을 클릭했을 때 넘어가도록. -->
		<!-- 글번호로 찾았을때도 넘어갈 수 있도록. -->
		<table border="1">
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" value="${board.title}" /></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${board.writer}</td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="content" cols="40" rows="10">
				${board.content}
				</textarea></td>
			</tr>
			<tr>
				<td>등록일</td>
				<td>${board.regDate}</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td><input type="text" name="hit" value="${board.hit}" /></td>
			</tr>
			<tr>
				<td = colspan="2"><input type="submit" value="수정" />
				<c:if test="${user.role == 'Admin'}">
						<a href="deleteBoard.do?seq=${board.seq}">삭제</a>
					</c:if> <a href="getBoardList.do">목록</a>
			</tr>
		</table>
		<br>
	</form>

</body>
</html>
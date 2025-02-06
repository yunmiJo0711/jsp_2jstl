<%@page import="vo.UserAccountVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOME-index.jsp</title>
</head>
<body>
<h1>HOME</h1>
	<hr/>
	<%
	//UserAccountVO vo = (UserAccountVO)session.getAttribute("user");
	%>
		<h3>2JSTL 프로젝트의 메뉴입니다.</h3>
		<ul>
		<!-- (UserAccountVO)session.getAttribute("user") 를 EL에서 -->	
			<c:if test="${user ne null }">  <!-- ne : not equal [!=] -->
					<li>로그인 사용자</li>
					<li> 아이디 : ${user.userid }</li>
					<li> 이름 : ${user.username }</li>
					<li> 이메일 :${user.email }</li>
					<li><a href="5_logout.jsp">로그아웃</a></li>  <!-- 로그인 상태일 때만 나오는 메뉴 -->	
			</c:if>
			<c:if test="${user eq null }">  <!-- eq : equal [==] -->
					<li><a href="/1start/4_register.jsp">회원가입</a></li> <!-- 로그인 상태일 때가 아닐 때 나오는 메뉴 -->
					<li><a href="5_login.jsp">로그인</a></li> <!-- 로그인 상태일 때가 아닐 때 나오는 메뉴 -->
			</c:if>
		</ul>
		
</body>
</html>
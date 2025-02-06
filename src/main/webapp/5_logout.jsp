<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- HTML 출력 없이 자바 코드만 실행할 때에는 HTML 관련 태그 없어도 됩니다.  -->

<%
	session.invalidate();  // 기존 세션 무효처리
	response.sendRedirect("/2JSTL/");  // 새로운 요청 -> 새로운 세션ID 발급
	// index.jsp 대신에 /2JSTL/ path(경로) 지정.
%>

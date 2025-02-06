<%@page import="vo.UserAccountVO"%>
<%@page import="dao.UserAccountDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>5_loginProc.jsp</title>
</head>
<body>
	<%
		String userid=request.getParameter("userid");
		String password=request.getParameter("password");
		
		// SQL 실행을 위해 UserAccountDao 의 객체를 가져오기.
		UserAccountDao dao = UserAccountDao.getInstance();
		// 로그인 SQL 실행을 위한 메소드 호출
		UserAccountVO vo = dao.selectForLogin(userid, password);
		if(vo != null){
			session.setAttribute("user", vo);  
			// 세션에 필요한 데이터 저장.  --> 로그인 사용자에 대한 정보
			// 브라우저에 저장하는건 쿠키, 세션에 필요한 데이터를 서버에 저장하는건 jsp
			// " " <- 이 안에 들어가는 key 값은 임의로 준다. 
			response.sendRedirect("/2JSTL/");  
			//  HOME 경로로 url 설정. 서버측에서 새로운 url로 페이지 요청. 
			
		}else{
			response.sendRedirect("5_login.jsp?fail=y");
			// 서버측에서 새로운 url로 페이지 요청. 로그인 실패를 알리기 위한 파라미터 추가.
		}
	%>
		
		
		
		
		
		
</body>
</html>
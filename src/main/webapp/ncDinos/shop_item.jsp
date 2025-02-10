<%@page import="vo.FanItemVO"%>
<%@page import="dao.FanitemDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.container{
	display: flex; width: 800px; margin: auto;
	}
	.img-box{
	width:400px; height: 450px;
	}
	.cont-box{
	margin-left: 20px; width: 300px;
	}
	.orderCount{
	border-bottom: 1px solid gray; border-top: 1px solid gray
	}
	.orderButton{
	 style="margin-top: 20px;
	}
	
</style>
</head>
<body>
<%
	FanItemVO  vo = null; 
	try {
		int seq = Integer.parseInt(request.getParameter("seq"));
		// request.getParameter("seq") : seq 라는 이름의 파라미터값 가져오기. 
		// Integer.parseInt(문자열) : 문자열을 정수로 변환
		
		FanitemDao dao = FanitemDao.getInstance();
		 vo = dao.selectByPk(seq);  // 파라미터로 전달되 seq 값으로 select 조회.
		 if(vo == null){
			 response.sendRedirect("ncDinos_shop.jsp");
		 }
		//out.print(vo);   // 테스트용
		pageContext.setAttribute("vo", vo);
	}catch (Exception e){
		// seq 파라미터가 없을 때 , 예외 발생. -> 상품 목록 페이지로 리다이렉트 합니다.
		response.sendRedirect("ncDinos_shop.jsp");
	}
%>

<h3>구매</h3>
<hr/>
<div class="container">
	<div class="img-box">
	<!-- img 이미지 크기는 부모요소 div의 100% -->
		<img alt="${vo.title }" src="product/${vo.filename}" width="100%">
	</div>
	<div class="cont-box">
		<h3>${vo.title}</h3>
		<h4>정가 :<fmt:formatNumber value="${vo.price }" pattern="###,###"/>원</h4>
		<h4>판매가 :<fmt:formatNumber value="${vo.price }" pattern="###,###"/>원</h4>
		<div class="orderCount">
			주문수량 : 
			<input type="number" min="1" max="999" size="20"
					style="padding: 5px 10px; margin: 10px; font-size: 1.3rem" 
			/>
		</div>
		<div class="orderButton">
			<button style="padding: 10px 20px; font-size: 1.3rem">구매하기</button>
		</div>
	</div>
</div>
<hr/>
		<div style="text-align: center;">
			<button style="padding: 10px 20px; font-size: 1.3rem"
					onclick="location.href='ncDinos_shop.jsp'">목록</button>
		</div>
</body>
</html>
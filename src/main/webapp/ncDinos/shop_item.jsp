<%@page import="vo.FanItemVO"%>
<%@page import="dao.FanitemDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NCDinos Fan Item</title>
<link rel="stylesheet" href="css/orderItem.css">  <!-- css주소가 제대로 맞는지 확인필요 ctrl하고 마우스 대보기 -->
</head>
<body>
<%
	FanItemVO vo = null; 
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
	<!-- 사용자가 선택 또는 입력하는 것이 아니고
		 기존의 값을 파라미터 전달해야 하는 것을 type="hidden" 으로 합니다. -->
	<!-- input에 form 태그 안의 입력 요소를 서버로 전송하려면 파라미터 이름을 name 속성으로 설정해야함. -->
	<form action="fanitem_order.jsp" method="post">
		<input type="hidden" name="seq" value="${vo.seq }"/>
		<input type="hidden" name="price" value="${vo.price }"/>
		<div class="cont-box">
			<h3>${vo.title}</h3>
			<h4>정 가 :<fmt:formatNumber value="${vo.price }" pattern="###,###"/>원</h4>
			<h4>판매가 :<fmt:formatNumber value="${vo.price }" pattern="###,###"/>원</h4>
			<div class="orderCount">
				주문수량 : <!-- input에 form 태그 안의 입력 요소를 서버로 전송하려면 파라미터 이름을 name 속성으로 설정해야함. -->
				<input type="number" name="count" min="1" max="999" size="20" value="1" />
			</div>
			<div class="orderButton">
			<!-- 구매 요구사항 : 반드시 로그인 상태에서 실행. -->
			<!-- 1start 프로젝트의 4_register.jsp 와 다른점(새로운 방법. 둘다 많이 사용하는 방법이다.)
				1) button 의 type : submit 을 button 으로 변경
				2) 자바스크립트 이벤트 : onsubmit 을 onclick 으로 변경 -->
				<button type="button"
						onclick="orderItem()">구매하기</button>
			</div>
		</div>
	</form>
</div>
<hr/>
	<div style="text-align: center;">
		<button onclick="location.href='ncDinos_shop.jsp'">목록</button>
	</div>
	<script type="text/javascript">
		const useraccount = '${user}'  // jsp 의 애프리뷰트 user를 가져옵니다.
		function orderItem(){
		if(useraccount ==''){  // 로그인 하지 않은 상태 
			if(confirm('구매는 로그인이 필요합니다. 로그인 하시겠습니까?')){
				location.href='../5_login.jsp'  // ../ 부모 폴더로 이동
				}
			}else{ // 로그인 한 상태
				if(confirm('구매하시겠습니까?')){
					document.forms[0].submit() // 첫번째[0] form 요소의 값을 서버로 제출 
				}
				
			}
		}
		
	</script>
</body>
</html>
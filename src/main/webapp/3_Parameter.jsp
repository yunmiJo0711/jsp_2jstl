<%@page import="vo.Product"%>
<%@page import="java.util.List"%>
<%@page import="dao.TblProductDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<title>프로젝트 2 : 3_Parameter.jsp</title>
</head>
<body>
<h2>상품 카테고리</h2>
<!-- a 태그로 메뉴의 링크 주소 설정. 추가로 ?파라미터이름=파라미터값 전달.
     a 태그의 href 의 url 주소값으로 새로운 요청(request)을 서버로 보냅니다.
-->
<ul>
	<li><a href="3_Parameter.jsp?category=A1">국내산 과일</a></li>
	<li><a href="3_Parameter.jsp?category=A2">수입 과일</a></li>
	<li><a href="3_Parameter.jsp?category=B1">인스턴트 식품</a></li>
	<li><a href="3_Parameter.jsp?category=B2">선물세트</a></li>
	<li><a href="3_Parameter.jsp?category=C1">과자류</a></li>
</ul>
<%
	String category = request.getParameter("category");
	// 1. 파라미터가 없는 URL 일 때 category 변수 초기화  
	//if(category==null)  category ="A1"; 
	
	// 2. 파라미터가 없는 URL 일 때, 전체 상품 출력하기 
	TblProductDao dao = TblProductDao.getInstance();
	List<Product> list = null;
	if(category==null)
		list = dao.selectAllProduct();
	else 
		list = dao.selectByCategory(category);   // 카테고리값 변경 직접 문자열값 변경
	//결과 리스트를 브라우저에 출력하고 싶다.--> table 태그 사용
	// dao 잘 실행되는지 간단 테스트
	// out.print(list);
	pageContext.setAttribute("list", list);
%>
	<h2>카테고리별 상품 목록</h2>
	<table class="table table-striped">
		<tr id="productH">
			<th style="width:100px;">번호</th>
			<th>카테고리</th>
			<th>상품명</th>
			<th>상품코드</th>
			<th>가격</th>
		</tr>
		<!-- for (Product temp : list)  -->
		<!-- varStatus 속성에 변수 이름을 지정하면 index 값을 가져올수 있음. -->
		<c:forEach items="${list }" var="temp" varStatus="status">
			<tr id="product">
				<td style="width:100px;">${status.index }</td>
				<td>${temp.category }</td>
				<td>${temp.pname }</td>  <!-- 왼쪽 정렬 -->
				<td>${temp.pcode }</td>
				<td>
					<fmt:formatNumber value="${temp.price }" pattern="##,###,###"/>
				</td> <!-- 오른쪽 정렬 -->
			</tr>
		</c:forEach>
	</table>		
</body>
</html>
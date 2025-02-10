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
<link rel="stylesheet" href="css/table.css">
<title>프로젝트 2 : 3_core_for.jsp</title>
</head>
<body>
<h2>상품 카테고리</h2>
<!-- 3_parameter.jsp 의 forEach는 items 속성을 사용합니다.
     자바의 향상for문과 동작 방식이 같습니다. `리스트에서는 대부분 items 속성으로 반복합니다.`
     여기서는 기존 for문의 index 를 사용합니다.(형식만 참고하세요.)
     core 태그 속성 begin,end 설정 필요합니다.
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
	<table>
		<tr id="productH">
			<th style="width:100px;">번호</th>
			<th>카테고리</th>
			<th>상품명</th>
			<th>상품코드</th>
			<th>가격</th>
		</tr>
		<c:forEach begin="0" end="${list.size() }" var="i">
		<!-- i번째 항목을 가져와서(list.get(i)) temp 변수에 저장합니다. -->
			<c:set var="temp" value="${list[i] }"/>
			<tr id="product">
				<td style="width:100px;">${i+1 }</td>
				<td>${temp.category }</td>
				<td>${temp.pname }</td>  <!-- 왼쪽 정렬 -->
				<td>${temp.pcode }</td>
				<td>${temp.price }</td> <!-- 오른쪽 정렬 -->
			</tr>
		</c:forEach>
	</table>

		
		
</body>
</html>
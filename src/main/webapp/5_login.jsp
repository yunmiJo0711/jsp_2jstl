<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>5_login.jsp</title> <!-- 밑의 link 에서 bootstrap 부트스트랩 사용한게 나옴. css 스타일 시트 링크를 복사해 head 부분에 추가해준다. -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="css/form.css"/>
    <script type="text/javascript" src="js/login.js"></script>
  </head>
  <body>
	  <div class="container regForm">  
	  	<h2>로그인</h2>
	  	<hr/>
	  	<form action="5_loginProc.jsp" method="post" onsubmit="return checkValues()">
	  	
	  		<div class="form-floating mb-3">
			  <input type="text" class="form-control" name="userid"
			  id="floatingInput" placeholder="사용자 아이디를 입력하세요">
			  <span></span>  <!-- 필수 입력 입니다. 메시지 출력 -->
			  <label for="floatingInput">사용자 아이디</label>
			</div>
			
			<div class="form-floating">
			  <input type="password" class="form-control" name="password"
			  id="floatingPassword" placeholder="패스워드를 입력하세요.">
			  <span></span>
			  <label for="floatingPassword">패스워드</label>
			</div>
			<div id="fail"></div>
			<div class="centerDiv">
				<button type="button" class="btn btn-secondary" onclick="location.href='/2JSTL/'">홈</button>
				<button class="btn btn-dark">로그인</button>
				<!-- form 안에서는 type="submit" 생략 가능 -->
			</div>
	  	</form>
		<hr/>
	   </div> 
	<script type="text/javascript">
		const fail = '${param.fail}'  // ????
		console.log(fail)
		if(fail==='y')
			document.getElementById('fail').innerHTML
			='사용자 아이디 또는 패스워드 정보가 올바르지 않습니다.'
	</script>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  </body>
</html>
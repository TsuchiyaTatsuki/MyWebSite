<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<jsp:include page="/baselayout/head.html" />

</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />


	<nav aria-label="breadcrumb" style="padding-top: 10px;">
		<ol class="breadcrumb bg-white">
			<li class="breadcrumb-item"><a
				href="file:///C:/Users/USER/Documents/PersonalDevelopment/PersonalMock/Top.html">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">ログイン</li>
		</ol>
	</nav>

	<div style="margin-left: 40px; margin-right: 40px">

		<!-- ログインフォーム -->
		<div class="jumbotron row" style="padding: 5% 8%;">
			<!-- 左半分 -->
			<div class="col-md-7 border-right" style="padding-right: 10%;">
				<h4 align="center" style="padding-bottom: 5%;">既存のIDでログイン</h4>
				<c:if test="${loginErrorMessage != null}">
					<p class="text-danger">${loginErrorMessage}</p>
				</c:if>
				<c:if test="${logoutMesse != null}">
					<p class="text-danger">${logoutMesse}</p>
				</c:if>
				<form action="MyLoginResult" method="POST">
					<div class="form-group">
						<input name="loginId" type="txt" class="form-control" id="userId"
							placeholder="ID" required>
					</div>
					<div class="form-group" style="padding-bottom: 5%;">
						<input name="password" type="password" class="form-control"
							id="Password" placeholder="パスワード" required>
					</div>
					<button type="submit" class="btn btn-primary" name="action">ログイン</button>
				</form>
			</div>
			<!-- 左半分 -->
			<!-- 右半分 -->
			<div class="col-md-5" style="padding-left: 10%;">
				<h4 align="center" style="padding-bottom: 25%;">初めてご利用の方</h4>
				<div align="center" class="align-self-center">
					<a href="NewUser" role="button" class="btn btn-primary btn-lg">新規会員登録</a>
				</div>
			</div>
			<!-- 右半分 -->
		</div>
		<!-- ログインフォーム -->
	</div>


	<jsp:include page="/baselayout/footer.jsp" />
</body>
</html>
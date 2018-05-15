<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録確認</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<nav aria-label="breadcrumb" style="padding-top: 10px;">
		<ol class="breadcrumb bg-white">
			<li class="breadcrumb-item"><a
				href="Top">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">新規会員登録</li>
		</ol>
	</nav>
	<div style="margin-left: 15%; margin-right: 15%">

		<!-- 会員登録フォーム -->
		<div class="jumbotron" style="padding: 5% 8%;">
			<h4>登録情報確認</h4>
			<hr class="my-4">
			<form action="NewUserResult" method="POST">
				<div class="row">
					<label class="col-sm-4 col-form-label">名前</label>
					<div class="col-sm-8">
						<input name="name" type="text" class="form-control-plaintext"
							id="name" value="${udb.name}" readonly>
					</div>
				</div>
				<hr class="my-4">
				<div class="row">
					<label class="col-sm-4 col-form-label">生年月日</label>
					<div class="col-sm-8">
						<input name="birthDate" type="text" class="form-control-plaintext"
							id="birthDate" value="${udb.formatDate}" readonly>
					</div>
				</div>
				<hr class="my-4">
				<div class="row">
					<label class="col-sm-4 col-form-label">住所</label>
					<div class="col-sm-8">
						<input name="address" type="text" class="form-control-plaintext"
							id="address" value="${udb.address}" readonly>
					</div>
				</div>
				<hr class="my-4">
				<div class="row">
					<label class="col-sm-4 col-form-label">ログインID</label>
					<div class="col-sm-8">
						<input name="loginId" type="text" class="form-control-plaintext"
							id="loginId" value="${udb.loginId}" readonly>
					</div>
				</div>
				<hr class="my-4">
				<div class="row">
					<label class="col-sm-4 col-form-label">パスワード</label>
					<div class="col-sm-8">
						<input name="password" type="password" class="form-control-plaintext"
							id="password" value="${udb.password}" readonly>
					</div>
				</div>
				<hr class="my-4">
				<div class="row justify-content-around">
					<div class="col col-lg-4">
						<button type="submit" class="btn btn-primary btn-block"
							name="confirm_button" value="0">修正</button>
					</div>
					<div class="col col-lg-4">
						<button type="submit" class="btn btn-primary btn-block"
							name="confirm_button" value="1">登録完了</button>
					</div>
				</div>
			</form>
		</div>
		<!-- 会員登録フォーム -->

	</div>
	<jsp:include page="/baselayout/footer.jsp" />
</body>
</html>
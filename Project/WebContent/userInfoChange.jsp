<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録情報更新</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<nav aria-label="breadcrumb" style="padding-top: 10px;">
		<ol class="breadcrumb bg-white">
			<li class="breadcrumb-item"><a
				href="Top">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">登録情報更新</li>
		</ol>
	</nav>
	<div style="margin-left: 15%; margin-right: 15%">

		<!-- 情報変更フォーム -->
		<div class="jumbotron" style="padding: 5% 8%;">
			<h4>登録情報更新</h4>
			<c:if test="${validationMessage != null }">
				<p class="text-danger">${validationMessage}</p>
			</c:if>

			<hr class="my-4">
			<form action="UserInfoChange" method="POST">
				<div class="form-group row">
					<label for="staticEmail" class="col-sm-4 col-form-label">名前</label>
					<div class="col-sm-8">
						<input name="name" type="text" class="form-control"
							id="staticEmail" value="${udb.name }">
					</div>
				</div>
				<hr class="my-4">
				<div class="form-group row">
					<label for="staticEmail" class="col-sm-4 col-form-label">生年月日</label>
					<div class="col-sm-8">
						<input name="birthDate" type="date" class="form-control"
							id="staticEmail" value="${udb.birthDate }">
					</div>
				</div>
				<hr class="my-4">
				<div class="form-group row">
					<label for="staticEmail" class="col-sm-4 col-form-label">住所</label>
					<div class="col-sm-8">
						<input name="address" type="text" class="form-control"
							id="staticEmail" value="${udb.address }">
					</div>
				</div>
				<hr class="my-4">
				<div class="form-group row">
					<label for="staticEmail" class="col-sm-4 col-form-label">ログインID</label>
					<div class="col-sm-8">
						<input name="loginId" type="text" class="form-control"
							id="staticEmail" value="${udb.loginId }">
					</div>
				</div>

				<hr class="my-4">
				<div class="row justify-content-md-center">
					<div class="col col-lg-6">
						<button type="submit" class="btn btn-primary btn-block" name="action">更新</button>
					</div>
				</div>
			</form>
		</div>
		<!-- 情報変更フォーム -->
	</div>
	<jsp:include page="/baselayout/footer.jsp" />
</body>
</html>
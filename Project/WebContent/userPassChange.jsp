<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード変更</title>
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

		<!-- パスワード変更フォーム -->
		<div class="jumbotron" style="padding: 5% 8%;">
			<h4>パスワード変更</h4>
			<c:if test="${validationMessage != null }">
				<p class="text-danger">${validationMessage}</p>
			</c:if>
			<hr class="my-4">
			<form action="UserPassChange" method="POST">
				<div class="form-group row">
					<label for="inputPassword" class="col-sm-4 col-form-label">新しいパスワード</label>
					<div class="col-sm-8">
						<input name="newPassword" type="password" class="form-control" id="newPassword"
							placeholder="新しいパスワード">
					</div>
				</div>

				<hr class="my-4">
				<div class="form-group row">
					<label for="inputPassword" class="col-sm-4 col-form-label">現在のパスワード</label>
					<div class="col-sm-8">
						<input name="password" type="password" class="form-control" id="password"
							placeholder="現在のパスワード">
					</div>
				</div>
				<hr class="my-4">
				<div class="form-group row">
					<label for="inputPassword" class="col-sm-4 col-form-label">現在のパスワード(確認)</label>
					<div class="col-sm-8">
						<input name="passwordCon" type="password" class="form-control" id="passwordCon"
							placeholder="現在のパスワード(確認)">
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
		<!-- パスワード変更フォーム -->
	</div>
	<jsp:include page="/baselayout/footer.jsp" />
</body>
</html>
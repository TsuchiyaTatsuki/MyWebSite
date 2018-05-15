<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録情報</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<nav aria-label="breadcrumb" style="padding-top: 10px;">
		<ol class="breadcrumb bg-white">
			<li class="breadcrumb-item"><a
				href="Top">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">登録情報</li>
		</ol>
	</nav>
	<div style="margin-left: 15%; margin-right: 15%">
		<!-- 会員登録フォーム -->
		<div class="jumbotron" style="padding: 5% 8%;">
			<h4>登録情報確認</h4>
			<c:if test="${updateMesse != null }">
				<p class="text-primary">${updateMesse}</p>
			</c:if>
			<hr class="my-4">
			<form>
				<div class="row">
					<label class="col-sm-4 col-form-label">基本情報</label>
					<div class="col-sm-6">
						<input type="text" class="form-control-plaintext" id="staticEmail"
							value="${udb.name }" readonly> <input type="text"
							class="form-control-plaintext" id="staticEmail" value="${udb.formatDate}" readonly>
						<input type="text" class="form-control-plaintext" id="staticEmail"
							value="${udb.address }" readonly> <input type="text"
							class="form-control-plaintext" id="staticEmail" value="${udb.loginId }"
							readonly>
					</div>
					<div class="col-sm-2 ml-auto align-self-center">
						<a
							href="UserInfoChange">
							<button type="button" class="btn btn-outline-secondary btn-sm">
								<i class="fas fa-redo-alt"></i> 変更
							</button>
						</a>
					</div>
				</div>
				<hr class="my-4">
				<div class="row">
					<label class="col-sm-4 col-form-label">パスワード</label>
					<div class="col-sm-6">
						<input type="password" class="form-control-plaintext"
							id="staticEmail" value="${udb.password }" style="width:7em;" readonly>
					</div>
					<div class="col-sm-2 ml-auto align-self-center">
						<a
							href="UserPassChange">
							<button type="button" class="btn btn-outline-secondary btn-sm">
								<i class="fas fa-redo-alt"></i> 変更
							</button>
						</a>
					</div>
				</div>
				<hr class="my-4">
			</form>
		</div>
		<!-- 会員登録フォーム -->
	</div>

	<jsp:include page="/baselayout/footer.jsp" />
</body>
</html>
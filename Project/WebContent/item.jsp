<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<nav aria-label="breadcrumb" style="padding-top: 10px;">
		<ol class="breadcrumb bg-white">
			<li class="breadcrumb-item"><a href="Top">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">商品詳細</li>
		</ol>
	</nav>

	<div class="row justify-content-end">
		<div class="col-4" style="padding-bottom: 35px;">
			<form action="Cart" method="POST">
				<input name="itemId" type="hidden" value="${itemdb.id }">
				<button type="submit" class="btn btn-secondary">
					<i class="fas fa-shopping-cart fa-lg text-white"></i> カートへ入れる
				</button>
			</form>
		</div>
	</div>

	<div class="row justify-content-around" style="padding-bottom: 30px;">
		<div class="col-sm-5">
			<img src="img/${itemdb.fileName }" class="img-fluid"
				alt="Responsive image">
		</div>
		<div class="col-sm-5">
			<h4>${itemdb.name }</h4>
			<h4>${itemdb.formatPrice }</h4>
			<h6>${itemdb.detail }</h6>
		</div>
	</div>
	<div style="padding-top: 2rem;">
		<hr class="my-4">
		<h5>関連するアイテム</h5>
		<div class="row" style="padding: 20px;">
			<!-- 商品カード -->
			<c:forEach var="item" items="${relationItemList }">
				<div class="col-2">
					<div class="card" style="width: 9rem; border: none;">
						<a href="Item?itemId=${item.id }"><img
							class="card-img-top" src="img/${item.fileName }"
							alt="Card image cap" style="height: 9rem;"></a>
						<div class="card-body">
							<p class="card-text">${item.name }<br>${item.formatPrice }</p>
						</div>
					</div>
				</div>
			</c:forEach>
			<!-- 商品カード -->
		</div>
	</div>
	<jsp:include page="/baselayout/footer.jsp" />
</body>
</html>
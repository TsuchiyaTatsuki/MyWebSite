<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<nav aria-label="breadcrumb" style="padding-top: 10px;">
		<ol class="breadcrumb bg-white">
			<li class="breadcrumb-item"><a href="Top">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">カート</li>
		</ol>
	</nav>
	<c:choose>
		<c:when test="${cart.size() == 0 }">
			<div class="row justify-content-md-center" style="padding-top: 3rem;">
				<div class="col align-self-center">
					<h5 class="text-center">カートに商品が入っていません。</h5>
				</div>
			</div>
			<div class="row justify-content-md-center" style="padding-top: 3rem;">
				<div class="col col-lg-4">
					<a class="btn btn-primary btn-block" href="Top">買い物を続ける</a>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="row">
				<div class="col col-8">
					<!-- アイテム -->
					<hr class="my-4">
					<c:forEach var="itemdb" items="${cart}" varStatus="status">
						<div class="row">
							<div class="col col-3 align-self-center">
								<div style="overflow: hidden; width: 9rem; height: 9rem;">
									<a href="Item?itemId=${itemdb.id }"> <img
										src="img/${itemdb.fileName }" class="img-fluid"
										alt="Responsive image">
									</a>
								</div>
							</div>
							<div class="col col-1"></div>
							<div class="col col-4 align-self-center">
								<h5>
									<a class="text-dark" style="text-decoration: none;"
										href="Item?itemId=${itemdb.id }"> ${itemdb.name } </a>
								</h5>
							</div>
							<div class="col col-4 align-self-center" align="right">
								<h5>${itemdb.formatPrice }</h5>
								<a href="CartItemDelete?number=${status.index }">
									<button type="button" class="btn btn-secondary">
										<i class="far fa-trash-alt fa-lg"></i> 削除
									</button>
								</a>
							</div>
						</div>
						<hr class="my-4">

					</c:forEach>
					<!-- アイテム -->
					<h5 align="right" style="padding-bottom: 30px;">商品合計:
						${formatSubTotalPrice }</h5>
				</div>

				<div class="col col-4">
					<div class="jumbotron shadow" style="padding: 5% 8%;">
						<h5 align="right" style="padding-top: 10px; padding-bottom: 10px;">小計
							(${cart.size() }点): ${formatSubTotalPrice }</h5>

						<div class="row justify-content-md-center"
							style="padding-bottom: 20px;">
							<div class="col col-lg-10">
								<a href="Buy" class="btn btn-primary btn-block btn-lg"
									role="button">レジへ進む</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	<div style="padding-top: 2rem;">
		<hr class="my-4">
		<h5>チェックしたアイテム</h5>
		<div class="row" style="padding: 20px;">
			<!-- 商品カード -->
			<c:forEach var="item" items="${readItemList }">
				<div class="col-2">
					<div class="card" style="width: 9rem; border: none;">
						<div style="overflow: hidden; height: 9rem;">
							<a href="Item?itemId=${item.id }"><img class="card-img-top"
								src="img/${item.fileName }" alt="Card image cap"></a>
						</div>
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
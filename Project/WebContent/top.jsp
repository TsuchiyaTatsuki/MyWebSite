<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップページ</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<jsp:include page="/baselayout/navber.jsp" />
	<div class="row">
		<!-- サイドメニュー -->
		<div class="col-sm-3">
			<div id="sidebar">
				<div class="sidebar__inner">
					<div class="list-group list-group-flush" style="padding-top: 2rem;">
						<li class="list-group-item">
							<h5>
								<strong>カテゴリ</strong>
							</h5>
						</li>
						<c:forEach var="gcate" items="${cateList }">
							<a href="ItemSearchResult?categoryName=${gcate.name }"
								class="list-group-item list-group-item-action">${gcate.name }</a>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<!-- サイドメニュー -->
		<!-- メインコンテンツ -->
		<div class="col-md-9" style="padding-top: 25px;">
			<!-- スライダー -->
			<c:choose>
				<c:when test="${genderId == null || genderId == 2 }">
					<div id="carouselExampleFade" class="carousel slide carousel-fade"
						data-ride="carousel" style="padding-bottom: 20px"
						data-interval="4000">
						<div class="carousel-inner" style="height: 340px;">
							<div class="carousel-item active">
								<a href="ItemSearchResult?categoryName=トップス"><img
									class="d-block w-100" src="img/publicdomainq-0018423cpl.jpg"
									alt="First slide"></a>
							</div>
							<div class="carousel-item">
								<a href="ItemSearchResult?categoryName=パンツ"><img
									class="d-block w-100" src="img/gahag-0006435869-1.jpg"
									alt="Second slide"></a>
							</div>
							<div class="carousel-item">
								<a href="ItemSearchResult?categoryName=トップス"><img
									class="d-block w-100" src="img/tops.jpg" alt="Third slide"></a>
							</div>
							<div class="carousel-item">
								<a href="ItemSearchResult?categoryName=パンツ"><img
									class="d-block w-100" src="img/pants.jpg" alt="fourth slide"></a>
							</div>
						</div>
						<a class="carousel-control-prev" href="#carouselExampleFade"
							role="button" data-slide="prev"> <span
							class="carousel-control-prev-icon" aria-hidden="true"></span> <span
							class="sr-only">Previous</span>
						</a> <a class="carousel-control-next" href="#carouselExampleFade"
							role="button" data-slide="next"> <span
							class="carousel-control-next-icon" aria-hidden="true"></span> <span
							class="sr-only">Next</span>
						</a>
					</div>
				</c:when>
				<c:when test="${genderId == 0 }">
					<div id="carouselExampleFade" class="carousel slide carousel-fade"
						data-ride="carousel" style="padding-bottom: 20px">
						<div class="carousel-inner" style="height: 340px;">
							<div class="carousel-item active">
								<a href="ItemSearchResult?categoryName=トップス"><img
									class="d-block w-100" src="img/publicdomainq-0018423cpl.jpg"
									alt="First slide"></a>
							</div>
							<div class="carousel-item">
								<a href="ItemSearchResult?categoryName=パンツ"><img
									class="d-block w-100" src="img/gahag-0006435869-1.jpg"
									alt="Second slide"></a>
							</div>
							<div class="carousel-item">
								<a href="ItemSearchResult?categoryName=シューズ"><img
									class="d-block w-100" src="img/shoes.jpg" alt="Third slide"></a>
							</div>
						</div>
						<a class="carousel-control-prev" href="#carouselExampleFade"
							role="button" data-slide="prev"> <span
							class="carousel-control-prev-icon" aria-hidden="true"></span> <span
							class="sr-only">Previous</span>
						</a> <a class="carousel-control-next" href="#carouselExampleFade"
							role="button" data-slide="next"> <span
							class="carousel-control-next-icon" aria-hidden="true"></span> <span
							class="sr-only">Next</span>
						</a>
					</div>
				</c:when>
				<c:when test="${genderId == 1 }">
					<div id="carouselExampleFade" class="carousel slide carousel-fade"
						data-ride="carousel" style="padding-bottom: 20px">
						<div class="carousel-inner" style="height: 340px;">
							<div class="carousel-item active">
								<a href="ItemSearchResult?categoryName=トップス"><img
									class="d-block w-100" src="img/tops.jpg" alt="First slide"></a>
							</div>
							<div class="carousel-item">
								<a href="ItemSearchResult?categoryName=アウター"><img
									class="d-block w-100" src="img/pants.jpg" alt="Second slide"></a>
							</div>
							<div class="carousel-item">
								<a href="ItemSearchResult?categoryName=ワンピース"><img
									class="d-block w-100" src="img/Kazukihiro18117018.jpg"
									alt="Third slide"></a>
							</div>
						</div>
						<a class="carousel-control-prev" href="#carouselExampleFade"
							role="button" data-slide="prev"> <span
							class="carousel-control-prev-icon" aria-hidden="true"></span> <span
							class="sr-only">Previous</span>
						</a> <a class="carousel-control-next" href="#carouselExampleFade"
							role="button" data-slide="next"> <span
							class="carousel-control-next-icon" aria-hidden="true"></span> <span
							class="sr-only">Next</span>
						</a>
					</div>
				</c:when>
			</c:choose>
			<!-- スライダー -->
			<div style="padding: 10px;">
				<c:choose>
					<c:when test="${genderId == null || genderId == 2 }">
						<h5>ランキング</h5>
						<hr class="my-4">
						<h5>メンズ</h5>
						<div class="row">
							<c:forEach var="item" items="${rankGender0}">
								<div class="col-3">
									<div class="card" style="width: 10rem; border: none;">
										<a href="Item?itemId=${item.id }"><img
											class="card-img-top" src="img/${item.fileName }"
											style="height: 10rem;"></a>
										<div class="card-body">
											<p class="card-text">${item.name }<br>${item.formatPrice }</p>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
						<h5 style="padding-top: 1rem;">レディース</h5>
						<div class="row">
							<c:forEach var="item" items="${rankGender1}">
								<div class="col-3">
									<div class="card" style="width: 10rem; border: none;">
										<a href="Item?itemId=${item.id }"><img
											class="card-img-top" src="img/${item.fileName }"
											style="height: 10rem;"></a>
										<div class="card-body">
											<p class="card-text">${item.name }<br>${item.formatPrice }</p>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</c:when>
					<c:otherwise>
						<h5>ランキング</h5>
						<hr class="my-4">
						<div class="row">
							<c:forEach var="item" items="${rankGender}">
								<div class="col-3">
									<div class="card" style="width: 10rem; border: none;">
										<a href="Item?itemId=${item.id }"><img
											class="card-img-top" src="img/${item.fileName }"
											style="height: 10rem;"></a>
										<div class="card-body">
											<p class="card-text">${item.name }<br>${item.formatPrice }</p>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</c:otherwise>
				</c:choose>
				<h5 style="padding-top: 1rem;">おすすめアイテム</h5>
				<hr class="my-4">
				<div class="row">
					<c:forEach var="item" items="${itemList }">
						<div class="col-3">
							<div class="card" style="width: 10rem; border: none;">
								<a href="Item?itemId=${item.id }"><img class="card-img-top"
									src="img/${item.fileName }" style="height: 10rem;"></a>
								<div class="card-body">
									<p class="card-text">${item.name }<br>${item.formatPrice }</p>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<!-- メインコンテンツ -->
	<jsp:include page="/baselayout/footer.jsp" />
</body>
</html>
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
			<div class="list-group list-group-flush">
				<c:choose>
					<c:when test="${genderId == 2 || genderId == null}">
						<li class="list-group-item" style="padding-top: 40px;">
							<h5>
								<strong>メンズ</strong>
							</h5>
						</li>
						<c:forEach var="gcate" items="${cateList }">
							<c:if test="${gcate.gender == 0 }">
								<a href="" class="list-group-item list-group-item-action">${gcate.name }</a>
							</c:if>
						</c:forEach>
						<li class="list-group-item" style="padding-top: 40px;">
							<h5>
								<strong>レディース</strong>
							</h5>
						</li>
						<c:forEach var="gcate" items="${cateList }">
							<c:if test="${gcate.gender == 1 }">
								<a href="" class="list-group-item list-group-item-action">${gcate.name }</a>
							</c:if>
						</c:forEach>
					</c:when>
					<c:otherwise>
<li class="list-group-item" style="padding-top: 40px;">
							<h5>
								<strong>カテゴリ</strong>
							</h5>
						</li>
						<c:forEach var="gcate" items="${cateList }">
								<a href="" class="list-group-item list-group-item-action">${gcate.name }</a>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<!-- サイドメニュー -->

		<!-- メインコンテンツ -->
		<div class="col-md-9" style="padding-top: 25px;">

			<!-- スライダー -->
			<div id="carouselExampleFade" class="carousel slide carousel-fade"
				data-ride="carousel" style="padding-bottom: 20px">
				<div class="carousel-inner" style="height: 340px;">
					<div class="carousel-item active">
						<a
							href="file:///C:/Users/USER/Documents/PersonalDevelopment/PersonalMock/itemSearchResult.html"><img
							class="d-block w-100" src="img/publicdomainq-0018423cpl.jpg"
							alt="First slide"></a>
					</div>
					<div class="carousel-item">
						<a
							href="file:///C:/Users/USER/Documents/PersonalDevelopment/PersonalMock/itemSearchResult.html"><img
							class="d-block w-100" src="img/gahag-0006435869-1.jpg"
							alt="Second slide"></a>
					</div>
					<div class="carousel-item">
						<a
							href="file:///C:/Users/USER/Documents/PersonalDevelopment/PersonalMock/itemSearchResult.html"><img
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
			<!-- スライダー -->
			<div class="row" style="padding: 10px;">
				<!-- 商品カード -->
				<c:forEach var="item" items="${itemList}">
					<div class="col-3">
						<div class="card" style="width: 10rem; border: none;">
							<c:choose>
								<c:when test="${item.fileName != null }">
									<a href="Item?itemId=${item.id }"><img class="card-img-top"
										src="img/${item.fileName }" style="height: 10rem;"></a>
								</c:when>
								<c:otherwise>
									<a href="Item?itemId=${item.id }"><img class="card-img-top"
										src="img/fuku_tatamu.png" style="height: 10rem;"></a>
								</c:otherwise>
							</c:choose>
							<div class="card-body">
								<p class="card-text">${item.name }<br>${item.formatPrice }</p>
							</div>
						</div>
					</div>
				</c:forEach>
				<!-- 商品カード -->
			</div>
		</div>
	</div>
	<!-- メインコンテンツ -->
	<jsp:include page="/baselayout/footer.jsp" />
</body>
</html>
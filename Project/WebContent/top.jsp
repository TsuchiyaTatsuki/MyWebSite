<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="beans.MyItemDataBeans"%>
<%@page import=" java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップページ</title>
<jsp:include page="/baselayout/head.html" />
<%
	ArrayList<MyItemDataBeans> itemList = (ArrayList<MyItemDataBeans>) request.getAttribute("itemList");
%>
</head>

<body>
	<jsp:include page="/baselayout/header.jsp" />
	</div>
	</div>

	<!-- ナビバー -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container">
			<div style="margin-left: 7%; margin-right: 7%">
				<div class="collapse navbar-collapse" id="navbarText">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item active" style="padding-right: 20px;"><a
							class="nav-link" href="#">すべて <span class="sr-only">(現位置)</span></a>
						</li>
						<li class="nav-item" style="padding-right: 20px;"><a
							class="nav-link" href="#">メンズ</a></li>
						<li class="nav-item"><a class="nav-link" href="#">レディース</a></li>
					</ul>
				</div>
			</div>
		</div>
	</nav>


	<!-- ナビバー -->

	<div class="container">
		<div style="margin-left: 6%; margin-right: 6%">
			<div class="row">

				<!-- サイドメニュー -->
				<div class="col-sm-3">
					<div class="list-group list-group-flush">

						<li class="list-group-item" style="padding-top: 40px;"><h5>
								<strong>カテゴリ</strong>
							</h5></li> <a
							href="file:///C:/Users/USER/Documents/PersonalDevelopment/PersonalMock/itemSearchResult.html"
							class="list-group-item list-group-item-action">カテゴリ1</a> <a
							href="file:///C:/Users/USER/Documents/PersonalDevelopment/PersonalMock/itemSearchResult.html"
							class="list-group-item list-group-item-action">カテゴリ2</a> <a
							href="file:///C:/Users/USER/Documents/PersonalDevelopment/PersonalMock/itemSearchResult.html"
							class="list-group-item list-group-item-action">カテゴリ3</a> <a
							href="file:///C:/Users/USER/Documents/PersonalDevelopment/PersonalMock/itemSearchResult.html"
							class="list-group-item list-group-item-action">カテゴリ4</a> <a
							href="file:///C:/Users/USER/Documents/PersonalDevelopment/PersonalMock/itemSearchResult.html"
							class="list-group-item list-group-item-action">カテゴリ5</a>
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
						<%
							for (MyItemDataBeans item : itemList) {
						%>
						<div class="col-3">
							<div class="card" style="width: 10rem; border: none;">
								<a
									href="file:///C:/Users/USER/Documents/PersonalDevelopment/PersonalMock/item.html"><img
									class="card-img-top"
									src="<%if (item.getFileName() == null) {%>
										img/fuku_tatamu.png
										<%} else {%>
										<%}%>
									"></a>
								<div class="card-body">
									<p class="card-text"><%=item.getName()%><br><%=item.getFormatPrice()%></p>
								</div>
							</div>
						</div>
						<%
							}
						%>
						<!-- 商品カード -->

					</div>
				</div>
			</div>
			<!-- メインコンテンツ -->

			<jsp:include page="/baselayout/footer.jsp" />
</body>
</html>
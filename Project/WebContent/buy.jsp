<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入確認</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<h4 style="padding-top: 30px;">注文内容の確認</h4>
	<div class="row">
		<div class="col col-8">
			<!-- お届け先 -->
			<hr class="my-4">
			<div class="row">
				<div class="col col-3 align-self-center">
					<div style="padding-left: 15px;">お届け先</div>
				</div>
				<div class="col col-7 align-self-center">
					<h6>${udb.name }様</h6>
					<h6>${udb.address }</h6>
				</div>
				<div class="col col-2 align-self-center" align="right">
					<a href="UserInfoChange">
						<button type="button" class="btn btn-outline-secondary btn-sm">
							<i class="fas fa-redo-alt"></i> 変更
						</button>
					</a>
				</div>
			</div>
			<hr class="my-4">
			<!-- お届け先 -->

			<!-- 配送方法 -->
			<div class="row">
				<div class="col col-3 align-self-center">
					<div style="padding-left: 15px;">配送方法</div>
				</div>
				<div class="col col-5 align-self-center">
					<h6>${dmdb.name }</h6>
				</div>
				<div class="col col-2 align-self-center" align="right">
					<h6>${dmdb.formatPrice }</h6>
				</div>
				<div class="col col-2 align-self-center" align="right">
					<div class="dropdown">
						<button class="btn btn-outline-secondary btn-sm dropdown-toggle"
							type="button" id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
							<i class="fas fa-redo-alt"></i> 変更
						</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<c:forEach var="dm" items="${dmList }">
								<a class="dropdown-item" href="DeliveryMethod?id=${dm.id }">${dm.name }</a>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
			<hr class="my-4">
			<!-- 配送方法 -->
			<h6 style="padding-left: 15px; pedding-top: 25px;">お届け商品</h6>
			<div style="padding-left: 15px;">
				<div class="card" style="border-radius: 10px;">
					<div class="card-body">
						<!-- アイテム -->
						<div class="row">
							<div class="col col-10">
								<c:forEach var="itemdb" items="${cart }" varStatus="status">
									<div class="row">
										<div class="col col-4 align-self-center">
											<div style="overflow: hidden; width: 8rem; height: 8rem;">
												<img src="img/${itemdb.fileName }" class="img-fluid"
													alt="Responsive image">
											</div>
										</div>
										<div class="col col-5 align-self-center">
											<h6>${itemdb.name }</h6>
										</div>
										<div class="col col-3 align-self-center" align="right">
											<h6>${itemdb.formatPrice }</h6>
										</div>
									</div>
									<c:if test="${!status.last }">
										<hr class="my-4">
									</c:if>
								</c:forEach>
							</div>
							<div class="col col-2 align-self-center" align="right">
								<a href="Cart">
									<button type="button" class="btn btn-outline-secondary btn-sm">
										<i class="fas fa-redo-alt"></i> 変更
									</button>
								</a>
							</div>
						</div>
						<hr class="my-4">
						<!-- アイテム -->
						<div class="row">
							<div class="col col-6 align-self-center">
								<h6>合計</h6>
							</div>
							<div class="col col-6 align-self-center" align="right">
								<h6>${cart.size() }点</h6>
							</div>
						</div>
					</div>
				</div>
			</div>
			<h5 align="right" style="padding: 20px">商品合計:
				${formatSubTotalPrice }</h5>
		</div>
		<div class="col col-4">
			<div class="jumbotron shadow" style="padding: 5% 8%;">
				<div class="row">
					<div class="col col-8 align-self-center">
						<h6>商品合計</h6>
					</div>
					<div class="col col-4 align-self-center">
						<h6 align="right" style="padding-top: 10px; padding-bottom: 10px;">${formatSubTotalPrice }</h6>
					</div>
				</div>
				<div class="row">
					<div class="col col-8 align-self-center">
						<h6>送料(${dmdb.name })</h6>
					</div>
					<div class="col col-4 align-self-center">
						<h6 align="right" style="padding-top: 10px; padding-bottom: 10px;">${dmdb.formatPrice }</h6>
					</div>
				</div>
				<hr class="my-4">
				<div class="row">
					<div class="col col-7 align-self-center">
						<h5>合計</h5>
					</div>
					<c:choose>
						<c:when test="${formatTotalPrice != null}">
							<div class="col col-5 align-self-center">
								<h5 align="right"
									style="padding-top: 10px; padding-bottom: 10px;">${formatTotalPrice }</h5>
							</div>
						</c:when>
						<c:otherwise>
							<div class="col col-5 align-self-center">
								<h5 align="right"
									style="padding-top: 10px; padding-bottom: 10px;">${formatSubTotalPrice }</h5>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="row justify-content-md-center"
					style="padding-bottom: 20px; padding-top: 20px;">
					<div class="col col-lg-10">
						<a href="BuyResult"
							class="btn btn-primary btn-block btn-lg shadow-sm" role="button">
							注文を確定</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/baselayout/footer.jsp" />
</body>
</html>
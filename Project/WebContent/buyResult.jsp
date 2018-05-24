<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入完了</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<h4 class="text-center" style="padding-top: 100px;">
		<strong>${lud.name } 様<br>
			<div style="padding-top: 20px;">ご注文の受付を完了しました</div></strong>
	</h4>

	<!-- 注文内容 -->
	<div style="padding: 40px;">
		<div class="card">
			<div class="card-header">
				<div class="row">
					<h5 class="align-self-center" style="padding-left: 10px;">
						<strong>ご注文内容</strong>
					</h5>
					<p class="ml-auto align-self-center" style="padding-right: 15px;">${buyResult.formatDate }</p>
					<p class="align-self-center" style="padding-right: 10px;">ご注文番号:${buyResult.id }</p>
				</div>
			</div>
			<div class="card-body">
				<!-- アイテム -->
				<c:forEach var="idb" items="${buyItemList }">
					<div class="row">
						<div class="col col-2 align-self-center">
							<div style="overflow: hidden; width: 9rem; height: 9rem;">
								<img src="img/${idb.fileName }" class="img-fluid"
									alt="Responsive image">
							</div>
						</div>
						<div class="col col-1"></div>
						<div class="col col-5 align-self-center">
							<h5>${idb.name }</h5>
						</div>
						<div class="ml-auto align-self-center"
							style="padding-right: 15px;">
							<h5>${idb.formatPrice }</h5>
						</div>

					</div>
					<hr class="my-4">
				</c:forEach>
				<!-- アイテム -->
				<div class="row">
					<div class="ml-auto align-self-center" style="padding-right: 17px;">
						<h5>小計: ${formatSubTotalPrice }</h5>
					</div>
				</div>
				<hr class="my-4">
				<div class="row">
					<div class="col col-3 align-self-center">
						<h5>配送方法</h5>
					</div>
					<div class="col col-5 align-self-center">
						<h5>${buyResult.deliveryMethodName }</h5>
					</div>
					<div class="ml-auto align-self-center" style="padding-right: 17px;">
						<h5>${buyResult.formatDeliveryMethodPrice }</h5>
					</div>

				</div>

			</div>
			<div class="card-footer">
				<div class="row" style="padding-left: 17px; padding-right: 17px;">
					<div class="ml-auto align-self-center" style="padding-right: 40px;">
						<h5>
							<strong>合計</strong>
						</h5>
					</div>
					<div class="align-self-center">
						<h5>
							<strong>${buyResult.formatPrice }</strong>
						</h5>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 注文内容 -->
	<jsp:include page="/baselayout/footer.jsp" />
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文履歴</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<nav aria-label="breadcrumb" style="padding-top: 10px;">
		<ol class="breadcrumb bg-white">
			<li class="breadcrumb-item"><a
				href="Top">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">注文履歴</li>
		</ol>
	</nav>
	<div style="margin-left: 40px; margin-right: 40px">
		<div class="jumbotron" style="padding: 5% 8%;">
			<div class="row">
				<div class="col-sm">
					<h4>注文履歴</h4>
				</div>
				<div class="ml-auto">
					<h6>件数: ${buyHistoryCount }件</h6>
				</div>
			</div>
			<c:choose>
				<c:when test="${buyHistoryCount == 0 }">
					<div class="row justify-content-md-center"
						style="padding-top: 3rem;">
						<div class="col align-self-center">
							<h5 class="text-center">注文履歴がありません。</h5>
						</div>
					</div>
					<div class="row justify-content-md-center"
						style="padding-top: 3rem;">
						<div class="col col-lg-4">
							<a class="btn btn-primary btn-block" href="Top">買い物を続ける</a>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<!-- buyList -->
					<c:forEach var="buyDataBeans" items="${buyList}">
						<div style="padding-top: 30px;">
							<div class="container bg-white">
								<div class="row p-4 border-bottom">
									<div class="col-sm-4">
										<h6>注文日 :${buyDataBeans.formatDate }</h6>
										<h6>注文番号:${buyDataBeans.id }</h6>
									</div>
									<div class="col-sm-8 border-left">
										<c:forEach var="idb"
											items="${buyItemList.get(buyList.indexOf(buyDataBeans))}">
											<div class="row" style="padding-bottom: 10px;">
												<div class="col-sm-4">
													<img src="img/${idb.fileName }" alt="..."
														class="img-thumbnail">
												</div>
												<div class="col-sm-8">
													<h5>${idb.name }</h5>
													<h5>${idb.formatPrice }</h5>
													<h6>${idb.category }</h6>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
								<div class="row p-2">
									<h5 class="ml-auto">配送方法:
										${buyDataBeans.deliveryMethodName }</h5>
									<h5 style="padding-left: 20px; padding-right: 20px;">合計金額:
										${buyDataBeans.formatPrice }</h5>
								</div>
							</div>
						</div>
					</c:forEach>
					<!-- buyList -->
				</c:otherwise>
			</c:choose>
			<div style="padding-top: 30px;">
				<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-end">
						<c:choose>
							<c:when test="${page == null || page == 1 }">
								<li class="page-item disabled"><a
									class="page-link border-0"><i class="fas fa-angle-left"></i></a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link border-0"
									href="UserBuyHistory?page=${page - 1 }"><i
										class="fas fa-angle-left"></i></a></li>
							</c:otherwise>
						</c:choose>
						<c:forEach var="i" begin="1" end="${pageMax }" step="1">
							<li class="page-item"><a class="page-link border-0"
								href="UserBuyHistory?page=${i }">${i }</a></li>
						</c:forEach>
						<c:choose>
							<c:when test="${pageMax == page }">
								<li class="page-item disabled"><a
									class="page-link border-0"><i class="fas fa-angle-right"></i></a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link border-0"
									href="UserBuyHistory?page=${page + 1 }"><i
										class="fas fa-angle-right"></i></a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</nav>
			</div>
		</div>
	</div>
	<jsp:include page="/baselayout/footer.jsp" />
</body>
</html>
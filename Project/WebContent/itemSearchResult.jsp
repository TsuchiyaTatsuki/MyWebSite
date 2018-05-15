<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索結果</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<nav aria-label="breadcrumb" style="padding-top: 10px;">
		<ol class="breadcrumb bg-white">
			<li class="breadcrumb-item"><a
				href="Top">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">検索結果</li>
		</ol>
	</nav>
	<div class="row">
		<!-- サイドメニュー -->
		<div class="col-sm-3">
			<div class="list-group list-group-flush" style="padding-bottom: 30px">
				<form action="ItemSearchResult" method="POST">
					<li class="list-group-item list-group-item-secondary"><h6>検索結果</h6>
						<div class="row" style="padding-left: 15px;">
							<h4>${itemCount }</h4>
							<h6 style="padding-top: 9px;">件</h6>
						</div></li>
					<c:if test="${!searchWord.equals(\"\")}">
						<li class="list-group-item list-group-item-secondary"
							style="padding-top: 40px;"><h5>
								<strong>検索ワード</strong>
							</h5> "${searchWord }"
							<button name="close" type="submit" class="close"
								aria-label="Close" value="searchWordClose">
								<span aria-hidden="true">&times;</span>
							</button></li>
					</c:if>

					<li class="list-group-item list-group-item-secondary"
						style="padding-top: 40px;"><h5>
							<strong>性別</strong>
						</h5></li>
					<c:choose>
						<c:when test="${genderId == 2 }">
							<a href="ItemSearchResult?genderId=0"
								class="list-group-item list-group-item-action list-group-item-secondary">メンズ</a>
							<a href="ItemSearchResult?genderId=1"
								class="list-group-item list-group-item-action list-group-item-secondary">レディース</a>
						</c:when>
						<c:when test="${genderId == 0 }">
							<a
								class="list-group-item list-group-item-action list-group-item-secondary">メンズ
								<button name="close" type="submit" class="close"
									aria-label="Close" value="genderClose">
									<span aria-hidden="true">&times;</span>
								</button>
							</a>
							<a href="ItemSearchResult?genderId=1"
								class="list-group-item list-group-item-action list-group-item-secondary">レディース</a>
						</c:when>
						<c:when test="${genderId == 1 }">
							<a href="ItemSearchResult?genderId=0"
								class="list-group-item list-group-item-action list-group-item-secondary">メンズ</a>
							<a
								class="list-group-item list-group-item-action list-group-item-secondary">レディース
								<button name="close" type="submit" class="close"
									aria-label="Close" value="genderClose">
									<span aria-hidden="true">&times;</span>
								</button>
							</a>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${genderId == 2 }">
							<li class="list-group-item list-group-item-secondary"
								style="padding-top: 40px;"><h5>
									<strong>メンズ</strong>
								</h5></li>
							<c:forEach var="gcate" items="${cateList }">
								<c:if test="${gcate.gender == 0 }">
									<c:choose>
										<c:when test="${categoryId == gcate.id }">
											<a
												class="list-group-item list-group-item-action list-group-item-secondary">${gcate.name }<button
													name="close" type="submit" class="close" aria-label="Close"
													value="categoryClose">
													<span aria-hidden="true">&times;</span>
												</button></a>
										</c:when>
										<c:otherwise>
											<a href="ItemSearchResult?categoryId=${gcate.id }"
												class="list-group-item list-group-item-action list-group-item-secondary">${gcate.name }</a>
										</c:otherwise>
									</c:choose>
								</c:if>
							</c:forEach>
							<li class="list-group-item list-group-item-secondary"
								style="padding-top: 40px;"><h5>
									<strong>レディース</strong>
								</h5></li>
							<c:forEach var="gcate" items="${cateList }">
								<c:if test="${gcate.gender == 1 }">
									<c:choose>
										<c:when test="${categoryId == gcate.id }">
											<a
												class="list-group-item list-group-item-action list-group-item-secondary">${gcate.name }<button
													name="close" type="submit" class="close" aria-label="Close"
													value="categoryClose">
													<span aria-hidden="true">&times;</span>
												</button></a>
										</c:when>
										<c:otherwise>
											<a href="ItemSearchResult?categoryId=${gcate.id }"
												class="list-group-item list-group-item-action list-group-item-secondary">${gcate.name }</a>
										</c:otherwise>
									</c:choose>
								</c:if>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<li class="list-group-item list-group-item-secondary"
								style="padding-top: 40px;"><h5>
									<strong>カテゴリ</strong>
								</h5></li>
							<c:forEach var="gcate" items="${cateList }" varStatus="status">
								<c:choose>
									<c:when test="${categoryId == gcate.id }">
										<a
											class="list-group-item list-group-item-action list-group-item-secondary">${gcate.name }<button
												name="close" type="submit" class="close" aria-label="Close"
												value="categoryClose">
												<span aria-hidden="true">&times;</span>
											</button></a>
									</c:when>
									<c:otherwise>
										<a href="ItemSearchResult?categoryId=${gcate.id }"
											class="list-group-item list-group-item-action list-group-item-secondary">${gcate.name }</a>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:otherwise>
					</c:choose>

				</form>
			</div>
		</div>
		<!-- サイドメニュー -->
		<!-- 商品一覧 -->
		<div class="col-sm-9">
			<h4 style="padding-bottom: 20px;">
				<c:choose>
					<c:when test="${genderId == 0 }">
			メンズ:
			</c:when>
					<c:when test="${genderId == 1 }">
			レディース:
			</c:when>
				</c:choose>
				<c:if test="${category != null }">${category.categoryName }: </c:if>
				<c:choose>
					<c:when test="${searchWord.equals(\"\")}">
				"全て"
				</c:when>
					<c:otherwise>
				"${searchWord }"
				</c:otherwise>
				</c:choose>
			</h4>
			<c:choose>
				<c:when test="${sortId == null || sortId == 0 }">
					<ul class="nav nav-tabs">
						<li class="nav-item"><a class="nav-link active"
							href="ItemSearchResult?sortId=0">新しい順</a></li>
						<li class="nav-item"><a class="nav-link"
							href="ItemSearchResult?sortId=1">価格高い順</a></li>
						<li class="nav-item"><a class="nav-link"
							href="ItemSearchResult?sortId=2">価格安い順</a></li>
					</ul>
				</c:when>
				<c:when test="${sortId == 1 }">
					<ul class="nav nav-tabs">
						<li class="nav-item"><a class="nav-link"
							href="ItemSearchResult?sortId=0">新しい順</a></li>
						<li class="nav-item"><a class="nav-link active"
							href="ItemSearchResult?sortId=1">価格高い順</a></li>
						<li class="nav-item"><a class="nav-link"
							href="ItemSearchResult?sortId=2">価格安い順</a></li>
					</ul>
				</c:when>
				<c:when test="${sortId == 2 }">
					<ul class="nav nav-tabs">
						<li class="nav-item"><a class="nav-link"
							href="ItemSearchResult?sortId=0">新しい順</a></li>
						<li class="nav-item"><a class="nav-link"
							href="ItemSearchResult?sortId=1">価格高い順</a></li>
						<li class="nav-item"><a class="nav-link active"
							href="ItemSearchResult?sortId=2">価格安い順</a></li>
					</ul>
				</c:when>
			</c:choose>
			<div class="row" style="padding: 20px;">
				<!-- 商品カード -->
				<c:forEach var="item" items="${itemList }">
					<div class="col-3">
						<div class="card" style="width: 10rem; border: none;">
							<a href="Item?itemId=${item.id }"><img class="card-img-top"
								src="img/${item.fileName }" alt="Card image cap"
								style="height: 10rem;"></a>
							<div class="card-body">
								<p class="card-text">${item.name }<br>${item.formatPrice }</p>
							</div>
						</div>
					</div>
				</c:forEach>
				<!-- 商品カード -->
			</div>
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-end">
					<c:choose>
						<c:when test="${page == null || page == 1 }">
							<li class="page-item disabled"><a class="page-link border-0"><i
									class="fas fa-angle-left"></i></a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link border-0"
								href="ItemSearchResult?page=${page - 1 }"><i
									class="fas fa-angle-left"></i></a></li>
						</c:otherwise>
					</c:choose>
					<c:forEach var="i" begin="1" end="${pageMax }" step="1">
						<li class="page-item"><a class="page-link border-0"
							href="ItemSearchResult?page=${i }">${i }</a></li>
					</c:forEach>
					<c:choose>
						<c:when test="${pageMax == page }">
							<li class="page-item disabled"><a class="page-link border-0"><i
									class="fas fa-angle-right"></i></a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link border-0"
								href="ItemSearchResult?page=${page + 1 }"><i
									class="fas fa-angle-right"></i></a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</nav>
		</div>
		<!-- 商品一覧 -->
	</div>
	<jsp:include page="/baselayout/footer.jsp" />
</body>
</html>
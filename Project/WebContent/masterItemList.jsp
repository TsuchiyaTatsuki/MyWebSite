<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品リスト</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<h3 class="p-4">
		<strong>商品一覧</strong>
		<c:if test="${itemMesse != null }">
			<p class="text-danger">${itemMesse}</p>
		</c:if>
	</h3>
	<h5 class="p-4" align="right">
		<a class="text-dark" href="MNewItem" style="text-decoration: none;">新規登録</a>
	</h5>
	<c:if test="${updateMesse != null }">
		<p class="text-primary">${updateMesse }</p>
	</c:if>
	<table class="table">
		<thead class="thead-light">
			<tr>
				<th scope="col" style="width: 10%">ID <c:choose>
						<c:when test="${MsortId == 0 }">
							<a class="text-secondary" href="MItemList?MsortId=1"
								style="text-decoration: none;"><i class="fas fa-sort-down"></i></a>
						</c:when>
						<c:when test="${MsortId == 1 }">
							<a class="text-secondary" href="MItemList?MsortId=0"
								style="text-decoration: none;"><i class="fas fa-sort-up"></i></a>
						</c:when>
						<c:otherwise>
							<a class="text-secondary" href="MItemList?MsortId=0"
								style="text-decoration: none;"><i class="fas fa-sort"></i></a>
						</c:otherwise>
					</c:choose></th>

				<th scope="col" style="width: 25%">
					<div class="row dropdown" style="padding-left: 1rem;">
						<a class="dropdown-toggle text-dark" href="#"
							id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"
							style="text-decoration: none;"> 商品名 </a>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<form action="MItemList" method="POST">
								<div class="dropdown-item">
									<input type="text" class="form-control" placeholder="検索フォーム"
										name="MsearchWord">
								</div>
								<div class="dropdown-divider"></div>
								<div class="dropdown-item">
									<div align="right">
										<button type="submit" class="btn btn-primary btn-sm">検索</button>
									</div>
								</div>
							</form>
						</div>
						<c:if test="${!MsearchWord.equals(\"\") }">
							<div class="ml-auto" style="padding-right: 1rem;">
								<form action="MItemList" method="POST">
									<button name="close" type="submit" class="close"
										aria-label="Close" value="searchWordClose">
										<span aria-hidden="true">&times;</span>
									</button>
								</form>
							</div>
						</c:if>
					</div>
				</th>
				<th scope="col" style="width: 15%">
					<div class="row dropdown" style="padding-left: 1rem;">
						<a class="dropdown-toggle text-dark" href="#"
							id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"
							style="text-decoration: none;"> 性別 </a>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<a class="dropdown-item" href="MItemList?MgenderId=0">メンズ</a> <a
								class="dropdown-item" href="MItemList?MgenderId=1">レディース</a>
						</div>
						<c:if test="${MgenderId != 2 }">
							<div class="ml-auto" style="padding-right: 1rem;">
								<form action="MItemList" method="POST">
									<button name="close" type="submit" class="close"
										aria-label="Close" value="genderClose">
										<span aria-hidden="true">&times;</span>
									</button>
								</form>
							</div>
						</c:if>
					</div>
				</th>
				<th scope="col" style="width: 15%">
					<div class="row dropdown" style="padding-left: 1rem;">
						<a class="dropdown-toggle text-dark" href="#"
							id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"
							style="text-decoration: none;"> カテゴリ </a>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<c:forEach var="gcate" items="${cateList }">
								<a class="dropdown-item"
									href="MItemList?McategoryName=${gcate.name }">${gcate.name }</a>
							</c:forEach>
						</div>
						<c:if test="${!McategoryName.equals(\"\") }">
							<div class="ml-auto" style="padding-right: 1rem;">
								<form action="MItemList" method="POST">
									<button name="close" type="submit" class="close"
										aria-label="Close" value="categoryClose">
										<span aria-hidden="true">&times;</span>
									</button>
								</form>
							</div>
						</c:if>
					</div>
				</th>
				<th scope="col" style="width: 15%">価格 <c:choose>
						<c:when test="${MsortId == 3 }">
							<a class="text-secondary" href="MItemList?MsortId=2"
								style="text-decoration: none;"><i class="fas fa-sort-down"></i></a>
						</c:when>
						<c:when test="${MsortId == 2 }">
							<a class="text-secondary" href="MItemList?MsortId=3"
								style="text-decoration: none;"><i class="fas fa-sort-up"></i></a>
						</c:when>
						<c:otherwise>
							<a class="text-secondary" href="MItemList?MsortId=3"
								style="text-decoration: none;"><i class="fas fa-sort"></i></a>
						</c:otherwise>
					</c:choose></th>
				<th scope="col" style="width: 10%"><c:if
						test="${MsortId != 0 || MgenderId != 2 || !MsearchWord.equals(\"\") || !McategoryName.equals(\"\") }">
						<div class="ml-auto" style="padding-right: 1rem;">
							<form action="MItemList" method="POST">
								<button name="close" type="submit" class="close"
									aria-label="Close" value="allClose">
									<span aria-hidden="true">&times;</span>
								</button>
							</form>
						</div>
					</c:if></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${itemList}">
				<tr>
					<th scope="row">${item.id }</th>
					<td style="width: 10%">${item.name }</td>
					<td style="width: 25%">${item.genderName }</td>
					<td style="width: 15%">${item.category }</td>
					<td style="width: 15%">${item.formatPrice }</td>
					<td style="width: 10%">
						<div class="row">
							<form action="MItemUpdate" method="get">
								<input type="hidden" name="gender" value="${item.gender }">
								<button type="submit" class="btn btn-outline-secondary btn-sm"
									name="itemId" value="${item.id }">更新</button>
							</form>
							<form action="MItemDelete" method="get">
								<button type="submit" class="btn btn-outline-danger btn-sm"
									name="itemId" value="${item.id }">削除</button>
							</form>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:if test="${itemList.size() == 0 }">
		<div class="row justify-content-md-center"
			style="padding-top: 3rem; padding-bottom: 3rem;">
			<div class="col align-self-center">
				<h5 class="text-center">検索条件に当てはまる商品が見つかりません。</h5>
			</div>
		</div>
	</c:if>
	<c:if test="${itemList.size() != 0 }">
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-end">
				<c:choose>
					<c:when test="${page == null || page == 1 }">
						<li class="page-item disabled"><a class="page-link border-0"><i
								class="fas fa-angle-left"></i></a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link border-0"
							href="MItemList?page=${page - 1 }"><i
								class="fas fa-angle-left"></i></a></li>
					</c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="1" end="${pageMax }" step="1">
					<li class="page-item"><a class="page-link border-0"
						href="MItemList?page=${i }">${i }</a></li>
				</c:forEach>
				<c:choose>
					<c:when test="${pageMax == page }">
						<li class="page-item disabled"><a class="page-link border-0"><i
								class="fas fa-angle-right"></i></a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link border-0"
							href="MItemList?page=${page + 1 }"><i
								class="fas fa-angle-right"></i></a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>
	</c:if>
	<jsp:include page="/baselayout/footer.jsp" />
</body>
</html>
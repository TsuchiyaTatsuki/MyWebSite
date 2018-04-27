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
		<a class="text-dark"
			href="file:///C:/Users/USER/Documents/PersonalDevelopment/PersonalMock/masterNewItem.html"
			style="text-decoration: none;">新規登録</a>
	</h5>

	<table class="table">
		<thead class="thead-light">
			<tr>
				<th scope="col" style="width: 10%">ID</th>
				<th scope="col" style="width: 25%">商品名</th>
				<th scope="col" style="width: 15%">性別</th>
				<th scope="col" style="width: 15%">カテゴリ</th>
				<th scope="col" style="width: 15%">価格</th>
				<th scope="col" style="width: 10%"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${itemList}">
				<tr>
					<th scope="row">${item.id }</th>
					<td style="width: 10%">${item.name }</td>
					<td style="width: 25%">${item.genderName }</td>
					<td style="width: 15%">${item.category }</td>
					<td style="width: 15%">${item.price }</td>
					<td style="width: 10%">
						<form action="MItemUpdate" method="get">
							<div class="row">
								<input type="hidden" name="gender" value="${item.gender }">
								<button type="submit" class="btn btn-outline-secondary btn-sm"
									name="itemId" value="${item.id }">更新</button>
								<a href="MItemDelete?id=${item.id }">
									<button type="button" class="btn btn-outline-danger btn-sm">
										削除</button>
								</a>
							</div>
						</form>
					</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	<jsp:include page="/baselayout/footer.jsp" />
</body>
</html>
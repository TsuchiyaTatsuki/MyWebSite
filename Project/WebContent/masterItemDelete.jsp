<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品削除確認</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<h3 class="p-4">
		<strong>商品削除確認</strong>
	</h3>
	<div style="margin-left: 15%; margin-right: 15%">
		<div class="jumbotron" style="padding: 5% 8%;">
			<div class="row">
				<div class="col-sm-4">
					<img src="img/${idb.fileName }" class="img-thumbnail"
						style="width: 10rem; height: 10rem;">
				</div>
				<div class="col-sm-8">
					<h5>商品ID: ${idb.id }</h5>
					<h5>商品名: ${idb.name }</h5>
					<c:choose>
						<c:when test="${idb.gender == 0 }">
							<h5>性別: メンズ</h5>
						</c:when>
						<c:when test="${idb.gender == 1 }">
							<h5>性別: レディース</h5>
						</c:when>
					</c:choose>
					<h5>カテゴリ: ${idb.category }</h5>
				</div>
			</div>
			<hr>
			<form action="MItemDelete" method="POST">
				<div class="row justify-content-around">
				<input type="hidden" name="itemId" value="${idb.id }">
					<div class="col col-lg-4">
						<button type="submit" class="btn btn-secondary btn-block"
							name="deleteButton" value="0">戻る</button>
					</div>
					<div class="col col-lg-4">
						<button type="submit" class="btn btn-danger btn-block"
							name="deleteButton" value="1">削除</button>
					</div>
				</div>
			</form>

		</div>
	</div>
	<jsp:include page="/baselayout/footer.jsp" />
</body>
</html>
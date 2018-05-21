<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品情報更新</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />

	<h3 class="p-4">
		<strong>商品情報更新</strong>
	</h3>

	<div style="margin-left: 10%; margin-right: 10%">
		<!-- 商品情報更新フォーム -->
		<h5>
			<strong>商品ID表示</strong>
		</h5>
		<form action="MItemImageUpdate" method="POST"
			enctype="multipart/form-data">
			<div class="form-group">
				<div class="row" style="padding-top: 10px;">
					<div class="col-sm-4">
						<c:choose>
							<c:when test="${fileName != null }">
								<img src="img/${fileName }" class="img-thumbnail"
									style="width: 15rem; height: 15rem;">
							</c:when>
							<c:otherwise>
								<img src="img/${idb.fileName }" class="img-thumbnail"
									style="width: 15rem; height: 15rem;">
							</c:otherwise>
						</c:choose>
					</div>
					<div class="col-sm-8 align-self-end">
						<c:if test="${errorMesse != null }">
							<p class="text-danger">${errorMesse }</p>
						</c:if>
						<div class="input-group mb-3">
							<div class="custom-file">
								<input name="file" type="file" class="custom-file-input"
									id="file"> <label class="custom-file-label"
									for="inputGroupFile01"><c:choose>
										<c:when test="${fileName != null }">
								${fileName }
							</c:when>
										<c:otherwise>
								${idb.fileName }
							</c:otherwise>
									</c:choose></label>
							</div>
							<div class="input-group-append">
								<input class="btn btn-outline-secondary" type="submit"
									value="送信">
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
		<form style="padding-bottom: 50px" action="MItemUpdate" method="POST">
			<div class="form-group" style="padding-top: 10px;">
				<label for="exampleFormControlInput1">商品名</label> <input name="name"
					type="text" class="form-control" id="name" value="${idb.name }">
			</div>
			<div class="form-group">
				<label for="exampleFormControlSelect1">性別</label>
				<c:choose>
					<c:when test="${idb.gender == 0 }">
						<input class="form-control" id="gender" placeholder="メンズ" readonly>
						<input class="form-control" type="hidden" name="gender" value="0">
					</c:when>
					<c:when test="${idb.gender == 1 }">
						<input class="form-control" id="gender" placeholder="レディース"
							readonly>
						<input class="form-control" type="hidden" name="gender" value="1">
					</c:when>
				</c:choose>
			</div>
			<div class="form-group">
				<label for="exampleFormControlSelect1">カテゴリ</label> <select
					name="category" class="form-control" id="exampleFormControlSelect1">
					<option value="${idb.categoryId }">${idb.category }</option>
					<c:forEach var="gcate" items="${cateList}">
						<option value="${gcate.id }">${gcate.name }</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="exampleFormControlTextarea1">商品詳細</label>
				<textarea name="detail" class="form-control" id="detail" rows="3">${idb.detail }</textarea>
			</div>
			<div class="form-group">
				<label for="exampleFormControlInput1">価格</label> <input name="price"
					type="number" class="form-control" id="price" value="${idb.price }">
			</div>
			<c:choose>
				<c:when test="${fileName != null }">
					<input type="hidden" name="fileName" value="${fileName }">
				</c:when>
				<c:otherwise>
					<input type="hidden" name="fileName" value="${idb.fileName }">
				</c:otherwise>
			</c:choose>
			<div class="row justify-content-md-center" style="padding-top: 2rem;">
				<div class="col col-lg-4">
					<button type="submit" class="btn btn-primary btn-block">更新する</button>
				</div>
			</div>
		</form>
		<!-- 商品情報更新フォーム -->
	</div>

	<jsp:include page="/baselayout/footer.jsp" />
</body>
</html>
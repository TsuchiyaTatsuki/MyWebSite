<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品新規登録</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<h3 class="p-4">
		<strong>商品新規登録</strong>
	</h3>
	<div style="margin-left: 10%; margin-right: 10%">


		<!-- 商品新規登録フォーム -->

		<form action="MItemImageUpdate" method="POST"
			enctype="multipart/form-data">
			<div class="form-group">

				<div class="row" style="padding-top: 10px;">
					<div class="col-sm-4">
						<c:choose>
							<c:when test="${fileName != null }">
								<div style="overflow: hidden; width: 15rem; height: 15rem;">
									<img src="img/${fileName }" class="img-fluid">
								</div>
							</c:when>
							<c:when test="${newItem.fileName != null }">
								<div style="overflow: hidden; width: 15rem; height: 15rem;">
									<img src="img/${newItem.fileName }" class="img-fluid">
								</div>
							</c:when>
							<c:otherwise>
								<div style="overflow: hidden; width: 15rem; height: 15rem;">
									<img src="img/fuku_tatamu.png" class="img-fluid">
								</div>
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
										<c:when test="${newItem.fileName != null }">
								${newItem.fileName }
							</c:when>
									</c:choose></label>
							</div>
							<div class="input-group-append">
								<input type="hidden" name="newItem" value="1"> <input
									class="btn btn-outline-secondary" type="submit" value="送信">
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
		<form style="padding-bottom: 50px" action="MNewItem" method="POST">
			<div class="form-group">
				<label for="exampleFormControlInput1">商品名</label> <input type="text"
					value="${newItem.name }" class="form-control" id="name" name="name"
					required>
			</div>
			<!-- タブ切り替え -->
			<div class="form-group">
				<label for="exampleFormControlSelect1">性別</label>
				<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
					<li class="nav-item"><a class="nav-link active"
						id="pills-men-tab" data-toggle="pill" href="#pills-men" role="tab"
						aria-controls="pills-home" aria-selected="true">メンズ</a></li>
					<li class="nav-item"><a class="nav-link" id="pills-women-tab"
						data-toggle="pill" href="#pills-women" role="tab"
						aria-controls="pills-profile" aria-selected="false">レディース</a></li>
				</ul>
				<label for="exampleFormControlSelect1">カテゴリ</label>
				<c:if test="${error != null}">
					<p class="text-danger">${error }</p>
				</c:if>
				<div class="tab-content" id="pills-tabContent">
					<div class="tab-pane fade show active" id="pills-men"
						role="tabpanel" aria-labelledby="pills-men-tab">
						<select class="form-control" id="menCate" name="menCate">
							<option value="0"></option>
							<c:forEach var="gcate" items="${menCate}">
								<option value="${gcate.id }">${gcate.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="tab-pane fade" id="pills-women" role="tabpanel"
						aria-labelledby="pills-women-tab">
						<select class="form-control" id="womenCate" name="womenCate">
							<option value="0"></option>
							<c:forEach var="gcate" items="${womenCate}">
								<option value="${gcate.id }">${gcate.name }</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<!-- タブ切り替え -->

			<div class="form-group">
				<label for="exampleFormControlTextarea1">商品詳細</label>
				<textarea name="detail" class="form-control" id="detail" rows="3"
					required>${newItem.detail }</textarea>
			</div>
			<div class="form-group">
				<label for="exampleFormControlInput1">価格</label> <input name="price"
					type="number" class="form-control" id="price"
					value="${newItem.price }" required>
			</div>

			<c:choose>
				<c:when test="${fileName != null }">
					<input type="hidden" name="fileName" value="${fileName }">
				</c:when>
				<c:when test="${newItem.fileName != null }">
					<input type="hidden" name="fileName" value="${newItem.fileName }">
				</c:when>
				<c:otherwise>
					<input type="hidden" name="fileName" value="0">
				</c:otherwise>
			</c:choose>
			<div class="row justify-content-md-center" style="padding-top: 2rem;">
				<div class="col col-lg-4">
					<button type="submit" class="btn btn-primary btn-block">登録</button>
				</div>
			</div>
		</form>
		<!-- 商品新規登録フォーム -->
	</div>
	<jsp:include page="/baselayout/footer.jsp" />
</body>
</html>
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
		<form action="MItemImageUpdate" method="POST" enctype="multipart/form-data">
			<div class="form-group">

				<div class="row" style="padding-top: 10px;">
					<div class="col-sm-4">
						<img src="${idb.fileName }" class="img-thumbnail"
							style="width: 15rem; height: 15rem;">
					</div>
					<div class="col-sm-8 align-self-end">
						<div class="input-group mb-3">
							<div class="custom-file">
								<input name="file" type="file" class="custom-file-input"
									id="file" value="/img"> <label class="custom-file-label"
									for="inputGroupFile01"></label>
							</div>
							<div class="input-group-append">
								<button class="btn btn-outline-secondary" type="submit">送信</button>
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
				<label for="exampleFormControlSelect1">性別</label> <select
					name="gender" class="form-control" id="gender">
					<option value="0">メンズ</option>
					<option value="1">レディース</option>
				</select>
			</div>
			<div class="form-group">
				<label for="exampleFormControlSelect1">カテゴリ</label> <select
					name="category" class="form-control" id="exampleFormControlSelect1">
					<option value="1">カテゴリ1</option>
					<option value="2">カテゴリ2</option>
					<option value="3">カテゴリ3</option>
					<option value="4">カテゴリ4</option>
					<option value="5">カテゴリ5</option>
				</select>
			</div>
			<div class="form-group">
				<label for="exampleFormControlTextarea1">商品詳細</label>
				<textarea name="detail" class="form-control" id="detail" rows="3">${idb.detail }</textarea>
			</div>
			<div class="form-group">
				<label for="exampleFormControlInput1">価格</label> <input name="price"
					type="text" class="form-control" id="price" value="${idb.price }">
			</div>

			<div align="center">
				<button type="submit" class="btn btn-primary">更新する</button>
			</div>
		</form>
		<!-- 商品情報更新フォーム -->
	</div>

	<jsp:include page="/baselayout/footer.jsp" />
</body>
</html>
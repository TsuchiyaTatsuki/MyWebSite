<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<div class="content">
	<!-- メニュー -->
	<span class="sticky-top shadow-sm d-block p-2 bg-dark text-white">
		<div class="container">
			<div style="margin-left: 6%; margin-right: 6%">
				<div class="d-flex">
					<a href="Top" style="text-decoration: none; margin-right: 10px"
						class="text-white"><div class="p-2">
							<img src="img/HdKl5dKX.png" class="img-fluid"
								alt="Responsive image">
						</div></a>
					<div class="p-2">
						<form class="form-inline" action="ItemSearchResult" method="POST">
							<div class="row">
								<input type="text" class="form-control form-control-sm"
									id="searchWord" name="searchWord" placeholder="検索フォーム">
								<button type="submit" class="btn btn-outline-secondary btn-sm"
									name="action">検索</button>
							</div>
						</form>

					</div>
					<c:choose>
						<c:when test="${sessionScope.isLogin == true}">
							<div class="ml-auto dropdown">
								<a class="nav-link dropdown-toggle text-white" href="#"
									id="navbarDropdown" role="button" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false">
									${sessionScope.lud.name} 様 </a>
								<div class="dropdown-menu" aria-labelledby="navbarDropdown">
									<a class="dropdown-item" href="UserInfo">登録情報</a> <a
										class="dropdown-item" href="UserBuyHistory">注文履歴</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="Logout">ログアウト</a>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<a href="Login" style="text-decoration: none; margin-right: 10px"
								class="ml-auto text-white"><div class="p-2">ログイン</div></a>
						</c:otherwise>
					</c:choose>
					<div class="p-2">
						<a href="Cart" style="text-decoration: none; margin-right: 10px"><i
							class="fas fa-shopping-cart fa-lg text-white"></i></a>
					</div>

					<!-- ドロップダウンリスト -->
					<c:if test="${sessionScope.lud.id == 1}">
						<div class="dropdown">
							<a class="nav-link dropdown-toggle text-white" href="#"
								id="navbarDropdown" role="button" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false"><i
								class="fas fa-align-justify fa-lg"></i></a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="MItemList">商品一覧</a> <a
									class="dropdown-item" href="MNewItem">商品新規登録</a>
							</div>
						</div>
					</c:if>
					<!-- ドロップダウンリスト -->
				</div>
			</div>
		</div>
	</span>
	<!-- メニュー -->
	<div class="container">
		<div style="margin-left: 6%; margin-right: 6%">
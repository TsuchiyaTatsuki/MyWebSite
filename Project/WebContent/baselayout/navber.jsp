<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
</div>
</div>

<!-- ナビバー -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container">
		<div style="margin-left: 7%; margin-right: 7%">
			<div class="collapse navbar-collapse" id="navbarText">
				<ul class="navbar-nav mr-auto">
					<c:choose>
						<c:when test="${genderId == 0 }">
							<li class="nav-item" style="padding-right: 20px;"><a
								class="nav-link" href="GenderSorting">すべて</a></li>
							<li class="nav-item" style="padding-right: 20px;"><a
								class="nav-link active">メンズ</a></li>
							<li class="nav-item"><a
									class="nav-link" href="GenderSorting?gender=1">レディース</a></li>
						</c:when>
						<c:when test="${genderId == 1 }">
							<li class="nav-item" style="padding-right: 20px;"><a
								class="nav-link" href="GenderSorting">すべて</a></li>
							<li class="nav-item" style="padding-right: 20px;"><a
								class="nav-link" href="GenderSorting?gender=0">メンズ</a></li>
							<li class="nav-item"><a
									class="nav-link active">レディース</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item" style="padding-right: 20px;"><a
								class="nav-link active">すべて</a></li>
							<li class="nav-item" style="padding-right: 20px;"><a
								class="nav-link" href="GenderSorting?gender=0">メンズ</a></li>
							<li class="nav-item"><a
									class="nav-link" href="GenderSorting?gender=1">レディース</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</div>
</nav>
<!-- ナビバー -->
<div class="container">
	<div style="margin-left: 6%; margin-right: 6%">
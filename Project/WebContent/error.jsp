<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<title>エラー</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<div style="margin-left: 15%; margin-right: 15%; padding-top: 4rem;">
		<!-- 会員登録フォーム -->
		<div class="jumbotron" style="padding: 5% 8%;">
			<h4 class="text-center text-danger">エラーが発生しました</h4>
			<h4 class="text-center text-danger">${errorMessage }</h4>
			<div class="row justify-content-md-center"
				style="padding-top: 2rem; padding-bottom: 2rem;">
				<div class="col col-lg-6">
					<a href="Top">
						<button type="button" class="btn btn-primary btn-block">
							トップに戻る</button>
					</a>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/baselayout/footer.jsp" />
</body>
</html>
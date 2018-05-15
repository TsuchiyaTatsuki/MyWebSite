<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<nav aria-label="Page navigation example">
	<ul class="pagination justify-content-end">
		<c:choose>
			<c:when test="${page == null || page == 1 }">
				<li class="page-item disabled"><a class="page-link border-0"><i
						class="fas fa-angle-left"></i></a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link border-0"
					href="UserBuyHistory?page=${page - 1 }"><i
						class="fas fa-angle-left"></i></a></li>
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="1" end="${pageMax }" step="1">
			<li class="page-item"><a class="page-link border-0"
				href="UserBuyHistory?page=${i }">${i }</a></li>
		</c:forEach>
		<c:choose>
			<c:when test="${pageMax == page }">
				<li class="page-item disabled"><a class="page-link border-0"><i
						class="fas fa-angle-right"></i></a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link border-0"
					href="UserBuyHistory?page=${page + 1 }"><i
						class="fas fa-angle-right"></i></a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</nav>
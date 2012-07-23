<!DOCTYPE HTML>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<!-- <link type="image/x-icon" rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico"/> -->

		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/stylesheet.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />

		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

		<title><decorator:title default="Comente Sobre"/></title>
	</head>
	<body>
		<c:if test="${!empty errors}">
			<div class="alert alert-error">
				<a class="close" data-dismiss="alert" href="#">×</a>
				<c:forEach var="error" items="${errors}">
					<div>${error.message}</div>
				</c:forEach>
			</div>
		</c:if>

		<div id="content">
			<decorator:body/>
		</div>
	</body>
</html>

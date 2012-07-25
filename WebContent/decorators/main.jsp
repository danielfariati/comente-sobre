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
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/scripts.js"></script>

		<title><decorator:title default="Comente Sobre"/></title>
	</head>
	<body>
    	<div id="header" class="navbar navbar-fixed-top">
    		<div class="navbar-inner">
    			<div class="container">
					<a class="brand" href="${pageContext.request.contextPath}">Comente Sobre</a>

					<ul class="nav">
						<li><a href="${pageContext.request.contextPath}/topic/list">T&oacute;picos</a></li>
					</ul>

					<ul class="nav float-right">
						<li>
							<div id="login-menu">
								<c:choose>
									<c:when test="${empty userSession.user}">
										<a href="${pageContext.request.contextPath}/user/login">Login</a>
									</c:when>
									<c:otherwise>
										Bem-vindo, ${userSession.user.name} | <a href="${pageContext.request.contextPath}/user/logout">Logout</a> 
									</c:otherwise>
								</c:choose>
							</div>
						</li>
					</ul>
				</div>
			</div>
    	</div>

		<div id="content" class="container">
			<c:if test="${!empty errors}">
				<div class="alert alert-error">
					<a class="close" data-dismiss="alert" href="#">×</a>
					<c:forEach var="error" items="${errors}">
						<div>${error.message}</div>
					</c:forEach>
				</div>
			</c:if>

			<decorator:body/>
		</div>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="ru">
<head><meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>Книги</title>

<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"/>

</head>

<body class="pages">
<c:if test="${pageContext.request.userPrincipal.name != null}">
	   <h2 align = "center">Текущий пользователь : ${pageContext.request.userPrincipal.name}
           </h2>
	</c:if>

<table class="firstline">
<tr>
<td><a class="toptext" href="${pageContext.request.contextPath}/books">Книги</a></td>
<td><a class="toptext" href="${pageContext.request.contextPath}/users">Пользователи</a></td>
</tr>
</table>
</body>
</html>
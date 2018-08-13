<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Книги</title>

<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"/>

</head>

<body class="pages">
<table class="firstline">
<tr>
<td><a class="toptext" href="${pageContext.request.contextPath}/books">Книги</a></td>
<td><a class="toptext" href="${pageContext.request.contextPath}/books">Пользователи</a></td>
</tr>
</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<script src="<c:url value="/resources/js/sorting.js" />"></script>

<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"/>
<body>

<a href="./index.jsp">На главную</a>

<h1>Список книг</h1>

<c:if test="${!empty listBooks}">
    <table class="sort" align="center"><thead>
        <tr>
        <th>ISBN</th>
        <th>Автор</th>
        <th>Название</th>
        <th>Кем взята</th>
        <th>Редактировать</th>
        <th>Удалить</th>
        </tr></thead><tbody>
        <c:forEach var="book" items="${listBooks}">
        <tr>
        <td>${book.ISBN}</td>
        <td><c:out value="${book.author}"/></td>
        <td><c:out value="${book.name_book}"/></td>
        <td><c:out value="${book.user_take}"/></td>
        <td><a href = "/edit?isbn=${book.ISBN}">Редактировать</a></td>
        <td><a href = "/remove?isbn=${book.ISBN}">Удалить</a></td>
        </tr>
        </c:forEach>
    </tbody></table>
</c:if>

<a href="${pageContext.request.contextPath}/add">Добавить книгу</a>
</body>
</html>
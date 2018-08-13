<html>
<body>

<a href="../../index.jsp" target="_blank">На главную</a>

<h1>Список книг</h1>

<c:if test="${!empty listBooks}">
    <table>
        <tr>
        <th>ISBN</th>
        <th>Автор</th>
        <th>Название</th>
        <th>Кем взята</th>
        <th>Редактировать</th>
        <th>Удалить</th>
        </tr>
        <c:forEach items = "${listBooks}" var ="book">
        <tr>
        <td>${book.ISBN}</td>
        <td>${book.author}</td>
        <td>${book.name_book}</td>
        <td>${book.user_take}</td>
        <td><a href = "c:url value="/edit/${book.ISBN}">">Редактировать</a></td>
        <td><a href = "c:url value="/remove/${book.ISBN}">">Удалить</a></td>
        </tr>
    </table>
</c:if>

<c:url var="addAction" value="/books/add">
</c:url>
</body>
</html>
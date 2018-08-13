<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head><meta charset="utf-8">
<script charset="UTF-8" src="<c:url value="/resources/js/sorting.js" />"></script>

<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"/>

<script type="text/javascript" charset="utf-8">
function del(isbn) {
if (confirm("Удалить книгу?")){
document.fordelete.isbn_del.value =isbn;
document.fordelete.submit();
}
}

function take(isbn, user_log) {
if (confirm("Взять себе книгу?")){
document.fortake.isbn_take.value =isbn;
document.fortake.user_log.value =user_log;
document.fortake.submit();"+
}
}

function ireturn(isbn) {
if (confirm("Вернуть книгу?")){
document.forreturn.isbn_return.value =isbn;
document.forreturn.submit();
}
}

</script>
</head>
<body>

<a href="./index.jsp">На главную</a>

<h1>Список книг</h1>

<h4 align = "center"><a class="show-btn" href = "javascript:void(0)" onclick = "document.getElementById('envelope').style.display='block';document.getElementById('fade').style.display='block'">Добавить книгу</a></h4>

<div id="envelope" class="envelope">

<form method="get" action="AddNewBook"><br>
<h3 align = "center">Добавить книгу</h3><br>
<table align="center">
<tr><td>ISBN</td><td><input type="text" name="isbn_add" id="isbn_add" class="text-ok" required /></td></tr>
<tr><td>Автор</td><td><input type="text" name="author_add" id="author_add" class="text-ok" required /></td></tr>
<tr><td>Наименование книги</td><td><input type="text" name="bookname_add" id="bookname_add" class="text-ok" required /></td></tr>
<tr><td> <br> </td><td>  </td></tr>
<tr><td><input type="submit" name="send" value="Добавить" ></td>
<td><input type="button" name="cancel" value="Отмена" onclick = "document.getElementById('envelope').style.display='none';document.getElementById('fade').style.display='none'"></td></tr>
</table></form>
</div>
<div id="fade" class="black-overlay"></div>

<div id="editbook" class="envelope">
<form method="get" action="EditBook"><br>
<h3 align = "center">Редактировать книгу</h3><br>
<table align="center">
<tr><td>ISBN</td><td><input type="text" name="isbn_edit" id="isbn_edit"  class="text-disabled" readonly /></td></tr>
<tr><td>Автор</td><td><input type="text" name="author_edit" id="author_edit" class="text-ok" required /></td></tr>
<tr><td>Наименование книги</td><td><input type="text" name="bookname_edit" id="bookname_edit" class="text-ok" required /></td></tr>
<tr><td> <br> </td><td>  </td></tr>
<tr><td><input type="submit" name="send1" value="Сохранить" ></td>
<td><input type="button" name="cancel1" value="Отмена" onclick = "document.getElementById('editbook').style.display='none';document.getElementById('fade1').style.display='none'"></td></tr>
</table></form>
</div>
<div id="fade1" class="black-overlay"></div>


<c:if test="${!empty listBooks}">
    <table class="sort" align="center"><thead><tr>
      <td>ISBN</td>
      <td>Автор</td>
      <td>Название книги</td>
      <td>Кем взята</td>
      <td>Удалить</td>
      </tr></thead><tbody>
        <c:forEach var="book" items="${listBooks}">
        <tr>
        <td onclick = "document.getElementById('editbook').style.display='block';document.getElementById('fade1').style.display='block';document.getElementById('isbn_edit').value='${book.ISBN}';document.getElementById('author_edit').value='${book.author}';document.getElementById('bookname_edit').value='${book.name_book}'">${book.ISBN}</td>
        <td><c:out value="${book.author}"/></td>
        <td><c:out value="${book.name_book}"/></td>
        <td><c:out value="${book.user_take}"/></td>
        <td><a href = "/remove?isbn=${book.ISBN}">Удалить</a></td>
        </tr>
        </c:forEach>
    </tbody></table>
</c:if>

<form name="fordelete" action="DelBook" method="get">
    <input type="hidden" name="isbn_del" id="isbn_del">
</form>
<form name="fortake" action="TakeBook" method="get">
    <input type="hidden" name="isbn_take" id="isbn_take">
    <input type="hidden" name="user_log" id="user_log">
</form>
<form name="forreturn" action="ReturnBook" method="get">
    <input type="hidden" name="isbn_return" id="isbn_return">
</form>
</body>
</html>
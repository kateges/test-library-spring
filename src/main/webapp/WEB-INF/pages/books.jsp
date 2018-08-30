<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="ru">

<head><meta http-equiv="content-type" content="text/html; charset=utf-8">
<script src="<c:url value="/resources/js/sorting.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />

</head>
<body class="pages">

<c:if test="${not empty add_book_res}">
<script>
    if (${add_book_res} > 0)
      alert("Книга добавлена");
    else
    {
      alert("Книга с таким ISBN уже существует");
      document.getElementById('envelope').style.display='block';
      document.getElementById('fade').style.display='block';
    }
</script>
</c:if>

<c:if test="${not empty edit_book_res}">
    <script>
    if (${edit_book_res} > 0)
      alert("Изменения сохранены");
   </script>
</c:if>

<c:if test="${not empty del_book_res}">
    <script>
    if (${del_book_res} > 0)
      alert("Книга удалена");
   </script>
</c:if>

<c:if test="${not empty take_book_res}">
    <script>
    if (${take_book_res} > 0)
      alert("Вы взяли книгу");
   </script>
</c:if>

<c:if test="${not empty return_book_res}">
    <script>
    if (${return_book_res} > 0)
      alert("Вы вернули книгу");
   </script>
</c:if>

<c:if test="${pageContext.request.userPrincipal.name != null}">
	   <h2 align = "center">Текущий пользователь : ${pageContext.request.userPrincipal.name}
           </h2>
</c:if>

<table class="firstline">
<tr>
<td class="toptext">Книги</td>
<td><a class="toptext" href="${pageContext.request.contextPath}/users">Пользователи</a></td>
</tr>
</table>

<h4 align = "center"><a class="show-btn" href = "javascript:void(0)" onclick = "document.getElementById('envelope').style.display='block';document.getElementById('fade').style.display='block'">Добавить книгу</a></h4>

<div id="envelope" class="envelope">

<form method="get" action="${pageContext.request.contextPath}/add"><br>
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
<form method="post" action="${pageContext.request.contextPath}/edit"><br>
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

<table class="sort" align="center">
    <thead><tr>
      <td>ISBN</td>
      <td>Автор</td>
      <td>Название книги</td>
      <td>Кем взята</td>
      <td>Удалить</td>
      </tr></thead><tbody>
        <c:forEach var="book" items="${listBooks}">
        <tr>
        <td onclick = "document.getElementById('editbook').style.display='block';document.getElementById('fade1').style.display='block';document.getElementById('isbn_edit').value='${book.ISBN}';document.getElementById('author_edit').value='${book.author}';document.getElementById('bookname_edit').value='${book.name_book}'">${book.ISBN}</td>
        <td>${book.author}</td>
        <td>${book.name_book}</td>
        <td><c:if test="${pageContext.request.userPrincipal.name == book.user_take}">
                 <a href="${pageContext.request.contextPath}/return?isbn_return=${book.ISBN}" onclick="return confirm('Вернуть книгу?')">Вернуть</a>
            </c:if>
            <c:if test="${empty book.user_take}">
                 <a href="${pageContext.request.contextPath}/take?isbn_take=${book.ISBN}&user_take=${pageContext.request.userPrincipal.name}" onclick="return confirm('Взять себе?')">Взять себе</a>
            </c:if>
            <c:if test="${pageContext.request.userPrincipal.name != book.user_take}">
                 ${book.user_take}
            </c:if>
        </td>
        <td> <a href="${pageContext.request.contextPath}/remove?isbn_del=${book.ISBN}" onclick="return confirm('Удалить книгу?')">Удалить</a> </td>
        </tr>
        </c:forEach>
    </tbody></table>
</c:if>

</body>

</html>
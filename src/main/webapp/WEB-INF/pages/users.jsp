<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head><meta charset="utf-8">
<script charset="UTF-8" src="<c:url value="/resources/js/sorting.js" />"></script>

<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"/>

</head>
<body class="pages">

<c:if test="${not empty add_user_res}">
    <script>
    if (${add_user_res} > 0)
      alert("Пользователь добавлен");
    else
    {
      alert("Пользователь с таким логином уже существует");
      document.getElementById('envelope').style.display='block';
      document.getElementById('fade').style.display='block';
    }
   </script>
</c:if>

<c:if test="${not empty del_user_res}">
    <script>
    if (${del_user_res} > 0)
    {
      alert("Пользователь удален");
    }
    else
    {
      alert("Нельзя удалить этого пользователя, потому что у него есть книги");
    }
   </script>
</c:if>

<table class="firstline">
<tr>
<td><a class="toptext" href="${pageContext.request.contextPath}/books">Книги</a></td>
<td class="toptext">Пользователи</td>
</tr>
</table>

<h4 align = "center"><a class="show-btn" href = "javascript:void(0)" onclick = "document.getElementById('envelope').style.display='block';document.getElementById('fade').style.display='block'">Добавить пользователя</a></h4>

<div id="envelope" class="envelope">

<form method="get" action="${pageContext.request.contextPath}/adduser"><br>
<h3 align = "center">Добавить пользователя</h3><br>
<table align="center">
<tr><td>Логин</td><td><input type="text" name="login_add" id="login_add" class="text-ok" required /></td></tr>
<tr><td>Пароль</td><td><input type="text" name="pwd_add" id="pwd_add" class="text-ok" required /></td></tr>
<tr><td> <br> </td><td>  </td></tr>
<tr><td><input type="submit" name="send" value="Добавить" ></td>
<td><input type="button" name="cancel" value="Отмена" onclick = "document.getElementById('envelope').style.display='none';document.getElementById('fade').style.display='none'"></td></tr>
</table></form>
</div>
<div id="fade" class="black-overlay"></div>

<c:if test="${!empty listUsers}">
    <table class="sort" align="center"><thead><tr>
      <td>Логин</td>
      <td>Пароль</td>
      <td>Удалить</td>
      </tr></thead><tbody>
        <c:forEach var="user" items="${listUsers}">
        <tr>
        <td>${user.user_log}</td>
        <td>${user.user_pwd}</td>
        <td> <a href="${pageContext.request.contextPath}/removeuser?login=${user.user_log}" onclick="return confirm('Удалить пользователя?')">Удалить</a> </td>
        </tr>
        </c:forEach>
    </tbody></table>
</c:if>

</body>
</html>
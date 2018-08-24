<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="ru">

<head><meta http-equiv="content-type" content="text/html; charset=utf-8">

<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />

<script type="text/javascript" src="<c:url value="/resources/js/sorting.js" />"></script>
<script type="text/javascript">

var img_dir = "<c:url value="/resources/images/" />"; // папка с картинками
var sort_case_sensitive = false; // вид сортировки (регистрозависимый или нет)

function _sort(a, b) {
	var a = a[0];
	var b = b[0];
	var _a = (a + '').replace(/,/, '.');
	var _b = (b + '').replace(/,/, '.');
	if (parseInt(_a) && parseInt(_b)) return sort_numbers(parseInt(_a), parseInt(_b));
	else if (!sort_case_sensitive) return sort_insensitive(a, b);
	else return sort_sensitive(a, b);
}

function sort_numbers(a, b) {
	return a - b;
}

function sort_insensitive(a, b) {
	var anew = a.toLowerCase();
	var bnew = b.toLowerCase();
	if (anew < bnew) return -1;
	if (anew > bnew) return 1;
	return 0;
}

function sort_sensitive(a, b) {
	if (a < b) return -1;
	if (a > b) return 1;
	return 0;
}

function getConcatenedTextContent(node) {
	var _result = "";
	if (node == null) {
		return _result;
	}
	var childrens = node.childNodes;
	var i = 0;
	while (i < childrens.length) {
		var child = childrens.item(i);
		switch (child.nodeType) {
			case 1: // ELEMENT_NODE
			case 5: // ENTITY_REFERENCE_NODE
				_result += getConcatenedTextContent(child);
				break;
			case 3: // TEXT_NODE
			case 2: // ATTRIBUTE_NODE
			case 4: // CDATA_SECTION_NODE
				_result += child.nodeValue;
				break;
			case 6: // ENTITY_NODE
			case 7: // PROCESSING_INSTRUCTION_NODE
			case 8: // COMMENT_NODE
			case 9: // DOCUMENT_NODE
			case 10: // DOCUMENT_TYPE_NODE
			case 11: // DOCUMENT_FRAGMENT_NODE
			case 12: // NOTATION_NODE
			// skip
			break;
		}
		i++;
	}
	return _result;
}

function sort(e) {
	var el = window.event ? window.event.srcElement : e.currentTarget;

	while (el.tagName.toLowerCase() != "td") el = el.parentNode;

	var a = new Array();
	var name = el.lastChild.nodeValue;
	var dad = el.parentNode;
	var table = dad.parentNode.parentNode;
	var up = table.up; // no set/getAttribute!

	var node, arrow, curcol;
	for (var i = 0; (node = dad.getElementsByTagName("td").item(i)); i++) {
		if (node.lastChild.nodeValue == name){
			curcol = i;
			if (node.className == "curcol"){
				arrow = node.firstChild;
				table.up = Number(!up);
			}else{
				node.className = "curcol";
				arrow = node.insertBefore(document.createElement("img"),node.firstChild);
				table.up = 0;
			}
			arrow.src = img_dir + table.up + ".gif";
			arrow.alt = "";
		}else{
			if (node.className == "curcol"){
				node.className = "";
				if (node.firstChild) node.removeChild(node.firstChild);
			}
		}
	}

	var tbody = table.getElementsByTagName("tbody").item(0);
	for (var i = 0; (node = tbody.getElementsByTagName("tr").item(i)); i++) {
		a[i] = new Array();
		a[i][0] = getConcatenedTextContent(node.getElementsByTagName("td").item(curcol));
		a[i][1] = getConcatenedTextContent(node.getElementsByTagName("td").item(1));
		a[i][2] = getConcatenedTextContent(node.getElementsByTagName("td").item(0));
		a[i][3] = node;
	}

	a.sort(_sort);

	if (table.up) a.reverse();

	for (var i = 0; i < a.length; i++) {
		tbody.appendChild(a[i][3]);
	}
}

function init(e) {
	if (!document.getElementsByTagName) return;

	if (document.createEvent) function click_elem(elem){
		var evt = document.createEvent("MouseEvents");
		evt.initMouseEvent("click", false, false, window, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, elem);
		elem.dispatchEvent(evt);
	}

	for (var j = 0; (thead = document.getElementsByTagName("thead").item(j)); j++) {
		var node;
		for (var i = 0; (node = thead.getElementsByTagName("td").item(i)); i++) {
			if (node.addEventListener) node.addEventListener("click", sort, false);
			else if (node.attachEvent) node.attachEvent("onclick", sort);
			node.title = "Нажмите на заголовок, чтобы отсортировать колонку";
		}
		thead.parentNode.up = 0;

		if (typeof(initial_sort_id) != "undefined"){
			td_for_event = thead.getElementsByTagName("td").item(initial_sort_id);
			if (td_for_event.dispatchEvent) click_elem(td_for_event);
			else if (td_for_event.fireEvent) td_for_event.fireEvent("onclick");
			if (typeof(initial_sort_up) != "undefined" && initial_sort_up){
				if (td_for_event.dispatchEvent) click_elem(td_for_event);
				else if (td_for_event.fireEvent) td_for_event.fireEvent("onclick");
			}
		}
	}
}

var root = window.addEventListener || window.attachEvent ? window : document.addEventListener ? document : null;
if (root){
	if (root.addEventListener) root.addEventListener("load", init, false);
	else if (root.attachEvent) root.attachEvent("onload", init);
}
//-->

</script>
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
      <td>Кем взята</td>
      </tr></thead><tbody>
        <c:forEach var="book" items="${listBooks}">
        <tr>
        <td onclick = "document.getElementById('editbook').style.display='block';document.getElementById('fade1').style.display='block';document.getElementById('isbn_edit').value='${book.ISBN}';document.getElementById('author_edit').value='${book.author}';document.getElementById('bookname_edit').value='${book.name_book}'">${book.ISBN}</td>
        <td>${book.author}</td>
        <td>${book.name_book}</td>
        <td>${book.user_take}</td>
        <td> <a href="${pageContext.request.contextPath}/remove?isbn_del=${book.ISBN}" onclick="return confirm('Удалить книгу?')">Удалить</a> </td>
        </tr>
        </c:forEach>
    </tbody></table>
</c:if>

</body>

</html>
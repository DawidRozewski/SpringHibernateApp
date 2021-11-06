<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<head>
    <title>Books</title>
    <style>
        table, th, td {
            border-collapse: collapse;
            border: 1px solid black;
            padding: 5px;
        }
        th {
            background-color: #ccc;
        }
    </style>
</head>
<table>
    <tr>
        <th>Id</th>
        <th>Title</th>
        <th>Description</th>
        <th>Authors</th>
        <th>Edit / Remove</th>
    </tr>

    <c:forEach items="${books}" var="book">
        <tr>
            <td>${book.id}</td>
            <td>${book.title}</td>
            <td>${book.description}</td>
            <td> <c:forEach items="${book.authors}" var="a" varStatus="loop">
                <c:out value="${a.firstName} ${a.lastName}"/><c:if test="${loop.index + 1 lt b.authors.size()}">; </c:if>
            </c:forEach><br/></td>
            <td> <a href="edit?id=${book.id}">Edit</a>
                <a href="remove?id=${book.id}">Remove</a></td>
        </tr>
    </c:forEach>

</table>
<br/>
<a href="/book/form/add">Add new book</a><br/><br/>
<strong> <a href="http://localhost:8080/">Main page</a></strong>
</body>
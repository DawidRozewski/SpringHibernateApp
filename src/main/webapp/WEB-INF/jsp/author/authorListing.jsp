<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<head>
    <title>Authors</title>
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
        <th>Fullname</th>
        <th>Edit / Remove</th>
    </tr>

    <c:forEach items="${authors}" var="a">
        <tr>
            <td>${a.id}</td>
            <td>${a.fullName}</td>
            <td> <a href="edit?id=${a.id}">Edit</a>
                <a href="remove?id=${a.id}">Remove</a></td>
        </tr>
    </c:forEach>

</table>
<strong> <a href="add">Add new author</a></strong><br/>
<strong> <a href="http://localhost:8080/">Stronga głowna</a></strong>

</body>
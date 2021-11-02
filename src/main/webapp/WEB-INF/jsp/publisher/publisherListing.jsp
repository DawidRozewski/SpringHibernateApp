<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<head>
    <title>Publishers</title>
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
        <th>Name</th>
        <th>Edit / Remove</th>
    </tr>

    <c:forEach items="${publishers}" var="p">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td> <a href="edit?id=${p.id}">Edit</a>
                <a href="remove?id=${p.id}">Remove</a></td>
        </tr>
    </c:forEach>

</table>
<strong> <a href="add">Add new publisher</a></strong><br/>
<strong> <a href="http://localhost:8080/">Strona głowna</a></strong>

</body>
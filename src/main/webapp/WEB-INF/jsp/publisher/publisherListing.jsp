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
        <th>NIP</th>
        <th>Regon</th>
        <th>Edit / Remove</th>
    </tr>

    <c:forEach items="${publishers}" var="p">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.nip}</td>
            <td>${p.regon}</td>
            <td> <a href="edit?id=${p.id}">Edit</a>
                <a href="remove?id=${p.id}">Remove</a></td>
        </tr>
    </c:forEach>

</table>
<br/>
<strong> <a href="add">Add new publisher</a></strong><br/><br/>
<strong> <a href="http://localhost:8080/">Main page</a></strong><br/>
<%@include file="../common/search.jsp"%>

</body>
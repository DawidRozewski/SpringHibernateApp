<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<h2>Add new publisher</h2>
<head>
    <style>
        .error {
            color: red;
            border: 2px solid;
        }
    </style>
</head>
<form:form modelAttribute="publisher">
    <form:hidden path="id"/>
    Name: <form:input path="name"/><br>
    <form:errors path="name"/><br/>

    NIP: <form:input path="nip"/><br/>
    <form:errors path="nip"/><br/>

    Regon: <form:input path="regon"/><br/>
    <form:errors path="regon"/><br/>

    <input type="submit" value="Zapisz">
</form:form>
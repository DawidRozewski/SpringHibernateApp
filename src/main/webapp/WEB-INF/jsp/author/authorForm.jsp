<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<h2>Add new author</h2>
<head>
    <style>
        .error {
            color: red;
            border: 2px solid;
        }
    </style>
</head>

<form:form modelAttribute="author">
    <form:hidden path="id"/>

    First name: <form:input path="firstName"/><br>
    <form:errors path="firstName"/><br/>

    Last name: <form:input path="lastName"/><br>
    <form:errors path="lastName"/><br/>

    Pesel: <form:input path="pesel"/><br/>
    <form:errors path="pesel"/><br/>

    Email: <form:input path="email"/><br/>
    <form:errors path="email"/><br/>

    <input type="submit" value="Save">
</form:form>
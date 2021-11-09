<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<h2>Add new book</h2>
<head>
    <style>
        .error {
            color: red;
        }
    </style>
</head>

<form:form modelAttribute="book">
    <form:hidden path="id"/>

    Title: <form:input path="title"/><br/>
    <form:errors path="title" cssClass="error"/>

    Description: <form:textarea path="description" cols="15" rows="10"/><br/>
    <form:errors path="description" cssClass="error"/><br/>

    Publisher: <form:select path="publisher.id" items="${publishers}" itemLabel="name" itemValue="id"/><br/>
    <form:errors path="publisher" cssClass="error"/><br/>

    Authors: <form:select path="authors" items="${authors}" itemValue="id" itemLabel="fullName" multiple="true"/><br/>
    <form:errors path="authors" cssClass="error"/><br/>

    Rating: <form:input path="rating" type="number"/> <br/>
    <form:errors path="rating" cssClass="error"/><br/>

    Pages: <form:input path="pages" type="number"/> <br/>
    <form:errors path="pages" cssClass="error"/><br/>

    <input type="submit" value="Save">
</form:form>



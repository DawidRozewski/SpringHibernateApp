<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<form:form>
    Title: <form:input path="title" value="${book.title}"  /><br/>
    Description: <form:textarea path="description" cols="15" rows="10"/><br/>
    Publisher: <form:select path="publisher.id" items="${publishers}" itemLabel="name" itemValue="id"/><br/>
    Authors: <form:select path="authors" items="${authors}" itemValue="id" itemLabel="fullName" multiple="true"/><br/>

    <input type="submit" value="Save book">
</form:form>
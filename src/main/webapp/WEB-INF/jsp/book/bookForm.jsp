<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<h2>Add new book</h2>
<form:form modelAttribute="book" >
    <form:hidden path="id"/>
    Title: <form:input path="title"/><br/>
    Description: <form:textarea path="description" cols="15" rows="10"/><br/>
    Publisher: <form:select path="publisher.id" items="${publishers}" itemLabel="name" itemValue="id"/><br/>
    Authors: <form:select path="authors" items="${authors}" itemValue="id" itemLabel="fullName" multiple="true"/><br/>

<input type="submit" value="Add book">
</form:form>
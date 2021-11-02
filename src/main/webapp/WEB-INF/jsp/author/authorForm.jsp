<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<h2>Add new author</h2>
<form:form modelAttribute="author">
    <form:hidden path="id"/>
    First name: <form:input path="firstName"/><br>
    Last name: <form:input path="lastName"/><br>
    <input type="submit" value="Zapisz">
</form:form>
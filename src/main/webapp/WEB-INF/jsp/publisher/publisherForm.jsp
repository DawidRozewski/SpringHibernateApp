<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<h2>Add new publisher</h2>
<form:form modelAttribute="publisher">
    <form:hidden path="id"/>
    Name: <form:input path="name"/><br>
    <input type="submit" value="Zapisz">
</form:form>
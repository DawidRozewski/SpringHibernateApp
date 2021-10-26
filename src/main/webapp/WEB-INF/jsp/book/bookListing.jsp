<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:forEach items="${books}" var="book">
    <strong><c:out value="${book.title}: ${book.publisher}"/></strong><br/>
    <strong>Description: </strong>
    <c:out value="${book.description}"/><br/>
    <strong>Authors:</strong>
    <c:forEach items="${book.authors}" var="a" varStatus="loop">
        <c:out value="${a.firstName} ${a.lastName}"/><c:if test="${loop.index + 1 lt b.authors.size()}">; </c:if>
    </c:forEach><br/>
    <a href="/book/form/show">Edit</a>
    <a href="/book/form/show">Delete</a>
    <hr>
</c:forEach>


<a href="/book/form/show">Show form</a>


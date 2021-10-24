<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<form:form method="post" modelAttribute="student">
    FirstName: <form:input path="firstName"/> <br/>
    LastName: <form:input path="lastName"/> <br/>
    Male: <form:radiobutton path="gender" value="M"/> <br/>
    Female: <form:radiobutton path="gender" value="F"/> <br/>
    Country: <form:select path="country" items="${countries}" multiple="false"/> <br/>
    Notes: <form:textarea path="notes" rows="1" cols="20"/> <br/>
    MailingList: <form:checkbox path="mailingList"/><br/>
    ProgrammingSkills: <form:select path="programmingSkills" items="${programmingSkills}" multiple="true"/> <br/>
    Hobbies: <form:checkboxes path="hobbies" items="${hobbies}"/><br/>

    <input type="submit" value="Wyślij"><br/>

</form:form>
<form method="get">

    Pole:
    <select name="field">
        <c:forEach items="${searchMode}" var="mode">
            <c:choose>
                <c:when test="${mode eq selectedField}">
                    <option value="${mode}" selected>${mode}</option>
                </c:when>
                <c:otherwise>
                    <option value="${mode}">${mode}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
    <br />
    <input type="text" name="query" value="${query}"/>
    <br/>
    <input type="submit" value="Szukaj!">
</form>
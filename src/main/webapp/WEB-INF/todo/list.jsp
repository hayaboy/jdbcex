
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--${dtoList}--%>


<ul>
    <c:forEach items="${dtoList}" var="dto">
        <li>
            <span><a href="/jdbcex/todo/read?tno=${dto.tno}">${dto.tno}</a></span>
            <span>${dto.title}</span>
            <span>${dto.dueDate}</span>
            <span>${dto.finished ? "했음": "아직 안했음 "}</span>
        </li>
    </c:forEach>
</ul>
</body>
</html>

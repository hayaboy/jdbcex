<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<%--<c:if test="${param.result == 'error'}">
    <h1>로그인 에러</h1>
</c:if>--%>

<form action="/jdbcex/login" method="post">
    <input type="text" name="mid">
    <input type="text" name="mpw">
    <br>
    <input type="checkbox" name="auto"> 로그인 상태 유지(내 로그인 정보 저장)
    <button type="submit">LOGIN</button>
</form>


</body>
</html>

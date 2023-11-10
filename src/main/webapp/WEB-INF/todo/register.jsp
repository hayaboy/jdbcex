<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-11-10
  Time: 오후 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<br>

<br>
<form action="/jdbcex/todo/register" method="post">

    <div>
        <input type="text" name="title" placeholder="해야할 일을 입력하세요">
    </div>
    <div>
        <input type="date" name="dueDate">
    </div>
    <p></p>
    <p></p>
    <p></p>
    <div>
        <p></p>
        <p></p>
        <p></p>
        <button type="submit">해야할 일 등록</button>
        <button type="reset">다시 설정</button>
    </div>
</form>
</body>
</html>

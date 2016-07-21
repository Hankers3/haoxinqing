<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Alice
  Date: 2016/3/30 0030
  Time: 09:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<style>
    p {
        font-size: 1.0rem;
        color: #000000;
    }
</style>
<head>
    <title>error!</title>
</head>
<body>
<p>
    请求产生了错误！错误原因为：<br><% exception.printStackTrace(new PrintWriter(out));%>
</p>
</body>
</html>

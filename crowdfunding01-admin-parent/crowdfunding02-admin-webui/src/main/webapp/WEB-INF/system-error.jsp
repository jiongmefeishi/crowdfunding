<%--
  Created by IntelliJ IDEA.
  User: tao
  Date: 2020/5/21
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>异常</title>
</head>
<body>
<h1>出错了！</h1>

<!-- 从请求域取出Exception对象，再进一步访问message属性就能够显示错误消息 -->
${requestScope.exception.message }
</body>
</html>

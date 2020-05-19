<%--
  Created by IntelliJ IDEA.
  User: tao
  Date: 2020/5/19
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<!-- http://localhost:8080/atcrowdfunding02-admin-webui/test/ssm.html -->
<base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>
<body>
<%--
    1、因为配置了servlet的请求拦截 url-pattern 的*.html 扩展名
    这里使用绝对路径 ${pageContext.request.contextPath}/test/ssm.html

    2、为了不需要每一次测试都写 ${pageContext.request.contextPath}/
    可以配置 base 基本路径前缀

    一条完整的请求路径，模仿动态书写前缀 base
    http://localhost:8080/atcrowdfunding02-admin-webui/test/ssm.html
    <base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>


    <a href="${pageContext.request.contextPath}/test/ssm.html">测试SSM整合</a>
    可以替换为
 --%>
<a href="test/ssm.html">测试SSM整合</a>
</body>
</html>

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
<%-- 引入jQuery支持 --%>
<script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>

<script type="text/javascript">
    $(function () {

        $("#btn4").click(function () {

            // 准备要发送的数据
            var student = {
                "stuId": 5,
                "stuName": "tom",
                "address": {
                    "province": "广东",
                    "city": "深圳",
                    "street": "后瑞"
                },
                "subjectList": [
                    {
                        "subjectName": "JavaSE",
                        "subjectScore": 100
                    }, {
                        "subjectName": "SSM",
                        "subjectScore": 99
                    }
                ],
                "map": {
                    "k1": "v1",
                    "k2": "v2"
                }
            };

            // 将JSON对象转换为JSON字符串
            var requestBody = JSON.stringify(student);

            // 发送Ajax请求
            $.ajax({
                "url": "send/compose/object.json",
                "type": "post",
                "data": requestBody,
                "contentType": "application/json;charset=UTF-8",
                "dataType": "json",
                "success": function (response) {
                    console.log(response);
                },
                "error": function (response) {
                    console.log(response);
                }
            });

        });


        $("#btn1").click(function () {

            $.ajax({
                "url": "send/array/one.html",
                "type": "post",
                //    要发送的参数
                "data": {
                    "array": [5, 8, 12, 13]
                },
                "dataType": "text",
                "success": function (response) {
                    // 服务器端成功处理请求后调用的回调函数，response是响应体数据
                    alert(response);
                },
                "error": function (response) {
                    // 服务器端处理请求失败后调用的回调函数，response是响应体数据
                    alert(response);
                }
            });
        });
    });
</script>
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

<%--测试接收JSON请求--%>
<br/>
<br/>
<button id="btn1">Send [5,8,12] One</button>

<br/>
<br/>
<button id="btn4">Send Compose Object</button>

</body>
</html>

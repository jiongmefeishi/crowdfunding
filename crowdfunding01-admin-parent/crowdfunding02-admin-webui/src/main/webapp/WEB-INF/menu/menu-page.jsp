<%--
  Created by IntelliJ IDEA.
  User: tao
  Date: 2020/6/5
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%-- 加载公共头部 --%>
<%@include file="/WEB-INF/include/include-head.jsp" %>

<body>

<%-- 加载公共导航栏 --%>
<%@ include file="/WEB-INF/include/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%-- 加载公共侧边栏 --%>
        <%@ include file="/WEB-INF/include/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

        </div>
    </div>
</div>
</body>
</html>
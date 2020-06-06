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
<%-- 加载ztree 样式 --%>
<link rel="stylesheet" href="ztree/zTreeStyle.css">
<%-- 加载ztree js --%>
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<%-- 加载自定义 js 文件 --%>
<script type="text/javascript" src="zqtScript/menu/auth-menu.js"></script>

<script type="text/javascript">

    $(function () {

        // 调用生成菜单树结构函数，生成菜单树
        generateMenuTree()
    });

</script>
<body>

<%-- 加载公共导航栏 --%>
<%@ include file="/WEB-INF/include/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%-- 加载公共侧边栏 --%>
        <%@ include file="/WEB-INF/include/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
                    <div style="float: right; cursor: pointer;" data-toggle="modal"
                         data-target="#myModal">
                        <i class="glyphicon glyphicon-question-sign"></i>
                    </div>
                </div>
                <div class="panel-body">
                    <%-- 展示菜单列表 --%>
                    <!-- 这个ul标签是zTree动态生成的节点所依附的静态节点 -->
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
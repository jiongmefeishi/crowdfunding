<%--
  Created by IntelliJ IDEA.
  User: tao
  Date: 2020/6/10
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%-- 分配角色(role)给管理员(role)的页面 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%-- 加载jstl 库 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="zh-CN">
<%-- 加载公共头部 --%>
<%@include file="/WEB-INF/include/include-head.jsp" %>

<script type="text/javascript">
    $(function () {
        $("#toRightBtn").click(function () {

            // select是标签选择器
            // :eq(0)表示选择页面上的第一个
            // :eq(1)表示选择页面上的第二个
            // “>”表示选择子元素
            // :selected表示选择“被选中的”option
            // appendTo()能够将jQuery对象追加到指定的位置
            $("select:eq(0)>option:selected").appendTo("select:eq(1)");

        });

        $("#toLeftBtn").click(function () {

            // select是标签选择器
            // :eq(0)表示选择页面上的第一个
            // :eq(1)表示选择页面上的第二个
            // “>”表示选择子元素
            // :selected表示选择“被选中的”option
            // appendTo()能够将jQuery对象追加到指定的位置
            $("select:eq(1)>option:selected").appendTo("select:eq(0)");

        });

        $("#submitBtn").click(function () {
            // 在提交表单前把“已分配”部分的option全部选中
            $("select:eq(1)>option").prop("selected", "selected");

            // 为了看到上面代码的效果，暂时不让表单提交
            // return false;
        });
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
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form action="assign/do/role/assign.html" method="post" role="form" class="form-inline">
                        <input type="hidden" name="adminId" value="${param.adminId }"/>
                        <input type="hidden" name="pageNum" value="${param.pageNum }"/>
                        <input type="hidden" name="keyword" value="${param.keyword }"/>
                        <div class="form-group">
                            <label for="exampleInputPassword1">未分配角色列表</label><br>
                            <select
                                    class="form-control" multiple="multiple" size="10"
                                    style="width: 100px; overflow-y: auto;">
                                <c:forEach items="${requestScope.unAssignedRoleList }" var="role">
                                    <option value="${role.id }">${role.name }</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li id="toRightBtn" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                                <br>
                                <li id="toLeftBtn" class="btn btn-default glyphicon glyphicon-chevron-left"
                                    style="margin-top: 20px;"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left: 40px;">
                            <label for="exampleInputPassword1">已分配角色列表</label><br>
                            <select
                                    name="roleIdList"
                                    class="form-control" multiple="multiple" size="10"
                                    style="width: 100px; overflow-y: auto;">
                                <c:forEach items="${requestScope.assignedRoleList }" var="role">
                                    <option value="${role.id }">${role.name }</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button id="submitBtn" type="submit" style="width: 150px;"
                                class="btn btn-lg btn-success btn-block">保存
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
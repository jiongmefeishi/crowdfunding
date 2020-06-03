<%--
  Created by IntelliJ IDEA.
  User: tao
  Date: 2020/5/31
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%-- 加载公共头部 --%>
<%@include file="/WEB-INF/include/include-head.jsp" %>
<%-- 引入pagination css --%>
<link rel="stylesheet" href="css/pagination.css"/>
<%-- 引入pagination js --%>
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<%-- 引入自定义的js 文件--%>
<script type="text/javascript" src="zqtScript/auth-role.js"></script>
<script type="text/javascript">
    $(function () {
        // 1.为分页操作初始化数据，存放使用window对象，全局使用
        window.pageNum = 1;
        window.pageSize = 5;
        window.keyword = "";

        // 2.调用执行分页的函数， 显示分页效果
        generateRolePage();

        // 给查询按钮绑定响应函数
        $("#searchBtn").click(function () {

            // 1.获取关键词数据，赋值给对应的全局变量 keyword
            window.keyword = $("#keywordInput").val();

            // 2.调用分页函数,刷新表格主体
            generateRolePage();
        });

        // 给新增按钮添加响应事件，弹出新增模态框
        $("#showAddModalBtn").click(function () {
            $("#addModal").modal('show');
        });

        // 给模态框的保存按钮点击事件新增响应函数
        $("#saveRoleBtn").click(function () {
            // 1.获取输入的角色名称
            // 第一种：给<input> 标签绑定 id
            // var roleName = $("#roleNameInput").val();

            // 第二种：通过父标签找到子标签，通过子标签里的属性定位到确定的子标签，获取值
            // 父标签+空格 表示父标签下面的子标签集合
            // #addModal [name=roleName] 含义解释
            // #addModal 找到整个模态框
            // 空格表示在后代元素中继续查找
            // [name=roleName] 表示匹配后代元素中属性name="roleName" 的元素
            var roleName = $("#addModal [name=roleName]").val();

            // 发送Ajax请求，保存
            $.ajax({
                "url": "role/save.json",
                "type": "post",
                "data": {
                    "name": roleName,
                },
                "dataType": "json",
                "success": function (res) {
                    var result = res.result;
                    if (result == "SUCCESS") {
                        layer.msg("操作成功")

                        // 成功，重新加载分页数据
                        // 新增的数据在最后一页，这里先处理将页数设为最大
                        var pages = getTotalPageNum();
                        // 将页码定位到最后一页
                        window.pageNum = pages
                        generateRolePage()
                    }

                    if (result == "FAILED") {
                        layer.msg("操作失败")
                    }
                },
                "error": function (res) {
                    layer.msg(res.status + res.statusText)
                }
            });

            // 关闭模态框
            $("#addModal").modal('hide');

            // 清理模态框
            $("#addModal [name=roleName]").val("");
        });

        // 给role 表格中的更新按钮新增点击事件响应函数
        // $(".pencilBtn").click(function () {
        //     // 传统的事件绑定方式只能在第一页有效，翻页后失效
        //     // 因为这按钮是动态生成的，每一此加载新页面都会重新生成
        //     // 使用 jQuery 的on 函数来解决此问题
        // });
        // on 函数解决事件绑定问题 on("事件类型", "找到真正需要绑定事件的元素选择器", 事件响应函数)
        // 1. 首先找到所有“动态生成”的元素（按钮）所附着的“静态”元素
        $("#rolePageBody").on("click", ".pencilBtn", function () {
            // 打开模态框
            $("#editModal").modal("show");

            // 回显，填充数据
            // 获取表格中当前行的角色名称
            var roleName = $(this).parent().prev().text();

            // 获取当前角色的 role id, this.id 获取的是<button id=""> 中的id 属性值
            // 当前函数用不到，更新模态框里点击更新按钮时触发的响应事件中需要，所以设置全局保存
            window.roleId = this.id;

            // 使用roleName 填充模态框中的文本框
            $("#editModal [name=roleName]").val(roleName);
        });

        // 给更新模态框更新按钮添加响应事件函数
        $("#updateRoleBtn").click(function () {

            // 从文本框中获取更新后的角色名称
            var roleName = $("#editModal [name=roleName]").val();
            $.ajax({
                "url": "role/update.json",
                "type": "post",
                "data": {
                    id: window.roleId,
                    name: roleName,
                },
                "dataType": "json",
                "success": function (res) {
                    var result = res.result;
                    if (result == "SUCCESS") {
                        layer.msg("操作成功")

                        // 成功，重新加载分页数据
                        generateRolePage()
                    }
                    if (result == "FAILED") {
                        layer.msg("操作失败")
                    }
                },
                "error": function (res) {
                    layer.msg(res.status + res.statusText)
                }
            });
            // 关闭模态框
            $("#editModal").modal('hide');
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
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <i class="glyphicon glyphicon-th"></i> 数据列表
                    </h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float: left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="keywordInput" class="form-control has-success" type="text"
                                       placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button id="searchBtn" type="button" class="btn btn-warning">
                            <i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button id="batchRemoveBtn" type="button" class="btn btn-danger"
                            style="float: right; margin-left: 10px;">
                        <i class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button
                            type="button"
                            id="showAddModalBtn"
                            class="btn btn-primary"
                            style="float: right;">
                        <i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear: both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input id="summaryBox" type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>

                            <%-- 动态显示role 分页数据 --%>
                            <tbody id="rolePageBody"></tbody>

                            <%-- 动态显示分页导航栏 --%>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <div id="Pagination" class="pagination"><!-- 这里显示分页导航栏 --></div>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%-- 统一添加模态框在页面的最后，因为加入的模态框默认是不显示的 --%>
<%-- 这里添加一个新增的模态框，通过给按钮添加点击事件来调用模态框 --%>
<%@include file="/WEB-INF/modal/modal-role-add.jsp" %>
<%@include file="/WEB-INF/modal/modal-role-edit.jsp" %>
</body>
</html>
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

        // 给菜单节点的新增按钮绑定响应函数，按钮是动态生成的，需要先找到对应依赖的静态元素
        // <ul id="treeDemo" class="ztree"></ul>
        $("#treeDemo").on('click', '.addBtn', function () {

            // 分析：点击当前按钮-> 触发响应事件 -> 打开模态框-> 在模态框中填写新增菜单信息
            // -> 点击模态框中保存按钮 ->触发响应事件 -> 执行Ajax请求服务器新增一条菜单记录
            // -> 关闭模态框，清理表单，刷新菜单树

            // 打开模态框
            $("#menuAddModal").modal('show');

            // 将当前节点的 id 作为新增节点的 pid， 保存到全局变量
            window.pid = this.id;
            // 取消超链接默认行为
            return false;
        });

        // 给新增菜单模态框中的保存按钮绑定响应函数
        $("#menuSaveBtn").click(function () {

            // 收集模态框中新增的菜单数据
            // 由于未给<input> 标签绑定 id,通过name属性确定标签
            var name = $.trim($("#menuAddModal [name=name]").val());
            var url = $.trim($("#menuAddModal [name=url]").val());
            // name=icon 有多个，这里是选择框，需要选择checked 状态的标签
            var icon = $.trim($("#menuAddModal [name=icon]:checked").val());

            // 封装数据为菜单实体
            var menu = {
                "pid": window.pid,
                "name": name,
                "url": url,
                "icon": icon
            }

            // 新增一条菜单记录
            saveMenu(menu);
            // 关闭模态框
            $("#menuAddModal").modal('hide');
            // 清空表单
            $("#menuResetBtn").click();
        });

        // 给菜单节点的编辑按钮绑定单击响应函数，按钮是动态生成的，需要先找到对应依赖的静态元素
        $("#treeDemo").on("click", ".editBtn", function () {

            // 将当前节点的id保存到全局变量
            window.id = this.id;

            // 打开模态框
            $("#menuEditModal").modal("show");

            // 获取zTreeObj对象
            var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");

            // 根据id属性查询节点对象
            // 用来搜索节点的属性名
            var key = "id";

            // 用来搜索节点的属性值
            var value = window.id;

            var currentNode = zTreeObj.getNodeByParam(key, value);

            // 回显表单数据
            $("#menuEditModal [name=name]").val(currentNode.name);
            $("#menuEditModal [name=url]").val(currentNode.url);

            // 回显radio可以这样理解：被选中的radio的value属性可以组成一个数组，
            // 然后再用这个数组设置回radio，就能够把对应的值选中
            $("#menuEditModal [name=icon]").val([currentNode.icon]);

            return false;
        });

        // 给更新模态框中的更新按钮绑定单击响应函数
        $("#menuEditBtn").click(function () {

            // 收集表单数据
            // 由于未给<input> 标签绑定 id,通过name属性确定标签
            var name = $("#menuEditModal [name=name]").val();
            var url = $("#menuEditModal [name=url]").val();
            var icon = $("#menuEditModal [name=icon]:checked").val();

            // 封装数据为菜单实体
            var menu = {
                "id": window.id,
                "name": name,
                "url": url,
                "icon": icon
            }

            // 新增一条菜单记录
            updateMenu(menu);
            // 关闭模态框
            $("#menuEditModal").modal("hide");
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

<%-- 加载模态框 --%>
<%-- 加载新增菜单模态框 --%>
<jsp:include page="/WEB-INF/modal/menu/modal-menu-add.jsp"/>
<%-- 加载修改菜单模态框 --%>
<jsp:include page="/WEB-INF/modal/menu/modal-menu-edit.jsp"/>
</body>
</html>
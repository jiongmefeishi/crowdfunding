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

        // 1.Ajax请求服务器获取菜单列表的树形结构的JSON数据
        $.ajax({
            "url": "menu/get/whole/tree.json",
            "type": "post",
            "data": {

            },
            "dataType": "json",
            "success": function (res) {
                var result = res.result;
                if(result == "SUCCESS") {

                    // 2.获取用来生成树形结构的JSON数据
                    var zNodes = res.data;
                    // 3.创建 ztree 所需的JSON设置
                    var setting = {
                        "view": { // 菜单节点显示视图相关
                            // 调用myAddDiyDom() 函数设置图标
                            "addDiyDom": myAddDiyDom,
                            // 调用myAddHoverDom() 函数给菜单节点增加鼠标悬停效果和动画
                            // 鼠标悬停：显示修改和删除按钮
                            "addHoverDom": myAddHoverDom,
                            // 鼠标离开：移除修改和删除按钮
                            "removeHoverDom": myRemoveHoverDom
                        },
                        "data": {
                            "key": { // 菜单节点属性设置相关
                                // 设置点击菜单节点，不可跳转
                                "url": "xUrl" // 默认为url,即对应后台返回的菜单节点的url 属性
                            }
                        }
                    };
                    // 4.初始化树形结构
                    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                }

                if (result == "FAILED"){
                    layer.msg(res.message)
                }
            },
            "error": function (res) {

            }
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
</body>
</html>
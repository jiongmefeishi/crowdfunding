/**
 * 封装菜单树创建函数
 *
 * 调用此函数创建菜单树结构
 */
function generateMenuTree() {
    // 1.Ajax请求服务器获取菜单列表的树形结构的JSON数据
    $.ajax({
        "url": "menu/get/whole/tree.json",
        "type": "post",
        "data": {},
        "dataType": "json",
        "success": function (res) {
            var result = res.result;
            if (result == "SUCCESS") {

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

            if (result == "FAILED") {
                layer.msg(res.message)
            }
        },
        "error": function (res) {
            layer.msg(res.status + res.statusText)
        }
    });
}

/**
 * 每一个菜单节点的生成都会调用此函数，修改默认的图标
 * @param treeId treeId 是整个树形结构附着的 ul 标签的 id
 * @param treeNode 当前树形节点的全部的数据，包括从后端查询得到的 Menu 对象的全部属性
 */
function myAddDiyDom(treeId, treeNode) {
    //treeId 是整个树形结构附着的 ul 标签的 id
    // console.log("treeId=" + treeId);
    // 当前树形节点的全部的数据，包括从后端查询得到的 Menu 对象的全部属性
    // console.log(treeNode);


    // 1.根据<span> 标签 id 的生成规则，拼接出对应的id
    // zTree生成id的规则
    // 例子：treeDemo_7_ico
    // 解析：ul标签的id_当前节点的序号_功能
    // 提示：“ul标签的id_当前节点的序号”部分可以通过访问treeNode的tId属性得到
    // 根据id的生成规则拼接出来span标签的id
    var spanId = treeNode.tId + "_ico";

    // 2.根据控制图标的<span> 标签的id，找到对应控制菜单树图标的<span>标签
    $("#" + spanId)
        .removeClass()  // 3.清除原本的 class
        .addClass(treeNode.icon);// 4.添加新的 class 样式，样式来自服务端返回的数据菜单节点的 icon 属性
}

/**
 * 给菜单节点增加鼠标悬停效果和动画
 * 鼠标悬停：显示修改和删除按钮
 *
 * @param treeId treeId 是整个树形结构附着的 ul 标签的 id
 * @param treeNode 当前树形节点的全部的数据，包括从后端查询得到的 Menu 对象的全部属性
 */
function myAddHoverDom(treeId, treeNode) {

    // 按钮组的标签结构：<span><a><i></i></a><a><i></i></a></span>
    // 按钮组出现的位置：节点中treeDemo_n_a超链接的后面

    // 为了在需要移除按钮组的时候能够精确定位到按钮组所在span，需要给span设置有规律的id
    var btnGroupId = treeNode.tId + "_btnGrp";

    // 判断一下以前是否已经添加了按钮组,有则结束函数
    if ($("#" + btnGroupId).length > 0) {
        return;
    }

    // 准备各个按钮的HTML标签
    var addBtn = "<a id='" + treeNode.id + "' class='addBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='添加子节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-plus rbg '></i></a>";
    var removeBtn = "<a id='" + treeNode.id + "' class='removeBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='删除节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-times rbg '></i></a>";
    var editBtn = "<a id='" + treeNode.id + "' class='editBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='修改节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-edit rbg '></i></a>";

    // 获取当前节点的级别数据
    var level = treeNode.level;

    // 声明变量存储拼装好的按钮代码
    var btnHTML = "";

    // 判断当前节点的级别，依据级别的不同，绑定不同的按钮
    // level0：根节点 添加子节点
    // level1：分支节点 修改 添加子节点 没有子节点：可以删除 有子节点：不能删除
    // level2：叶子节点 修改 删除
    if (level == 0) {
        // 级别为0时是根节点，只能添加子节点
        btnHTML = addBtn;
    } else if (level == 1) {
        // 级别为1时是分支节点，可以添加子节点、修改
        btnHTML = addBtn + " " + editBtn;
        // 获取当前节点的子节点数量
        var length = treeNode.children.length;
        // 如果没有子节点，可以删除
        if (length == 0) {
            btnHTML = btnHTML + " " + removeBtn;
        }
    } else if (level == 2) {
        // 级别为2时是叶子节点，可以修改、删除
        btnHTML = editBtn + " " + removeBtn;
    }

    // 找到按钮组所依赖的超链接
    var anchorId = treeNode.tId + "_a";

    // 执行在超链接后面附加span元素的操作
    $("#" + anchorId).after("<span id='" + btnGroupId + "'>" + btnHTML + "</span>");

}


/**
 * 移除菜单节点鼠标悬停效果和动画
 * 鼠标离开：取消显示修改和删除按钮
 *
 * @param treeId treeId 是整个树形结构附着的 ul 标签的 id
 * @param treeNode 当前树形节点的全部的数据，包括从后端查询得到的 Menu 对象的全部属性
 */
function myRemoveHoverDom(treeId, treeNode) {

    // 拼接按钮组的id
    var btnGroupId = treeNode.tId + "_btnGrp";

    // 移除对应的元素
    $("#" + btnGroupId).remove();
}

/**
 * 新增一条菜单记录
 * @param menu 菜单实体
 */
function saveMenu(menu) {

    $.ajax({
        "url": "menu/save.json",
        "type": "post",
        "data": menu,
        "dataType": "json",
        "success": function (res) {
            let result = res.result;
            if (result == "SUCCESS") {
                layer.msg("操作成功！");

                // 重新加载树形结构，注意：要在确认服务器端完成保存操作后再刷新
                // 否则有可能刷新不到最新的数据，因为这里是异步的
                generateMenuTree();
            }

            if (result == "FAILED") {
                layer.msg("操作失败！" + response.message);
            }
        },
        "error": function (response) {
            layer.msg(response.status + " " + response.statusText);
        }
    });
}


/**
 * 更新一条菜单记录
 * @param menu 菜单实体
 */
function updateMenu(menu) {
    $.ajax({
        "url": "menu/update.json",
        "type": "post",
        "data": menu,
        "dataType": "json",
        "success": function (res) {
            var result = res.result;

            if (result == "SUCCESS") {
                layer.msg("操作成功！");
                generateMenuTree();
            }

            if (result == "FAILED") {
                layer.msg("操作失败！" + res.message);
            }
        },
        "error": function (res) {
            layer.msg(res.status + " " + res.statusText);
        }
    });
}

/**
 * 删除一条菜单记录
 * @param menuId 菜单id
 */
function removeMenu(menuId) {
    $.ajax({
        "url": "menu/remove.json",
        "type": "post",
        "data": {
            "id": menuId
        },
        "dataType": "json",
        "success": function (response) {
            var result = response.result;

            if (result == "SUCCESS") {
                layer.msg("操作成功！");

                // 重新加载树形结构，注意：要在确认服务器端完成保存操作后再刷新
                // 否则有可能刷新不到最新的数据，因为这里是异步的
                generateMenuTree();
            }

            if (result == "FAILED") {
                layer.msg("操作失败！" + response.message);
            }
        },
        "error": function (response) {
            layer.msg(response.status + " " + response.statusText);
        }
    });
}

/**
 * 调用此方法生成Role信息数据分页的展示页
 * 执行分页，生成页面效果，任何时候调用此方法都会重新加载页面
 */
function generateRolePage() {
    // 1.获取分页数据
    var pageInfo = getPageInfoRemote();

    // 2.填充分页数据表格 body 部分
    fillTableBody(pageInfo);
}

/**
 * 获取远程数据，即请求服务器，请求获取数据库中Role 的数据
 */
function getPageInfoRemote() {
    // 发起Ajax 请求，请求获取服务端数据
    // 第一种 Ajax 写法，成功和失败均直接进行处理
    /*    $.ajax({
            "url": "role/get/page/info.json", // 请求地址
            "type": "post", // 请求方式
            "data": { // 请求携带的数据
                "pageNum": window.pageNum,
                "pageSize": window.pageSize,
                "keyword": window.keyword
            },
            "async":false, // 关闭异步请求
            "dataType": "json", // 服务端返回的数据类型 ，json 格式接收
            "success": function (res) { // 请求成功，处理函数

            },
            "error": function (res) { // 请求失败，处理函数

            }
        });*/

    // 第二种Ajax 写法，先请求，随后处理
    // 1.获取Ajax请求结果
    var ajaxResult = $.ajax({
        "url": "role/get/page/info.json", // 请求地址
        "type": "post", // 请求方式
        "data": { // 请求携带的数据
            "pageNum": window.pageNum,
            "pageSize": window.pageSize,
            "keyword": window.keyword
        },
        "async": false, // 关闭异步请求
        "dataType": "json", // 服务端返回的数据类型
    });

    // 2.获取当前响应状态码，判断是否200
    var statusCode = ajaxResult.status;
    // 如果当前响应状态码不是200，说明发生了错误或其他意外情况，显示提示消息，让当前函数停止执行
    if (statusCode != 200) {
        layer.msg("失败！响应状态码=" + statusCode + " 说明信息=" + ajaxResult.statusText);
        return null;
    }
    // 如果响应状态码是200，说明请求处理成功，获取pageInfo
    var resultEntity = ajaxResult.responseJSON;

    // 3.获取服务端返回的result 属性数据
    var result = resultEntity.result;

    // 判断result是否表示成功,如果失败,显示提示消息,结束当前函数运行
    if (result == "FAILED") {
        layer.msg(resultEntity.message);
        return null;
    }

    // 4.确认result为成功后获取pageInfo
    var pageInfo = resultEntity.data;

    // 返回pageInfo
    return pageInfo;
}

/**
 * 填充表格
 * @param pageInfo 封装了Role 的分页数据对象
 */
function fillTableBody(pageInfo) {

    // 1.清除旧数据，<tbody> 中旧数据
    // （点击翻页时，会调用fillTableBody() 函数将新一页的数据拼接进表格标签中，
    // 上一页数据不会自动清除，这里需要手动清除）
    $("#rolePageBody").empty();
    // 这里清空是为了让没有搜索结果时不显示页码导航条
    $("#Pagination").empty();


    // 2.判断pageInfo对象是否有效
    if (pageInfo == null ||
        pageInfo == undefined ||
        pageInfo.list == null ||
        pageInfo.list.length == 0
    ) {
        $("#rolePageBody").append("<tr><td colspan='4'>抱歉，没有查询到您要的数据</td></tr>");
        return;
    }

    // 3.有效，使用pageInfo对象中封装的list填充分页数据
    for (var i = 0; i < pageInfo.list.length; i++) {

        // 获取role对象
        var role = pageInfo.list[i];
        // 获取role的属性
        var roleId = role.id;
        var roleName = role.name;

        // 生成分页数据表的各表项标签
        var numberTd = "<td>" + (i + 1) + "</td>";
        var checkBoxTd = "<td><input id='" + roleId + "' class='itemBox' type='checkbox'></td>";
        var roleNameTd = "<td>" + roleName + "</td>";

        // 生成按钮标签
        var checkBtn = "<button type='button' class='btn btn-success btn-xs'><i class=' glyphicon glyphicon-check'></i></button>";
        // 通过button标签的id属性（别的属性其实也可以）把roleId值传递到button按钮的单击响应函数中，在单击响应函数中使用this.id
        var pencilBtn = "<button id='" + roleId + "' type='button' class='btn btn-primary btn-xs pencilBtn'><i class=' glyphicon glyphicon-pencil'></i></button>";
        // 通过button标签的id属性（别的属性其实也可以）把roleId值传递到button按钮的单击响应函数中，在单击响应函数中使用this.id
        var removeBtn = "<button id='" + roleId + "' type='button' class='btn btn-danger btn-xs removeBtn'><i class=' glyphicon glyphicon-remove'></i></button>";
        // 拼接各个按钮到一个 <td> 中
        var buttonTd = "<td>" + checkBtn + " " + pencilBtn + " " + removeBtn + "</td>";

        // 生成 表格 <tr>, 组装所有的 <td> 标签
        var tr = "<tr>" + numberTd + checkBoxTd + roleNameTd + buttonTd + "</tr>";

        // 将组装的表格标签，追加到 表格标签 <tbody> 中
        $("#rolePageBody").append(tr);
    }

    // 生成分页导航条
    generateNavigator(pageInfo);
}

/**
 * 分成分页页码导航条
 * @param pageInfo 封装了Role 的分页数据对象
 */
function generateNavigator(pageInfo) {
    // 1.获取总记录数
    var totalRecord = pageInfo.total;

    // 2.声明相关属性，声明一个 JSON对象存储 Pagination 要设置的属性
    var properties = {
        "num_edge_entries": 3,								 // 边缘页数
        "num_display_entries": 5,                            // 主体页数
        "callback": paginationCallBack,                      // 指定用户点击“翻页”的按钮时跳转页面的回调函数
        "items_per_page": pageInfo.pageSize,                 // 每页要显示的数据的数量
        "current_page": pageInfo.pageNum - 1,                // Pagination内部使用pageIndex来管理页码，pageIndex从0开始，pageNum从1开始，所以要减一
        "prev_text": "上一页",                                // 上一页按钮上显示的文本
        "next_text": "下一页"                                 // 下一页按钮上显示的文本
    }

    // 调用pagination()函数， 执行分页
    $("#Pagination").pagination(totalRecord, properties);
}

/**
 * 翻页回调函数
 * @param pageIndex 页码
 * @param jQuery
 */
function paginationCallBack(pageIndex, jQuery) {
    // 修改window对象的pageNum 属性
    window.pageNum = pageIndex + 1;
    // 调用分页函数
    generateRolePage();

    // 取消页码超链接的默认行为
    return false;
}

/**
 * 获取role 的分页总数
 */
function getTotalPageNum() {

    // 1.获取分页数据
    var pageInfo = getPageInfoRemote();

    // 2.获取总页数
    var totalPages = pageInfo["pages"];
    return totalPages;
}


/**
 * 显示确认模态框：role 删除时弹出进行确认
 */
function showConfirmModal(roleArray) {

    // 打开确认提示模态框
    $("#confirmModal").modal('show');

    // 先清除上一次遗留的信息
    $("#roleNameDiv").empty();

    // 创建全局范围内的 roleIdList
    window.roleIdList = [];

    // 遍历roleArray 数组
    for (let i = 0; i < roleArray.length; i++) {
        // 取出 Role，填充进模态框
        var role = roleArray[i];
        var roleName = role.roleName;
        $("#roleNameDiv").append(roleName + "<br/>");

        var roleId = role.roleId;
        // 通过 数组的 push() 方法将 roleId 添加到集合
        window.roleIdList.push(roleId);
    }
}


/**
 * 新增一条 Role 记录
 * 成功，弹出提示，重新加载分页数据
 * 失败，弹出提示
 * 异常，弹出错误信息
 * @param roleName 新增的Role对象的名称
 */
function saveRole(roleName) {
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
}


/**
 * 更新一条 Role 记录
 * 成功，弹出提示，重新加载分页数据
 * 失败，弹出提示
 * 异常，弹出错误信息
 * @param roleName 新增的Role对象的名称
 */
function updateRole(roleName) {
    console.log("update")
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
}

/**
 * 删除 Role 记录，根据roleIdArray（转为JSON字符串） 删除role记录（单/多）
 * 成功，弹出提示，重新加载分页数据
 * 失败，弹出提示
 * 异常，弹出错误信息
 * @param requestBody 从全局变量范围获取的roleIdArray，转换为的JSON字符串
 */
function removeRole(requestBody) {
    console.log("remove")

    $.ajax({
        "url": "role/remove/by/role/id/array.json",
        "type": "post",
        // 集合形式，后端也没有准备响应的接收实体，那么就采用JSON字符串形式传递
        // 同样，后端也需要使用@RequestBody List<Integer> roleIdList 进行接收
        "data": requestBody,
        "dataType": "json",
        // 传入的是JSON 字符串，需要指定字符集
        "contentType": "application/json;charset=UTF-8",
        "success": function (res) {
            var result = res.result;
            if (result == "SUCCESS") {
                layer.msg("操作成功")
                // 删除成功，重新加载页面
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
}
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
        var checkBoxTd = "<td><input type='checkbox'></td>";
        var roleNameTd = "<td>" + roleName + "</td>";

        // 生成按钮标签
        var checkBtn = "<button type='button' class='btn btn-success btn-xs'><i class=' glyphicon glyphicon-check'></i></button>";
        // 通过button标签的id属性（别的属性其实也可以）把roleId值传递到button按钮的单击响应函数中，在单击响应函数中使用this.id
        var pencilBtn = "<button id='" + roleId + "' type='button' class='btn btn-primary btn-xs pencilBtn'><i class=' glyphicon glyphicon-pencil'></i></button>";
        // 通过button标签的id属性（别的属性其实也可以）把roleId值传递到button按钮的单击响应函数中，在单击响应函数中使用this.id
        var removeBtn = "<button id='" + roleId + "' type='button' class='btn btn-danger btn-xs removeBtn'><i class=' glyphicon glyphicon-remove'></i></button>";
        // 拼接各个按钮到一个 <td> 中
        var buttonTd = "<td>"+checkBtn+" "+pencilBtn+" "+removeBtn+"</td>";

        // 生成 表格 <tr>, 组装所有的 <td> 标签
        var tr = "<tr>"+numberTd+checkBoxTd+roleNameTd+buttonTd+"</tr>";

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

    // 2.声明相关属性，声明一个 JSON对象存储Pagination 要设置的属性
    var properties = {
        "num_edge_entries": 3,
        "num_display_entries": 5,
        "callback": paginationCallBack,
        "items_per_page": pageInfo.pageSize,
        "current_page": pageInfo.pageNum - 1,
        "prev_text": "上一页",
        "next_text": "下一页"
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
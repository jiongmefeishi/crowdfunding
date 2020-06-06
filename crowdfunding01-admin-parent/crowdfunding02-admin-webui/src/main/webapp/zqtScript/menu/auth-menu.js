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
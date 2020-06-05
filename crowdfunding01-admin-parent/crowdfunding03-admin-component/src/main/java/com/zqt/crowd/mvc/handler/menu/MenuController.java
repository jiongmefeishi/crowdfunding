package com.zqt.crowd.mvc.handler.menu;

import com.zqt.crowd.entity.menu.Menu;
import com.zqt.crowd.service.api.menu.MenuService;
import com.zqt.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther: zqtao
 * @description: 菜单管理控制层
 * @Date: 2020/6/5 14:55
 * @version: 1.0
 */
@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 获取所有的菜单节点
     */
    @ResponseBody
    @RequestMapping("/menu/get/whole/tree.json")
    public ResultEntity<Menu> getWholeTreeNew() {

        // 1.查询所有的菜单节点
        List<Menu> menuList = menuService.getAll();

        // 2.声明一个变量用来存储找到的根节点
        Menu root = null;

        // 3.创建map 用来存储菜单 id 和 Menu 对象的对应关系便于后续根据父节点id查找父节点
        Map<Integer, Menu> menuMap = new HashMap<>();
        for (Menu menu : menuList) {
            // 获取菜单 id
            Integer id = menu.getId();
            menuMap.put(id, menu);
        }

        // 4.遍历菜单节点集合，映射父子关系
        for (Menu menu : menuList) {

            // 获取当前菜单节点对象的父菜单节点的 pid
            Integer pid = menu.getPid();
            // 检查pid 是否为null，null表示是根节点,没有父节点
            if (pid == null) {
                root = menu;
                continue;
            }

            // 不是根节点，找到父节点，组装建立父子关系
            Menu parentMenu = menuMap.get(pid);
            // 将子节点存入父节点的 children 集合
            parentMenu.getChildren().add(menu);
        }

        // 经过上面的运算，根节点包含了整个菜单的树形结构，返回根节点就是返回整个树
        return ResultEntity.successWithData(root);
    }
}

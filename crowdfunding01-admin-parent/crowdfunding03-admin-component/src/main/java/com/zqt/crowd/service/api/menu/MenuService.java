package com.zqt.crowd.service.api.menu;

import com.zqt.crowd.entity.menu.Menu;

import java.util.List;

/**
 * @auther: zqtao
 * @description: 权限校验之菜单管理业务层接口
 * @Date: 2020/6/5 14:55
 * @version: 1.0
 */
public interface MenuService {

    /**
     * 获取所有的菜单节点
     * @return 菜单节点集合
     */
    List<Menu> getAll();

    void saveMenu(Menu menu);
}

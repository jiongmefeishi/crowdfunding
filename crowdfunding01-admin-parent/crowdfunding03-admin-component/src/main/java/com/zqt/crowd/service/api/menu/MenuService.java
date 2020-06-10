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

    /**
     * 新增一条菜单记录
     * @param menu 菜单
     */
    void saveMenu(Menu menu);

    /**
     * 更新一条菜单记录
     * @param menu 菜单
     */
    void updateMenu(Menu menu);

    /**
     * 根据菜单id删除一条菜单记录
     * @param menuId 菜单id
     */
    void removeMenu(Integer menuId);
}

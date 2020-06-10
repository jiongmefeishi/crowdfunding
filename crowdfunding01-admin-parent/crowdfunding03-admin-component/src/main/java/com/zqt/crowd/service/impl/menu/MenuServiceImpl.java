package com.zqt.crowd.service.impl.menu;

import com.zqt.crowd.entity.menu.Menu;
import com.zqt.crowd.entity.menu.MenuExample;
import com.zqt.crowd.mapper.menu.MenuMapper;
import com.zqt.crowd.service.api.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther: zqtao
 * @description: 权限校验之菜单管理业务层实现类
 * @Date: 2020/6/5 14:56
 * @version: 1.0
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getAll() {
        return menuMapper.selectByExample(new MenuExample());
    }

    @Override
    public void saveMenu(Menu menu) {
        menuMapper.insert(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public void removeMenu(Integer menuId) {
        menuMapper.deleteByPrimaryKey(menuId);
    }
}

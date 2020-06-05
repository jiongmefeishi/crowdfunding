package com.zqt.crowd.entity.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: zqtao
 * @description: 权限校验之菜单实体类
 * @Date: 2020/6/5 14:55
 * @version: 1.0
 */
public class Menu {

    /**
     * 菜单主键
     */
    private Integer id;

    /**
     * 菜单父节点 ID
     */
    private Integer pid;

    /**
     * 菜单节点名称
     */
    private String name;

    /**
     * 菜单节点附带的 URL 地址
     * 点击当前节点需要跳转到的地址
     */
    private String url;

    /**
     * 节点图标的样式
     */
    private String icon;

    /**
     * 扩展属性：存放当前菜单节点下的所有子菜单节点的集合
     */
    private List<Menu> children;

    /**
     * 扩展属性：前端展示菜单树使用 zTree， open 属性表示菜单是否打开，显示子菜单节点
     */
    private boolean open;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public Menu() {
        // 初始化集合是为了避免空指针异常
        this.children = new ArrayList<Menu>();
        // 默认设置打开
        this.open = true;
    }

    public Menu(Integer id, Integer pid, String name, String url, String icon) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.url = url;
        this.icon = icon;
        // 初始化集合是为了避免空指针异常
        this.children = new ArrayList<Menu>();
        // 默认设置打开
        this.open = true;
    }

    public Menu(Integer id, Integer pid, String name, String url, String icon, List<Menu> children, boolean open) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.url = url;
        this.icon = icon;
        this.children = children;
        this.open = open;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", children=" + children +
                ", open=" + open +
                '}';
    }
}
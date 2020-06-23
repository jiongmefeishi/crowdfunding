package com.zqt.crowd.entity.auth;

/**
 * @author: zqtao
 * @description: 权限校验之权限实体类
 * @Date: 2020/6/11 14:55
 * @version: 1.0
 */
public class Auth {

    /**
     * 权限主键
     */
    private Integer id;

    /**
     * 权限具体值（英文如：delete，update）
     */
    private String name;

    /**
     * 权限展示值（中文如：删除，修改）
     */
    private String title;

    /**
     * 权限所属模块类别（和自身关联，无关联则是模块，有关联则是模块下子权限）
     */
    private Integer categoryId;

    public Auth() {
    }

    public Auth(Integer id, String name, String title, Integer categoryId) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.categoryId = categoryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Auth{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
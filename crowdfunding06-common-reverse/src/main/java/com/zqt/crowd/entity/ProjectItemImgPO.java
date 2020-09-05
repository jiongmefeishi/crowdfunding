package com.zqt.crowd.entity;

public class ProjectItemImgPO {
    private Integer id;

    private Integer projectId;

    private String itemImgPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getItemImgPath() {
        return itemImgPath;
    }

    public void setItemImgPath(String itemImgPath) {
        this.itemImgPath = itemImgPath == null ? null : itemImgPath.trim();
    }
}
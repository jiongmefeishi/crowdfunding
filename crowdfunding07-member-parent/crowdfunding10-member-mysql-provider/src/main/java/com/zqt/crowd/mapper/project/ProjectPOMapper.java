package com.zqt.crowd.mapper.project;

import com.zqt.crowd.entity.po.project.ProjectPO;
import com.zqt.crowd.entity.po.project.ProjectPOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectPOMapper {
    int countByExample(ProjectPOExample example);

    int deleteByExample(ProjectPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectPO record);

    int insertSelective(ProjectPO record);

    List<ProjectPO> selectByExample(ProjectPOExample example);

    ProjectPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectPO record, @Param("example") ProjectPOExample example);

    int updateByExample(@Param("record") ProjectPO record, @Param("example") ProjectPOExample example);

    int updateByPrimaryKeySelective(ProjectPO record);

    int updateByPrimaryKey(ProjectPO record);

    /**
     * 保存项目、分类的关联关系信息
     *
     * @param typeIdList 项目分类 id 集合
     * @param projectId  项目id
     */
    void insertInnerTypeAndProject(@Param("typeIdList") List<Integer> typeIdList, @Param("projectId") Integer projectId);

    /**
     * 保存项目、标签的关联关系信息
     *
     * @param tagIdList 项目标签 id 集合
     * @param projectId 项目id
     */
    void insertInnerTagAndProject(@Param("tagIdList") List<Integer> tagIdList, @Param("projectId") Integer projectId);
}
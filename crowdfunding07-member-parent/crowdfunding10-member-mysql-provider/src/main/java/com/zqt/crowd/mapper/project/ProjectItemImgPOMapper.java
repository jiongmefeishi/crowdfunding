package com.zqt.crowd.mapper.project;

import com.zqt.crowd.entity.po.project.ProjectItemImgPO;
import com.zqt.crowd.entity.po.project.ProjectItemImgPOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectItemImgPOMapper {
    int countByExample(ProjectItemImgPOExample example);

    int deleteByExample(ProjectItemImgPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectItemImgPO record);

    int insertSelective(ProjectItemImgPO record);

    List<ProjectItemImgPO> selectByExample(ProjectItemImgPOExample example);

    ProjectItemImgPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectItemImgPO record, @Param("example") ProjectItemImgPOExample example);

    int updateByExample(@Param("record") ProjectItemImgPO record, @Param("example") ProjectItemImgPOExample example);

    int updateByPrimaryKeySelective(ProjectItemImgPO record);

    int updateByPrimaryKey(ProjectItemImgPO record);
}
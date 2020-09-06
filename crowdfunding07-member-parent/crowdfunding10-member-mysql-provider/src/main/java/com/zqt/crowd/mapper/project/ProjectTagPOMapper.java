package com.zqt.crowd.mapper.project;

import com.zqt.crowd.entity.po.project.ProjectTagPO;
import com.zqt.crowd.entity.po.project.ProjectTagPOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectTagPOMapper {
    int countByExample(ProjectTagPOExample example);

    int deleteByExample(ProjectTagPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectTagPO record);

    int insertSelective(ProjectTagPO record);

    List<ProjectTagPO> selectByExample(ProjectTagPOExample example);

    ProjectTagPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectTagPO record, @Param("example") ProjectTagPOExample example);

    int updateByExample(@Param("record") ProjectTagPO record, @Param("example") ProjectTagPOExample example);

    int updateByPrimaryKeySelective(ProjectTagPO record);

    int updateByPrimaryKey(ProjectTagPO record);
}
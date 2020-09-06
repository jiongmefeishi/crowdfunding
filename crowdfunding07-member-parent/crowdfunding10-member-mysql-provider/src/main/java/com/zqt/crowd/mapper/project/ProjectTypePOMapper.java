package com.zqt.crowd.mapper.project;

import com.zqt.crowd.entity.po.project.ProjectTypePO;
import com.zqt.crowd.entity.po.project.ProjectTypePOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectTypePOMapper {
    int countByExample(ProjectTypePOExample example);

    int deleteByExample(ProjectTypePOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectTypePO record);

    int insertSelective(ProjectTypePO record);

    List<ProjectTypePO> selectByExample(ProjectTypePOExample example);

    ProjectTypePO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectTypePO record, @Param("example") ProjectTypePOExample example);

    int updateByExample(@Param("record") ProjectTypePO record, @Param("example") ProjectTypePOExample example);

    int updateByPrimaryKeySelective(ProjectTypePO record);

    int updateByPrimaryKey(ProjectTypePO record);
}
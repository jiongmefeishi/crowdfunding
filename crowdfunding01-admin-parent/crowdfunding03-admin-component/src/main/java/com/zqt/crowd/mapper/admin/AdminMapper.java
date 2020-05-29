package com.zqt.crowd.mapper.admin;

import com.zqt.crowd.entity.admin.Admin;
import com.zqt.crowd.entity.admin.AdminExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    /**
     * 根据关键词查询记录，模糊查询
     * @param keyword 关键词
     * @return admin list
     */
    List<Admin> selectAdminByKeyword(String keyword);
}
package com.zqt.crowd.mapper.admin;

import com.zqt.crowd.entity.admin.Admin;
import com.zqt.crowd.entity.admin.AdminExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理员数据层操纵接口
 */
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
     *
     * @param keyword 关键词
     * @return admin list
     */
    List<Admin> selectAdminByKeyword(String keyword);

    /**
     * 根据adminId删除旧的关联关系数据
     *
     * @param adminId adminId
     */
    void deleteOLdRelationship(Integer adminId);

    /**
     * 根据roleIdList和adminId保存新的关联关系
     *
     * @param adminId    adminId
     * @param roleIdList 当前adminId 需要绑定的 role 的集合
     */
    void insertNewRelationship(@Param("adminId") Integer adminId, @Param("roleIdList") List<Integer> roleIdList);
}
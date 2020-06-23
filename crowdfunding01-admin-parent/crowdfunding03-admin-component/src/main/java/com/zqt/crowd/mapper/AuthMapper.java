package com.zqt.crowd.mapper;

import com.zqt.crowd.entity.auth.Auth;
import com.zqt.crowd.entity.auth.AuthExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 权限校验之权限数据层操纵接口
 */
public interface AuthMapper {
    int countByExample(AuthExample example);

    int deleteByExample(AuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Auth record);

    int insertSelective(Auth record);

    List<Auth> selectByExample(AuthExample example);

    Auth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByExample(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    /**
     * 根据角色id 查询分配到的 权限列表
     * @param roleId 角色id
     * @return 权限id列表
     */
    List<Integer> selectAssignedAuthIdByRoleId(Integer roleId);

    /**
     * 根据roleId 删除旧的关联关系
     * @param roleId 角色id
     */
    void deleteOldRelationship(Integer roleId);

    /**
     * 根据角色id 和 权限id列表 建立新的关联关系
     * @param roleId 角色id
     * @param authIdList 权限id列表
     */
    void insertNewRelationship(@Param("roleId") Integer roleId, @Param("authIdList") List<Integer> authIdList);

    /**
     * 根据管理员 id 查询已经分配到的 权限列表 名称
     * @param adminId 角色id
     * @return 权限id列表
     */
    List<String> selectAssignedAuthNameByAdminId(Integer adminId);
}
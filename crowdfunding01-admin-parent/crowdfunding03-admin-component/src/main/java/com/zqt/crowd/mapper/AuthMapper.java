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
}
package com.zqt.crowd.mapper;

import com.zqt.crowd.entity.OrderReturnInfoPO;
import com.zqt.crowd.entity.OrderReturnInfoPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderReturnInfoPOMapper {
    int countByExample(OrderReturnInfoPOExample example);

    int deleteByExample(OrderReturnInfoPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderReturnInfoPO record);

    int insertSelective(OrderReturnInfoPO record);

    List<OrderReturnInfoPO> selectByExample(OrderReturnInfoPOExample example);

    OrderReturnInfoPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderReturnInfoPO record, @Param("example") OrderReturnInfoPOExample example);

    int updateByExample(@Param("record") OrderReturnInfoPO record, @Param("example") OrderReturnInfoPOExample example);

    int updateByPrimaryKeySelective(OrderReturnInfoPO record);

    int updateByPrimaryKey(OrderReturnInfoPO record);
}
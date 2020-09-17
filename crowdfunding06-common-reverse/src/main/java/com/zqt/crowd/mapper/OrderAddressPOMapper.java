package com.zqt.crowd.mapper;

import com.zqt.crowd.entity.OrderAddressPO;
import com.zqt.crowd.entity.OrderAddressPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderAddressPOMapper {
    int countByExample(OrderAddressPOExample example);

    int deleteByExample(OrderAddressPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderAddressPO record);

    int insertSelective(OrderAddressPO record);

    List<OrderAddressPO> selectByExample(OrderAddressPOExample example);

    OrderAddressPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderAddressPO record, @Param("example") OrderAddressPOExample example);

    int updateByExample(@Param("record") OrderAddressPO record, @Param("example") OrderAddressPOExample example);

    int updateByPrimaryKeySelective(OrderAddressPO record);

    int updateByPrimaryKey(OrderAddressPO record);
}
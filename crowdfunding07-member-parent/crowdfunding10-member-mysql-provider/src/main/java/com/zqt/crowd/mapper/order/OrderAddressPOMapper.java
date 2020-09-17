package com.zqt.crowd.mapper.order;

import com.zqt.crowd.entity.po.order.OrderAddressPO;
import com.zqt.crowd.entity.po.order.OrderAddressPOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
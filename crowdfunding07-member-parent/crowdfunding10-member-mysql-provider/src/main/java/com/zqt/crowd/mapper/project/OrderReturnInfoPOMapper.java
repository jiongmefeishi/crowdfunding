package com.zqt.crowd.mapper.project;

import com.zqt.crowd.entity.po.project.OrderReturnInfoPO;
import com.zqt.crowd.entity.po.project.OrderReturnInfoPOExample;
import com.zqt.crowd.entity.vo.project.OrderReturnInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 批量新增项目回报信息
     *
     * @param returnPOList 项目回报信息
     * @param projectId    项目id
     */
    void insertOrderReturnInfoPOBatch(@Param("returnPOList") List<OrderReturnInfoVO> returnPOList, @Param("projectId") Integer projectId);
}
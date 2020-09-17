package com.zqt.crowd.service.impl.order;

import com.zqt.crowd.entity.po.order.OrderAddressPO;
import com.zqt.crowd.entity.po.order.OrderAddressPOExample;
import com.zqt.crowd.entity.vo.order.OrderAddressVO;
import com.zqt.crowd.entity.vo.order.OrderProjectVO;
import com.zqt.crowd.mapper.order.OrderAddressPOMapper;
import com.zqt.crowd.mapper.order.OrderPOMapper;
import com.zqt.crowd.mapper.order.OrderProjectPOMapper;
import com.zqt.crowd.service.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: zqtao
 * @description: 订单业务层接口实现类
 */

@Slf4j
@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderProjectPOMapper orderProjectPOMapper;

    @Autowired
    private OrderPOMapper orderPOMapper;

    @Autowired
    private OrderAddressPOMapper addressPOMapper;

    /**
     * 查询回报确认信息
     *
     * @param projectId 项目id
     * @param returnId  回报信息 id
     */
    @Override
    public OrderProjectVO getOrderProjectVO(Integer projectId, Integer returnId) {

        return orderProjectPOMapper.selectOrderProjectVO(returnId);
    }

    /**
     * 获取会员收货地址列表
     *
     * @param memberId 会员id
     * @return List<OrderAddressVO>
     */
    @Override
    public List<OrderAddressVO> getAddressVOList(Integer memberId) {

        OrderAddressPOExample example = new OrderAddressPOExample();

        example.createCriteria().andMemberIdEqualTo(memberId);

        List<OrderAddressPO> addressPOList = addressPOMapper.selectByExample(example);

        List<OrderAddressVO> addressVOList = new ArrayList<>();

        for (OrderAddressPO addressPO : addressPOList) {
            OrderAddressVO addressVO = new OrderAddressVO();
            BeanUtils.copyProperties(addressPO, addressVO);

            addressVOList.add(addressVO);
        }

        return addressVOList;
    }

    /**
     * 保存收货地址
     *
     * @param addressVO 收货地址信息
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void saveAddress(OrderAddressVO addressVO) {

        OrderAddressPO addressPO = new OrderAddressPO();

        BeanUtils.copyProperties(addressVO, addressPO);

        addressPOMapper.insert(addressPO);

    }

}


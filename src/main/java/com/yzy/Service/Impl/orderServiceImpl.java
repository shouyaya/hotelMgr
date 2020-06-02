package com.yzy.Service.Impl;

import com.yzy.Dao.CheckininfoMapper;
import com.yzy.Entity.Checkininfo;
import com.yzy.Service.orderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("orderService")
public class orderServiceImpl implements orderService {

    @Resource(name = "checkInInfoDao")
    private CheckininfoMapper checkininfoMapper;

    @Override
    public int addOrderInfo(Checkininfo checkininfo) {
     return checkininfoMapper.insertCheckInInfo(checkininfo);
    }

    @Override
    public List<Checkininfo> findAllOrder(int page,int size,String flag) {
        int offset=(page-1)*size;
        return checkininfoMapper.findAll(offset,size,flag);
    }

    @Override
    public List<Checkininfo> findAllOrderUsable() {
        return checkininfoMapper.findAllOrderUsable();
    }

    @Override
    public List<Checkininfo> findOrderUsableById(String orderId) {
        return  checkininfoMapper.findOrderUsableById(orderId);
    }

    @Override
    public void updateOrderUsed(String orderId) {
        checkininfoMapper.updateOrderUsed(orderId);
    }

    @Override
    public List<Checkininfo> findOrderById(String orderId) {
        return checkininfoMapper.findOrderById(orderId);
    }

    @Override
    public List<Checkininfo> findOrderByName(String orderName) {
        return checkininfoMapper.findOrderByName(orderName);
    }

    @Override
    public int deleteOrderById(String orderId) {
        return checkininfoMapper.deleteOrderById(orderId);
    }

    @Override
    public int updateOrderById(Checkininfo checkininfo) {
        return checkininfoMapper.updateOrderById(checkininfo);
    }

}

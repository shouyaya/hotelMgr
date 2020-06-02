package com.yzy.Service;

import com.yzy.Entity.Checkininfo;

import java.util.List;

public interface orderService {

    int addOrderInfo(Checkininfo checkininfo);
    List<Checkininfo> findAllOrder(int page,int size,String flag);
    List<Checkininfo> findAllOrderUsable();
    List<Checkininfo> findOrderUsableById(String orderId);
    void updateOrderUsed(String orderId);
    List<Checkininfo> findOrderById(String orderId);
    List<Checkininfo> findOrderByName(String orderName);
    int deleteOrderById(String orderId);
    int updateOrderById(Checkininfo checkininfo);


}

package com.yzy.Service;

import com.yzy.Entity.Billinfo;

import java.util.List;

public interface billService {
    int addBillInfo(Billinfo billinfo);
    List<Billinfo> findAll(int offset,int size,String flag);
    List<Billinfo> findBillById(int billId);
    List<Billinfo> findBillByOrderId(String orderId);
    int deleteBillById(int billId);
}

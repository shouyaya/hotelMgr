package com.yzy.Service.Impl;

import com.yzy.Dao.BillinfoMapper;
import com.yzy.Entity.Billinfo;
import com.yzy.Service.billService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("billService")
public class billServiceImpl implements billService {
    @Resource(name = "BillInfoDao")
    private BillinfoMapper billinfoMapper;

    @Override
    public int addBillInfo(Billinfo billinfo) {
        return billinfoMapper.insertBillInfo(billinfo);
    }

    @Override
    public List<Billinfo> findAll(int offset, int size, String flag) {
        offset=(offset-1)*size;
        return billinfoMapper.findAll(offset,size,flag);
    }

    @Override
    public List<Billinfo> findBillById(int billId) {
        return billinfoMapper.findBillById(billId);
    }

    @Override
    public List<Billinfo> findBillByOrderId(String orderId) {
        return  billinfoMapper.findBillByOrderId(orderId);
    }

    @Override
    public int deleteBillById(int billId) {
        return  billinfoMapper.deleteBillById(billId);
    }
}

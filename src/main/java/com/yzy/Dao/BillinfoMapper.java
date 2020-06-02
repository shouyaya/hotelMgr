package com.yzy.Dao;

import com.yzy.Entity.Billinfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BillinfoMapper 数据访问类
 * @author xlei @qq 251425887 @tel 13352818008
 * @Email dlei0009@163.com
 * @date 2020-05-21 22:51:24
 * @version 1.0
 */

@Repository("BillInfoDao")
public interface BillinfoMapper {

    int insertBillInfo(Billinfo billinfo);
    List<Billinfo> findAll(@Param("offset") int offset, @Param("size") int size, @Param("flag") String flag);
    List<Billinfo> findBillById(int id);
    List<Billinfo> findBillByOrderId(String orderId);
    int deleteBillById(int id);

}
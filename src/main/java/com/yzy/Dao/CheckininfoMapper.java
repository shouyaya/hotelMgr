package com.yzy.Dao;

import com.yzy.Entity.Checkininfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CheckininfoMapper 数据访问类
 * @author xlei @qq 251425887 @tel 13352818008
 * @Email dlei0009@163.com
 * @date 2020-05-21 22:51:24
 * @version 1.0
 */

@Repository("checkInInfoDao")
public interface CheckininfoMapper {

    int insertCheckInInfo(Checkininfo checkininfo);
    List<Checkininfo> findAll(@Param("offset") int offset,@Param("size") int size,@Param("flag") String flag);
    List<Checkininfo> findOrderUsableById(String orderId);
    List<Checkininfo> findAllOrderUsable();
    void updateOrderUsed(String orderId);
    List<Checkininfo> findOrderById(String orderId);
    List<Checkininfo> findOrderByName(String orderName);
    int deleteOrderById(String orderId);
    int updateOrderById(Checkininfo checkininfo);

}
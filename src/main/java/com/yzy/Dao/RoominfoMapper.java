package com.yzy.Dao;

import com.yzy.Entity.Roominfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RoominfoMapper 数据访问类
 * @author xlei @qq 251425887 @tel 13352818008
 * @Email dlei0009@163.com
 * @date 2020-05-21 22:51:24
 * @version 1.0
 */

@Repository("roomInfoDao")
public interface RoominfoMapper {

List<Roominfo> findAll();
String findRoomTypeById(String id);
void updateRoomUsed(String roomId);
void updateRoomUsable(String roomId);
List<Roominfo> realFindAll(@Param("offset") int offset, @Param("size") int size, @Param("flag") String flag);
List<Roominfo> findById(String roomId);
List<Roominfo> findByTypeId(String typeId);
int deleteById(String roomId);
int updateRoomById(Roominfo roominfo);
int insertRoom(Roominfo roominfo);
}
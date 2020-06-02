package com.yzy.Service;

import com.yzy.Entity.Floorinfo;
import com.yzy.Entity.Roominfo;
import com.yzy.Entity.Roomtype;


import java.util.List;


public interface roomService {
    List<Roominfo> getRoomInfo();
    String getRoomTypeByRoomId(String id);
    void updateRoomUsed(String id);
    void updateRoomUsable(String id);
    List<Roominfo> realFindAll(int offset,int size,String flag);
    List<Roominfo> findById(String roomId);
    List<Roominfo> findByTypeId(String typeId);
    int deleteById(String roomId);
    int updateRoomById(Roominfo roominfo);
    int addRoom(Roominfo roominfo);

    int addRoomType(Roomtype roomtype);
    List<Roomtype> findAllRoomType();

    List<Floorinfo> findAllFloor();


}

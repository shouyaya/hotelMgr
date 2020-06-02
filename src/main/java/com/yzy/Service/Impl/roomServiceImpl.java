package com.yzy.Service.Impl;

import com.yzy.Dao.FloorinfoMapper;
import com.yzy.Dao.RoominfoMapper;
import com.yzy.Dao.RoomtypeMapper;
import com.yzy.Entity.Floorinfo;
import com.yzy.Entity.Roominfo;
import com.yzy.Entity.Roomtype;
import com.yzy.Service.roomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("roomService")
public class roomServiceImpl implements roomService {

    @Resource(name = "roomInfoDao")
    private RoominfoMapper roominfoMapper;

    @Resource(name = "roomTypeDao")
    private RoomtypeMapper roomtypeMapper;

    @Resource(name = "floorInfoDao")
    private FloorinfoMapper floorinfoMapper;

    @Override
    public List<Roominfo> getRoomInfo() {
        return roominfoMapper.findAll();
    }

    @Override
    public String getRoomTypeByRoomId(String id) {
        return roominfoMapper.findRoomTypeById(id);
    }

    @Override
    public void updateRoomUsed(String id) {
        roominfoMapper.updateRoomUsed(id);
    }

    @Override
    public void updateRoomUsable(String id) {
        roominfoMapper.updateRoomUsable(id);
    }

    @Override
    public List<Roominfo> realFindAll(int offset, int size, String flag) {
        offset=(offset-1)*size;
        return roominfoMapper.realFindAll(offset, size, flag);
    }

    @Override
    public List<Roominfo> findById(String roomId) {
        return  roominfoMapper.findById(roomId);
    }

    @Override
    public List<Roominfo> findByTypeId(String typeId) {
        return roominfoMapper.findByTypeId(typeId);
    }

    @Override
    public int deleteById(String roomId) {
        return  roominfoMapper.deleteById(roomId);
    }

    @Override
    public int updateRoomById(Roominfo roominfo) {
        return roominfoMapper.updateRoomById(roominfo);
    }

    @Override
    public int addRoom(Roominfo roominfo) {
        return roominfoMapper.insertRoom(roominfo);
    }

    @Override
    public int addRoomType(Roomtype roomtype) {
        return  roomtypeMapper.insertRoomType(roomtype);
    }

    @Override
    public List<Roomtype> findAllRoomType() {
        return roomtypeMapper.findAll();
    }

    @Override
    public List<Floorinfo> findAllFloor() {
        return floorinfoMapper.findAllFloor();
    }

}

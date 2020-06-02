package com.yzy.Controller;

import com.google.gson.Gson;
import com.yzy.Entity.Billinfo;
import com.yzy.Entity.Floorinfo;
import com.yzy.Entity.Roominfo;
import com.yzy.Entity.Roomtype;
import com.yzy.Global.PojotoGson;
import com.yzy.Service.roomService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@Controller("roomController")
public class roomController {
    @Resource(name = "roomService")
    roomService roomService;

    /**
     * 此方法用于选择房间时返回房间信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getRoomInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Roominfo> roomInfo = roomService.getRoomInfo();
        PrintWriter out = response.getWriter();
        Gson gson=new Gson();
        out.print(gson.toJson(roomInfo));
    }

    /**
     * 此方法用于跳转至房间查询页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void beforeSelect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/roomPages/roomTable.jsp").forward(request,response);
    }

    /**
     * 此方法获取所有房间信息至jsp并且实现删除查询功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public  void findAllRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = Integer.parseInt(request.getParameter("page")); // 当前页码
        int limit = Integer.parseInt(request.getParameter("limit")); // 每页的数据量
        int make = Integer.parseInt(request.getParameter("make")); //状态标志

        List<Roominfo> result=new ArrayList<>();  //存放结果的数组

        String code="0"; //数据状态字段
        String msg = "数据查询正常"; //状态信息
        String count; //数据总数

        //单个全局属性
        String roomId; //账单单号
        String select; //查询值

        //获取对应状态属性
        if (make == 1 || make == 2) { //1和2这部分完全是相同的  1：通过房间号查账单，2：通过房间类型号查账单
            select = request.getParameter("select");
            if(select!=null&&!select.equals("")) {
                if (make == 1) {
                    result = roomService.findById(select);
                } else {
                    result = roomService.findByTypeId(select);
                }
            }else{
                result = roomService.realFindAll(page, limit,"used");
            }
            count = String.valueOf(result.size());
            msg = "结果如下";
        } else if (make == 4) {                     //4：删除房间
            roomId = request.getParameter("roomId");
            if (roomService.deleteById(roomId) == -1) {
                msg = "删除失败";
                code = "-1";
            }
            count = String.valueOf(roomService.realFindAll(-1,-1,"noUsed").size());
        } else {
            result = roomService.realFindAll(page, limit,"used");
            count = String.valueOf(roomService.realFindAll(-1,-1,"noUsed").size());
        }

        PojotoGson pojotoGson=new PojotoGson(code,msg,count,result);
        Gson gson=new Gson();
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(pojotoGson));
    }

    /**
     * 获取房间类型号与楼层号至更新房间信息页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updateRoomProm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Roomtype> roomTypes = roomService.findAllRoomType();
        List<Floorinfo> floors = roomService.findAllFloor();

        request.setAttribute("roomTypes",roomTypes);
        request.setAttribute("floors",floors);
        request.getRequestDispatcher("/pages/roomPages/updateRoom.jsp").forward(request,response);
    }

    /**
     * 给更新房间信息提供对应的房间信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void beforeUpdateRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomId = request.getParameter("roomId");
//        System.out.println(roomId);
        List<Roominfo> roominfos = roomService.findById(roomId);
//        System.out.println(roominfos);
        Roominfo roominfo = roominfos.get(0);
        Gson gson=new Gson();
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(roominfo));
    }

    /**
     * 更新房间信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updateRoomInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomId = request.getParameter("roomId");
        String typeId = request.getParameter("typeId");
        String floorId = request.getParameter("floorId");
        String ratedNum = request.getParameter("ratedNum");
        String bedNum = request.getParameter("bedNum");
        String roomDescription = request.getParameter("roomDescription");
        String remark = request.getParameter("remark");
        String status = request.getParameter("status");

        Roominfo roominfo=new Roominfo();
        roominfo.setRoomid(roomId);
        roominfo.setTypeid(typeId);
        roominfo.setFloorid(Integer.parseInt(floorId));
        roominfo.setRatednum(Integer.parseInt(ratedNum));
        roominfo.setBednum(Integer.parseInt(bedNum));
        roominfo.setRoomdescription(roomDescription);
        roominfo.setRemark(remark);
        roominfo.setStatus(status);

//        System.out.println(roominfo);

        int i = roomService.updateRoomById(roominfo);
        PrintWriter out = response.getWriter();
        Gson gson=new Gson();
        out.print(gson.toJson(i));

    }

    /**
     * 此方法用于跳转至新增房间类型页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void beforeAddRoomType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/roomPages/addRoomType.jsp").forward(request,response);
    }

    /**
     * 此方法用于添加房间类型
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addRoomType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String typeId = request.getParameter("typeId");
        String typeName = request.getParameter("typeName");
        String price = request.getParameter("price");

        Roomtype roomtype=new Roomtype();

        roomtype.setTypeid(typeId);
        roomtype.setTypename(typeName);
        roomtype.setPrice(price);

        int i = roomService.addRoomType(roomtype);

        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(i));

    }

    /**
     * 此方法用于跳转新增房间页面并获取房间类型编号与楼层编号
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void beforeAddRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Roomtype> roomTypes = roomService.findAllRoomType();
        List<Floorinfo> floors = roomService.findAllFloor();

        request.setAttribute("roomTypes",roomTypes);
        request.setAttribute("floors",floors);
        request.getRequestDispatcher("/pages/roomPages/addRoom.jsp").forward(request,response);
    }

    public void addRoomInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomId = request.getParameter("roomId");
        String typeId = request.getParameter("typeId");
        String floorId = request.getParameter("floorId");
        String ratedNum = request.getParameter("ratedNum");
        String bedNum = request.getParameter("bedNum");
        String roomDescription = request.getParameter("roomDescription");
        String remark = request.getParameter("remark");
        String status = request.getParameter("status");

        Roominfo roominfo=new Roominfo();
        roominfo.setRoomid(roomId);
        roominfo.setTypeid(typeId);
        roominfo.setFloorid(Integer.parseInt(floorId));
        roominfo.setRatednum(Integer.parseInt(ratedNum));
        roominfo.setBednum(Integer.parseInt(bedNum));
        roominfo.setRoomdescription(roomDescription);
        roominfo.setRemark(remark);
        roominfo.setStatus(status);

        int i = roomService.addRoom(roominfo);
        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(i));
    }
}

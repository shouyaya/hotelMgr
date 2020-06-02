package com.yzy.Controller;

import com.google.gson.Gson;
import com.yzy.Entity.Billinfo;
import com.yzy.Entity.Checkininfo;
import com.yzy.Global.PojotoGson;
import com.yzy.Service.billService;
import com.yzy.Service.orderService;
import com.yzy.Service.roomService;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller("orderController")
public class orderController {


    @Resource(name = "orderService")
    private orderService orderService;

    @Resource(name = "roomService")
    private roomService roomService;

    @Resource(name = "billService")
    private billService billService;
    /**
     * 通过此方法进入订单页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void beforeAddOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/orderPages/order.jsp").forward(request,response);
    }


    /**
     * 通过此方法插入订单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void insertOrderInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //var params = "orderId=" + orderId + "&orderName=" + orderName + "&orderPhone=" + orderPhone +
        //						"&orderIDcard=" + orderIDcard + "&roomId=" + roomId + "&arrireDate=" + arrireDate +
        //						"&leaveDate=" + leaveDate + "&orderState=" + orderState + "&checkNum=" + checkNum +
        //						"&price=" + price + "&checkPrice=" + checkPrice + "&discount=" + discount +
        //						"&discountReason=" + discountReason + "&addBed=" + addBed + "&addBedPrice=" + addBedPrice +
        //						"&orderMoney=" + orderMoney + "&operatorId=" + operatorId + "&remark=" + remark + "&make=1";

        String checkId=request.getParameter("orderId");
        String checkName = request.getParameter("orderName");
        String checkPhone = request.getParameter("orderPhone");
        String checkIDcard = request.getParameter("orderIDcard");
        String roomId = request.getParameter("roomId");
        String arrireDate=request.getParameter("arrireDate");
        String leaveDate = request.getParameter("leaveDate");
        String checkState = request.getParameter("orderState"); //状态
        String checkNum = request.getParameter("checkNum");//入住人数
        String price = request.getParameter("price");   //客房价格
        String checkPrice = request.getParameter("checkPrice"); //入住天数*客房价格
        String discount = request.getParameter("discount");
        String discountReason = request.getParameter("discountReason");
        String addBed = request.getParameter("addBed");
        String addBedPrice = request.getParameter("addBedPrice");
        String orderMoney = request.getParameter("orderMoney");
        String deposit = request.getParameter("deposit");
        String operatorId = request.getParameter("operatorId");

        Checkininfo checkininfo=new Checkininfo();
        checkininfo.setCheckid(checkId);
        checkininfo.setCheckname(checkName);
        checkininfo.setCheckphone(checkPhone);
        checkininfo.setCheckidcard(checkIDcard);
        checkininfo.setRoomid(roomId);
        checkininfo.setArriretime(arrireDate);
        checkininfo.setLeavetime(leaveDate);
        checkininfo.setCheckstate(checkState);
        checkininfo.setChecknum(Integer.parseInt(checkNum));
        checkininfo.setPrice(price);
        checkininfo.setCheckprice(checkPrice);
        checkininfo.setDiscountreason(discountReason);
        checkininfo.setAddbed(addBed);
        checkininfo.setAddbedprice(addBedPrice);
        checkininfo.setCheckmoney(orderMoney); //预订给的钱
        checkininfo.setDeposit(deposit);
        checkininfo.setOperatorid(operatorId);
        //处理折扣置空
        if(discount!=null&&!discount.equals("")){
            float v = Float.parseFloat(discount)*10;
            checkininfo.setDiscount((int) v);
        }else {
            checkininfo.setDiscount(10);
        }
        //获取房间类型id
        String typeId = roomService.getRoomTypeByRoomId(roomId);
        checkininfo.setTypeid(typeId);
        System.out.println(checkininfo);
        //插入记录
        int i = orderService.addOrderInfo(checkininfo);
        //修改对应的房间状态为已住
        roomService.updateRoomUsed(roomId);
        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(i));
    }


    /**
     *计算订单金额的servlet
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public void calculateMoney(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        String arrireDate = request.getParameter("arrireDate");
        String leaveDate = request.getParameter("leaveDate");
        int price = Integer.parseInt(request.getParameter("price"));       //客房价格
        String discountStr = request.getParameter("discount");     //折扣
        String addBed = request.getParameter("addBed");         //是否加床
        String addBedPriceStr = request.getParameter("addBedPrice");   //加床价格
        int deposit = Integer.parseInt(request.getParameter("deposit"));           //押金
        int flag=0;
        float discount=1;
        int addBedPrice=0;
        if(discountStr!=null&&!discountStr.equals("")){
           discount=Float.parseFloat(discountStr);
        }
        if(addBedPriceStr!=null&&!addBedPriceStr.equals("")){
            addBedPrice=Integer.parseInt(addBedPriceStr);
        }


        //计算日期相差多少天
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date smdate=sdf.parse(arrireDate);
        Date bdate=sdf.parse(leaveDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        int days=Integer.parseInt(String.valueOf(between_days));
//        System.out.println(days);

        //计算预订金额
        float orderPrice=0;

        if(addBed.equals("true")){
            orderPrice=(price+addBedPrice)*days*discount+deposit;
            flag=1;
        }else{
            orderPrice=price*days*discount+deposit;
            flag=1;
        }
        if(discount>1){
            flag=-1;
        }
        String result="{\"flag\":\""+String.valueOf(flag)+"\",\"orderPrice\":\""+String.valueOf(orderPrice)+"\"}";
        PrintWriter out = response.getWriter();
        JSONObject jsonObject=new JSONObject(result);
//        com.yzy.Entity.data data=new data(String.valueOf(flag),String.valueOf(orderPrice));
//        Gson gson=new Gson();
        out.print(jsonObject);
//        System.out.println(jsonObject);
    }

    /**
     * 此方法获取所有的订单信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Checkininfo> allOrder = orderService.findAllOrderUsable();
        PrintWriter out = response.getWriter();
        Gson gson=new Gson();
        out.print(gson.toJson(allOrder));
    }

    /**
     * 此方法通过订单id获取订单信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectOrderById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        PrintWriter out = response.getWriter();
        Gson gson=new Gson();
        if(orderId!=null&&!orderId.equals("")){
            List<Checkininfo> byId = orderService.findOrderUsableById(orderId);
            out.print(gson.toJson(byId));
        }else{
            List<Checkininfo> allOrder = orderService.findAllOrderUsable();
            out.print(gson.toJson(allOrder));
        }
    }

    /**
     * 此方法跳转至选择订单页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void beforeCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/orderPages/selectOrder.jsp").forward(request,response);
    }

    /**
     * 此方法跳转至退房页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void checkOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        List<Checkininfo> byId = orderService.findOrderUsableById(orderId);
        Checkininfo checkininfo = byId.get(0);
//        System.out.println(checkininfo);
        request.setAttribute("checkininfo",checkininfo);
        request.getRequestDispatcher("/pages/orderPages/checkOut.jsp").forward(request,response);
    }

    /**
     * 此方法计算该返还的押金
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void calculateReturnDeposit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String extraMoneyStr = request.getParameter("extraMoney");
        String depositStr = request.getParameter("deposit");
        float extraMoney=0;
        float deposit=0;
        if (extraMoneyStr!=null&&!extraMoneyStr.equals("")){
            extraMoney=Float.parseFloat(extraMoneyStr);
        }

        if (depositStr!=null&&!depositStr.equals("")){
            deposit=Float.parseFloat(depositStr);
        }

        float result=deposit-extraMoney;
        PrintWriter out = response.getWriter();
        Gson gson=new Gson();
        out.print(gson.toJson(result));
    }

    /**
     * 此方法用于插入账单信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void insertBillInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String returnDeposit = request.getParameter("returnDeposit");
        String orderId = request.getParameter("orderId");
        String remark = request.getParameter("remark");
        Billinfo billinfo=new Billinfo();
        List<Checkininfo> byId = orderService.findOrderUsableById(orderId);
        Checkininfo checkininfo = byId.get(0);
        billinfo.setCheckid(orderId);

        //用当前系统时间生成退房时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String str = df.format(new Date());//获取String类型的时间
        billinfo.setCostdate(str);

        //计算总共消费金额
        String checkmoneyStr = checkininfo.getCheckmoney();//获取预收款
        float checkMoney = Float.parseFloat(checkmoneyStr);
        float returnDepositFloat=Float.parseFloat(returnDeposit);
        float costMoney=checkMoney-returnDepositFloat;
        billinfo.setCostmoney(String.valueOf(costMoney));

        billinfo.setReturndeposit(returnDeposit);
        billinfo.setRemark(remark);

        int i = billService.addBillInfo(billinfo);
        //将房间状态修改为：未住
        //将订单信息状态修改为：已退押金
        if(i > 0){
            roomService.updateRoomUsable(checkininfo.getRoomid());
            orderService.updateOrderUsed(orderId);
        }
        Gson gson=new Gson();
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(i));

    }

    /**
     * 此方法跳转至订单查询页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void beforeSelect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/orderPages/orderTable.jsp").forward(request,response);
    }

    /**
     * 此方法获取所有订单信息至jsp并且实现删除查询功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public  void findAllOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int page = Integer.parseInt(request.getParameter("page")); // 当前页码
        int limit = Integer.parseInt(request.getParameter("limit")); // 每页的数据量
        int make = Integer.parseInt(request.getParameter("make")); //状态标志

        List<Checkininfo> result=new ArrayList<>();  //存放结果的数组
        List<Checkininfo> allResult=orderService.findAllOrder(-1,-1,"noUsed"); //所有订单的信息

        String code="0"; //数据状态字段
        String msg = "数据查询正常"; //状态信息
        String count; //数据总数

        //单个全局属性
        String orderId; //预定单号
        String select; //查询值

        //获取对应状态属性
        if (make == 1 || make == 2) { //1和2这部分完全是相同的  1：通过id查订单，2：通过预订人姓名查订单
            select = request.getParameter("select");
            if(select!=null&&!select.equals("")) {
                if (make == 1) {
                    result = orderService.findOrderById(select);
                } else {
                    result = orderService.findOrderByName(select);
                }
            }else{
                result = orderService.findAllOrder(page, limit,"used");
            }
            count = String.valueOf(result.size());
            msg = "结果如下";
        } else if (make == 4) {                     //4：删除订单
            orderId = request.getParameter("orderId");
            if (orderService.deleteOrderById(orderId) == -1) {
                msg = "删除失败";
                code = "-1";
            }
            roomService.updateRoomUsable(orderId);    //将房间状态置为未住
            count = String.valueOf(orderService.findAllOrder(-1,-1,"noUsed").size());
        } else {
            result = orderService.findAllOrder(page, limit,"used");
            count = String.valueOf(orderService.findAllOrder(-1,-1,"noUsed").size());
        }

        PojotoGson pojotoGson=new PojotoGson(code,msg,count,result);
        Gson gson=new Gson();
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(pojotoGson));


    }


    /**
     * 此方法用于查询订单信息返回至订单修改页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void beforeUpdateOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        System.out.println(orderId);
        List<Checkininfo> orderById = orderService.findOrderById(orderId);
        Checkininfo checkininfo = orderById.get(0);
        PrintWriter out = response.getWriter();
        Gson gson=new Gson();
        out.print(gson.toJson(checkininfo));
    }


    /**
     * 此方法更新订单的信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updateOrderInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldRoomId = request.getParameter("oldRoomId");   //取值，后面将房间状态赋值为未住
//        System.out.println("old:"+oldRoomId);


        //取值操作
        String checkId=request.getParameter("orderId");
        String checkName = request.getParameter("orderName");
        String checkPhone = request.getParameter("orderPhone");
        String checkIDcard = request.getParameter("orderIDcard");
        String roomId = request.getParameter("roomId");
        String arrireDate=request.getParameter("arrireDate");
        String leaveDate = request.getParameter("leaveDate");
        String checkState = request.getParameter("orderState"); //状态
        String checkNum = request.getParameter("checkNum");//入住人数
        String price = request.getParameter("price");   //客房价格
        String checkPrice = request.getParameter("checkPrice"); //入住天数*客房价格
        String discount = request.getParameter("discount");
        String discountReason = request.getParameter("discountReason");
        String addBed = request.getParameter("addBed");
        String addBedPrice = request.getParameter("addBedPrice");
        String orderMoney = request.getParameter("orderMoney");
        String deposit = request.getParameter("deposit");
        String operatorId = request.getParameter("operatorId");

        Checkininfo checkininfo=new Checkininfo();
        checkininfo.setCheckid(checkId);
        checkininfo.setCheckname(checkName);
        checkininfo.setCheckphone(checkPhone);
        checkininfo.setCheckidcard(checkIDcard);
        checkininfo.setRoomid(roomId);
        checkininfo.setArriretime(arrireDate);
        checkininfo.setLeavetime(leaveDate);
        checkininfo.setCheckstate(checkState);
        checkininfo.setChecknum(Integer.parseInt(checkNum));
        checkininfo.setPrice(price);
        checkininfo.setCheckprice(checkPrice);
        checkininfo.setDiscountreason(discountReason);
        checkininfo.setAddbed(addBed);
        checkininfo.setAddbedprice(addBedPrice);
        checkininfo.setCheckmoney(orderMoney); //预订给的钱
        checkininfo.setDeposit(deposit);
        checkininfo.setOperatorid(operatorId);
        //处理折扣置空
        if(discount!=null&&!discount.equals("")){
            float v = Float.parseFloat(discount)*10;
            checkininfo.setDiscount((int) v);
        }else {
            checkininfo.setDiscount(10);
        }
        //获取房间类型id
        String typeId = roomService.getRoomTypeByRoomId(roomId);
        checkininfo.setTypeid(typeId);
//        System.out.println(checkininfo);

        //更新订单记录
        int i = orderService.updateOrderById(checkininfo);
//        System.out.println("影响的行号："+i);
        if (i>0){
            if(checkState.equals("未退押金")) {
                roomService.updateRoomUsable(oldRoomId);    //修改旧房间为：未住
                roomService.updateRoomUsed(roomId);        //修改新房间为：已住
            }
        }
        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(i));

    }

}

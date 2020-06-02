package com.yzy.Controller;

import com.google.gson.Gson;
import com.yzy.Entity.Billinfo;
import com.yzy.Entity.Checkininfo;
import com.yzy.Global.PojotoGson;
import com.yzy.Service.billService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller("billController")
public class billController {

    @Resource(name = "billService")
    private billService billService;

    /**
     * 此方法跳转至账单查询页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void beforeSelect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/billPages/billTable.jsp").forward(request,response);
    }

    /**
     * 此方法获取所有账单信息至jsp并且实现删除查询功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAllBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = Integer.parseInt(request.getParameter("page")); // 当前页码
        int limit = Integer.parseInt(request.getParameter("limit")); // 每页的数据量
        int make = Integer.parseInt(request.getParameter("make")); //状态标志

        List<Billinfo> result=new ArrayList<>();  //存放结果的数组

        String code="0"; //数据状态字段
        String msg = "数据查询正常"; //状态信息
        String count; //数据总数

        //单个全局属性
        String billId; //账单单号
        String select; //查询值

        //获取对应状态属性
        if (make == 1 || make == 2) { //1和2这部分完全是相同的  1：通过账单id查账单，2：通过订单id查账单
            select = request.getParameter("select");
            if(select!=null&&!select.equals("")) {
                if (make == 1) {
                    result = billService.findBillById(Integer.parseInt(select));
                } else {
                    result = billService.findBillByOrderId(select);
                }
            }else{
                result = billService.findAll(page, limit,"used");
            }
            count = String.valueOf(result.size());
            msg = "结果如下";
        } else if (make == 4) {                     //4：删除订单
            billId = request.getParameter("billId");
            if (billService.deleteBillById(Integer.parseInt(billId)) == -1) {
                msg = "删除失败";
                code = "-1";
            }
            count = String.valueOf(billService.findAll(-1,-1,"noUsed").size());
        } else {
            result = billService.findAll(page, limit,"used");
            count = String.valueOf(billService.findAll(-1,-1,"noUsed").size());
        }

        PojotoGson pojotoGson=new PojotoGson(code,msg,count,result);
        Gson gson=new Gson();
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(pojotoGson));
    }
}

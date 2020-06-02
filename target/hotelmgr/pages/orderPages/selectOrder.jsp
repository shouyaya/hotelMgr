<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/layui/css/layui.css" media="all">
    <script src="${pageContext.request.contextPath}/js/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/global.js"></script>
    <style>
        .fixDiv {
            position: sticky;
            bottom: 0;
            background-color: white;
            BORDER-BOTTOM: #e1e1e1 1px solid;
            BORDER-TOP: #e1e1e1 1px solid;
            BORDER-RIGHT: #e1e1e1 1px solid;
            BORDER-LEFT: #e1e1e1 1px solid;
            border-radius: 10px
        }
    </style>
</head>

<body>

<div class="fixDiv">
    <label class="layui-form-label" style="width: 100px">根据订单id查找</label>
    <div class="layui-input-inline">
        <input type="text"  id="selectId" class="layui-input" placeholder="请输入订单ID" >
    </div>
    <button class="layui-btn layui-btn-radius layui-btn-primary" id="selectedOrder" type="button">搜索</button>
</div>
<table id="dataTable" class='layui-table'>
    <tr>
        <th>订单ID</th>
        <th>预定人</th>
        <th>房间编号</th>
        <th>预收款</th>
        <th>押金</th>
    </tr>
</table>
<div class="fixDiv">
    <label class="layui-form-label">当前选中：</label>
    <div class="layui-input-inline">
        <input type="text" id="tId" class="layui-input" placeholder="订单ID" readonly>
    </div>
    <div class="layui-input-inline">
        <input type="text" id="tName" class="layui-input" placeholder="预定人" readonly>
    </div>
    <button class="layui-btn layui-btn-radius layui-btn-primary" id="checkedOrder" type="button">结算该订单</button>
</div>

<script>
    //网页加载完毕
    $(document).ready(function() {

        //发出ajax请求，调用后端数据
        $.getJSON('${pageContext.request.contextPath}/order/selectOrder.do', function(data) {

            //遍历响应的json数组

            $.each(data, function(index,el) {
                var tId = el.checkid;
                var tName = el.checkname;
                var html = '';
                html += '<tr onclick="tId.value=\'' + tId + '\',tName.value=\'' + tName + '\'" class="update" >';
                html += '	<td>' + el.checkid + '</td>';
                html += '	<td>' + el.checkname + '</td>';
                html += '	<td>' + el.roomid + '</td>';
                html += '	<td>' + el.price + '</td>';
                html += '	<td>' + el.deposit + '</td>';
                html += '</tr>';

                //追加到表格中
                $('#dataTable').append(html);
                // $('#dataTable').empty();//清空table内容

            });

        });
    });
    $('#selectedOrder').on('click',function () {

        var orderId=$('#selectId').val();
        var params="orderId="+orderId;
        $.getJSON('${pageContext.request.contextPath}/order/selectOrderById.do',params,function(data) {
            //清空table内容
            $('#dataTable tbody .update').empty();//清空table内容

            //遍历响应的json数组

            $.each(data, function(index,el) {
                var tId = el.checkid;
                var tName = el.checkname;
                var html = '';
                html += '<tr onclick="tId.value=\'' + tId + '\',tName.value=\'' + tName + '\'" class="update" >';
                html += '	<td>' + el.checkid + '</td>';
                html += '	<td>' + el.checkname + '</td>';
                html += '	<td>' + el.roomid + '</td>';
                html += '	<td>' + el.price + '</td>';
                html += '	<td>' + el.deposit + '</td>';
                html += '</tr>';

                //追加到表格中
                $('#dataTable').append(html);
                // $('#dataTable').empty();//清空table内容

            });

        });

    });
    $('#checkedOrder').on('click',function () {
        var orderId=$('#tId').val();
        var params="?orderId="+orderId;
        window.location.href="${pageContext.request.contextPath}/order/checkOut.do"+params;
    <%--    $.post('${pageContext.request.contextPath}/order/checkOut.do',params,function () {--%>
    <%--        return false;--%>

    <%--    })--%>
    });
</script>
</body>

</html>
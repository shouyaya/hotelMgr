<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <meta charset="utf-8">
    <title>预定单</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/layui/css/layui.css" media="all">
    <script src="${pageContext.request.contextPath}/js/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/global.js"></script>
    <script src="${pageContext.request.contextPath}/js/getTime.js"></script>
    <script src="${pageContext.request.contextPath}/js/Cookie.js"></script>
</head>

<body>
<fieldset class="layui-elem-field layui-field-title " style="margin-top: 20px;">
    <legend>酒店管理 - 预订单</legend>
</fieldset>

<form class="layui-form">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">客房编号</label>
            <div class="layui-input-block">
                <input type="text" id="roomId" class="layui-input">
            </div>
        </div>
    </div>


    <div class="layui-form-item">

        <div class="layui-inline">
            <label class="layui-form-label">类型编号</label>
            <div class="layui-input-inline">
                <input type="text" id="typeId" lay-verify="number" autocomplete="off" placeholder="类型编号" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">楼层编号</label>
            <div class="layui-input-inline">
                <input type="text" id="floorId" lay-verify="required" autocomplete="off" placeholder="楼层编号" class="layui-input" readonly>
            </div>
        </div>

    </div>

    <div class="layui-form-item">

        <div class="layui-inline">
            <label class="layui-form-label">额定人数</label>
            <div class="layui-input-inline">
                <input type="text" id="ratedNum" lay-verify="number" autocomplete="off" placeholder="额定人数" class="layui-input">
            </div>
        </div>
        <div class="layui-inline" style="display: none">
            <label class="layui-form-label">床数</label>
            <div class="layui-input-inline">
                <input type="text" id="bedNum" lay-verify="number" autocomplete="off" placeholder="床数" class="layui-input">
            </div>
        </div>

    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">客房描述</label>
        <div class="layui-input-block">
            <input type="text" id="roomDescription" placeholder="客房描述" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-inline">
                <input type="text" id="status" autocomplete="off" placeholder="状态" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea id="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="updateRoom">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script>
    layui.use(['form', 'layedit', 'laydate'], function() {
        var form = layui.form,
            layer = layui.layer,
            layedit = layui.layedit,
            laydate = layui.laydate;
        var isAddBed = false;

        var orderId = getCookie("roomId");
        deleteCookie("roomId"); //取到值就麻溜的删
        var queryId = "roomId=" + roomId;



        // 开始赋值
        $.post('${pageContext.request.contextPath}/room/beforeUpdateRoom.do', queryId, function(orderInfo) {
            var obj = JSON.parse(orderInfo);
            $("#roomId").val(roomId);
            $("#typeId").val(obj.typeid);
            $("#floorId").val(obj.floorid);
            $("#ratedNum").val(obj.ratednum);
            $("#bedNum").val(obj.bednum);
            $("#roomDescription").val(obj.roomdescription);
            $("#remark").val(obj.remark);
            $("#status").val(obj.status);

        });


        //监听提交
        form.on('submit(updateOrder)', function(data) {

            //先获取值
            var orderId = $('#orderId').val();
            var orderName = $('#orderName').val();
            var orderPhone = $('#orderPhone').val();
            var orderIDcard = $('#orderIDcard').val();
            var roomId = $('#typeId').val();

            //返回数据类型： yyyy-mm-dd hh:mm:ss
            var orderAllTime = ($('#orderAllTime').val()).split(" | ");
            var arrireDate = orderAllTime[0];
            var leaveDate = orderAllTime[1];

            var orderState = $('#orderState').val();
            var checkNum = $('#checkNum').val();

            // var roomId = $('#roomId').val(); 后台处理 -->直接放一个空类就行了

            var price = $('#price').val();
            var checkPrice = $('#checkPrice').val();
            var discount = $('#discount').val();
            var discountReason = $('#discountReason').val();

            //加床：true 不加：false
            var addBed = isAddBed;

            var addBedPrice = $('#addBedPrice').val();
            var orderMoney = $('#orderMoney').val();
            var deposit =$('#deposit').val();
            var operatorId = getCookie("loginName");
            var remark = $('#remark').val();


            var params = "orderId=" + orderId + "&orderName=" + orderName + "&orderPhone=" + orderPhone +
                "&orderIDcard=" + orderIDcard + "&roomId=" + roomId + "&arrireDate=" + arrireDate +
                "&leaveDate=" + leaveDate + "&orderState=" + orderState + "&checkNum=" + checkNum +
                "&price=" + price + "&checkPrice=" + checkPrice + "&discount=" + discount +
                "&discountReason=" + discountReason + "&addBed=" + addBed + "&addBedPrice=" + addBedPrice +
                "&orderMoney=" + orderMoney + "&deposit=" + deposit + "&operatorId=" + operatorId + "&remark=" + remark + "&make=1"+"&oldRoomId="+oldRoomId;

            $.post('${pageContext.request.contextPath}/order/updateOrderInfo.do', params, function(data) {
                if(data === '1') {
                    layer.alert('预订单修改成功！', {
                        title: '新增成功',
                        icon: 6,
                        shade: 0.6,
                        anim: 3,
                        offset: '220px'
                    });
                } else if(data === '0') {
                    layer.alert('存在相同字段！', {
                        title: '新增失败',
                        icon: 5,
                        shade: 0.6,
                        anim: 6,
                        offset: '220px'
                    });
                } else {
                    layer.alert('预订单修改失败！', {
                        title: '新增失败',
                        icon: 2,
                        shade: 0.6,
                        anim: 6,
                        offset: '220px'
                    });
                }
            });
            return false;
        });
    });
</script>
</body>

</html>
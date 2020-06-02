<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="utf-8">
    <title>房间信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/layui/css/layui.css" media="all">
    <script src="${pageContext.request.contextPath}/js/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/global.js"></script>
    <script src="${pageContext.request.contextPath}/js/getTime.js"></script>
    <script src="${pageContext.request.contextPath}/js/Cookie.js"></script>
</head>

<body>
<fieldset class="layui-elem-field layui-field-title " style="margin-top: 20px;">
    <legend>宾馆管理 - 房间信息</legend>
</fieldset>

<form class="layui-form">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">客房编号</label>
            <div class="layui-input-block">
                <input type="text" id="roomId" lay-verify="number" class="layui-input">
            </div>
        </div>
    </div>


    <div class="layui-form-item">

        <div class="layui-inline">
            <label class="layui-form-label">类型编号</label>
            <div class="layui-input-inline">
<%--                <input type="text" id="typeId" lay-verify="number" autocomplete="off" placeholder="类型编号" class="layui-input">--%>
                <select name="city" class="layui-input-inline" id="typeId">
                    <c:forEach items="${roomTypes}" var="roomType">
                        <option value="${roomType.typeid}">${roomType.typeid}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">楼层编号</label>
            <div class="layui-input-inline">
<%--                <input type="text" id="floorId" lay-verify="required" autocomplete="off" placeholder="楼层编号" class="layui-input">--%>
                <select name="city" class="layui-input-inline" id="floorId">
                    <c:forEach items="${floors}" var="floor">
                        <option value="${floor.floorid}">${floor.floorid}</option>
                    </c:forEach>
                </select>
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
        <div class="layui-inline">
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
<%--            <label class="layui-form-label">状态</label>--%>
<%--            <div class="layui-input-inline">--%>
<%--                <input type="text" id="status" autocomplete="off" placeholder="状态" class="layui-input">--%>
<%--            </div>--%>
            <label class="layui-form-label">状态</label>
            <div class="layui-input-inline">
                <select name="city" class="layui-input-inline" id="status">
                    <option value="未住">未住</option>
                    <option value="已住">已住</option>
                </select>
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

        var roomId = getCookie("roomId");
        deleteCookie("roomId"); //取到值就麻溜的删
        var queryId = "roomId=" + roomId;



        // 开始赋值
        $.post('${pageContext.request.contextPath}/room/beforeUpdateRoom.do', queryId, function(roomInfo) {
            var obj = JSON.parse(roomInfo);
            $("#roomId").val(roomId);
            $("#typeId").val(obj.typeid);
            $("#floorId").val(obj.floorid);
            $("#ratedNum").val(obj.ratednum);
            $("#bedNum").val(obj.bednum);
            $("#roomDescription").val(obj.roomdescription);
            $("#remark").val(obj.remark);
            $("#status").val(obj.status);
            form.render("select"); //重新渲染select

        });


        //监听提交
        form.on('submit(updateRoom)', function(data) {

            //先获取值
            var roomId=$("#roomId").val();
            var typeId=$("#typeId").val();
            var floorId=$("#floorId").val();
            var ratedNum=$("#ratedNum").val();
            var bedNum=$("#bedNum").val();
            var roomDescription=$("#roomDescription").val();
            var remark=$("#remark").val();
            var status=$("#status").val();


            var params = "roomId=" + roomId + "&typeId=" + typeId + "&floorId=" + floorId +
                "&ratedNum=" + ratedNum + "&bedNum=" + bedNum + "&roomDescription=" + roomDescription +
                "&remark=" + remark + "&status=" + status;

            $.post('${pageContext.request.contextPath}/room/updateRoomInfo.do', params, function(data) {
                if(data === '1') {
                    layer.alert('房间信息修改成功！', {
                        title: '修改成功',
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
                    layer.alert('房间信息修改失败！', {
                        title: '修改失败',
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
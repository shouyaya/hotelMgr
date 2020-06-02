<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <meta charset="utf-8">
    <title>添加房间类型</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/layui/css/layui.css" media="all">
    <script src="${pageContext.request.contextPath}/js/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/global.js"></script>
    <script src="${pageContext.request.contextPath}/js/getTime.js"></script>
    <script src="${pageContext.request.contextPath}/js/Cookie.js"></script>
</head>

<body>
<fieldset class="layui-elem-field layui-field-title " style="margin-top: 20px;">
    <legend>宾馆管理 - 添加房间类型</legend>
</fieldset>

<form class="layui-form">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">类型编号</label>
            <div class="layui-input-block">
                <input type="text" id="typeId" lay-verify="number" placeholder="请输入类型编号" class="layui-input">
            </div>
        </div>
    </div>


    <div class="layui-form-item">

        <div class="layui-inline">
            <label class="layui-form-label">类型名称</label>
            <div class="layui-input-inline">
                <input type="text" id="typeName" lay-verify="required" autocomplete="off" placeholder="请输入类型名称" class="layui-input">
            </div>
        </div>

    </div>

    <div class="layui-form-item">

        <div class="layui-inline">
            <label class="layui-form-label">价格</label>
            <div class="layui-input-inline">
                <input type="text" id="price" lay-verify="number" autocomplete="off" placeholder="请输入价格" class="layui-input">
            </div>
        </div>

    </div>


    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="addRoomType">立即提交</button>
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

        //监听提交
        form.on('submit(addRoomType)', function(data) {

            //先获取值
            var typeId=$("#typeId").val();
            var typeName=$("#typeName").val();
            var price=$("#price").val();

            var params = "typeId=" + typeId + "&typeName=" + typeName + "&price=" + price;

            $.post('${pageContext.request.contextPath}/room/addRoomType.do', params, function(data) {
                if(data === '1') {
                    layer.alert('房间类型添加成功！', {
                        title: '添加成功',
                        icon: 6,
                        shade: 0.6,
                        anim: 3,
                        offset: '220px'
                    });
                } else if(data === '0') {
                    layer.alert('存在相同字段！', {
                        title: '添加失败',
                        icon: 5,
                        shade: 0.6,
                        anim: 6,
                        offset: '220px'
                    });
                } else {
                    layer.alert('房间类型添加失败！', {
                        title: '添加失败',
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
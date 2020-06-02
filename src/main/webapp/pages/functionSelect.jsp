<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>宾馆管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body style="padding: 0px;margin: 0px">
<table class="layui-table" width ="100%" height="1000px" border="0"cellspacing="0" style="margin:0px">
<tr height="60px">
    <td style="padding: 0px">
        <div class="layui-header" style="height: 100%">
            <!-- 头部区域（可配合layui已有的水平导航） -->
            <ul class="layui-nav" lay-filter="" >
                <li class="layui-nav-item"><a href="">宾馆管理系统</a></li>
                <li class="layui-nav-item layui-this"><a href="${pageContext.request.contextPath}/order/beforeAddOrder.do" target="main">预订</a></li>
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/order/beforeCheck.do" target="main">退房</a></li>
                <li class="layui-nav-item">
                    <a href="javascript:;">查询</a>
                    <dl class="layui-nav-child"> <!-- 二级菜单 -->
                        <dd><a href="${pageContext.request.contextPath}/order/beforeSelect.do" target="main">订单查询</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/bill/beforeSelect.do" target="main">账单查询</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/room/beforeSelect.do" target="main">房间查询</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">添加</a>
                    <dl class="layui-nav-child"> <!-- 二级菜单 -->
                        <dd><a href="${pageContext.request.contextPath}/room/beforeAddRoomType.do" target="main">添加房间类型</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/room/beforeAddRoom.do" target="main">添加房间</a></dd>
                </dl></li>
            </ul>
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                        ${LoginName}
                    </a>
                </li>
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/login/logout.do">注销</a></li>
            </ul>
        </div>
    </td>
</tr>
<tr height="90%">
    <td style="padding: 0px">
        <!—加左导航的内容页 -->
        <iframe name="main" src="${pageContext.request.contextPath}/order/beforeAddOrder.do" width="100%" height="100%">
        </iframe>
    </td>
</tr>
<tr>
    <td style="padding: 0px">
        <div class="layui-footer">
            <!-- 底部固定区域 -->
            © yzy.com - 底部固定区域
        </div>
    </td>
</tr>
</table>


<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    //注意：导航 依赖 element 模块，否则无法进行功能性操作
    layui.use('element', function(){
        var element = layui.element;

        //…
    });
</script>
</body>

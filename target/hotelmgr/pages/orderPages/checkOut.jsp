<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
    <meta charset="utf-8">
    <title>退房单</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/layui/css/layui.css" media="all">
    <script src="${pageContext.request.contextPath}/js/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/getTime.js"></script>
    <script src="${pageContext.request.contextPath}/js/Cookie.js"></script>
</head>

<body>
<fieldset class="layui-elem-field layui-field-title " style="margin-top: 20px;">
    <legend>宾馆管理 - 退房单</legend>
</fieldset>

<form class="layui-form">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">预定单号</label>
            <div class="layui-input-block">
                <input type="text" id="orderId" class="layui-input" value="${checkininfo.checkid}" readonly>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">预定人</label>
            <div class="layui-input-inline">
                <input type="text" id="orderName" lay-verify="required" autocomplete="off" placeholder="预定人姓名" class="layui-input" value="${checkininfo.checkname}"  readonly>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">预定电话</label>
            <div class="layui-input-inline">
                <input type="tel" id="orderPhone" lay-verify="phone" autocomplete="off" placeholder="预定人电话" class="layui-input" value="${checkininfo.checkphone}" readonly>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">身份证</label>
        <div class="layui-input-block">
            <input type="text" id="orderIDcard" lay-verify="required|identity" placeholder="公民身份证号" autocomplete="off" class="layui-input" value="${checkininfo.checkidcard}" readonly>
        </div>
    </div>

    <div class="layui-form-item">

        <div class="layui-inline">
            <label class="layui-form-label">入住时长</label>
            <div class="layui-input-inline" style="width: 300px">
                <input type="text" class="layui-input"  lay-verify="required" id="orderAllTime" placeholder="抵店时间 - 离店时间"  value="${checkininfo.arriretime}-${checkininfo.leavetime}" readonly>
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">入住人数</label>
            <div class="layui-input-inline">
                <input type="text" id="checkNum" lay-verify="number" autocomplete="off" placeholder="实际入住人数" class="layui-input"  value="${checkininfo.checknum}" readonly>
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">房间编号</label>
            <div class="layui-input-inline">
                <input type="text" id="typeId" lay-verify="required" autocomplete="off" placeholder="房间编号" class="layui-input"  value="${checkininfo.roomid}" readonly>
            </div>
        </div>

    </div>

    <div class="layui-form-item">

        <div class="layui-inline">
            <label class="layui-form-label">客房价格</label>
            <div class="layui-input-inline">
                <input type="text" id="price" lay-verify="number" autocomplete="off" placeholder="￥" class="layui-input" value="${checkininfo.price}" readonly>
            </div>
        </div>
        <div class="layui-inline" style="display: none">
            <label class="layui-form-label">入住价格</label>
            <div class="layui-input-inline">
                <input type="text" id="checkPrice" lay-verify="number" autocomplete="off" placeholder="￥" class="layui-input">
            </div>
        </div>

    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">折扣</label>
            <div class="layui-input-inline">
                <input type="text" id="discount" lay-verify="number" autocomplete="off" placeholder="折扣请输入,无折扣置空" class="layui-input" value="${checkininfo.discount}" readonly>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">折扣原因</label>
            <div class="layui-input-inline">
                <input type="text" id="discountReason" autocomplete="off" placeholder="无" class="layui-input" value="${checkininfo.discountreason}" readonly>
            </div>
        </div>

    </div>

    <div class="layui-form-item">
        <c:if test="${checkininfo.addbed=='true'}">
            <div class="layui-inline">
                <label class="layui-form-label">是否加床</label>
                <div class="layui-input-inline">
                    <input type="radio"  name="addBed" value="Y" title="是" lay-filter="addBedYes" checked="" readonly>
                    <input type="radio"  name="addBed" value="N" title="否" lay-filter="addBedNo" readonly>
                </div>
            </div>
            <div class="layui-inline">
                <div id="addBed" class="layui-inline layui-show">
                    <label class="layui-form-label">加床价格</label>
                    <div class="layui-input-inline">
                        <input type="text" id="addBedPrice" lay-verify="number" autocomplete="off" placeholder="￥" class="layui-input" value="${checkininfo.addbedprice}" readonly>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${checkininfo.addbed=='false'}">
            <div class="layui-inline">
                <label class="layui-form-label">是否加床</label>
                <div class="layui-input-inline">
                    <input type="radio"  name="addBed" value="Y" title="是" lay-filter="addBedYes" readonly>
                    <input type="radio"  name="addBed" value="N" title="否" lay-filter="addBedNo" checked="" readonly>
                </div>
            </div>
            <div class="layui-inline">
                <div id="addBed" class="layui-inline layui-hide">
                    <label class="layui-form-label">加床价格</label>
                    <div class="layui-input-inline">
                        <input type="text" id="addBedPrice" lay-verify="number" autocomplete="off" placeholder="￥" class="layui-input" value="${checkininfo.addbedprice}" readonly>
                    </div>
                </div>
            </div>
        </c:if>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">额外费用</label>
            <div class="layui-input-inline" style="width: 300px">
                <input type="text" id="extraMoney"  autocomplete="off" placeholder="请输入额外费用，如无请置空" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">预收款</label>
            <div class="layui-input-inline">
                <input type="text" id="orderMoney" lay-verify="required|number" autocomplete="off" placeholder="￥" class="layui-input" value="${checkininfo.checkmoney}" readonly>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">押金</label>
            <div class="layui-input-inline">
                <input type="text" id="deposit" lay-verify="required|number" autocomplete="off" placeholder="￥" class="layui-input" value="${checkininfo.deposit}" readonly>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">单据状态</label>
            <div class="layui-input-inline">
                <input type="text" id="orderState" value="已退押金"  autocomplete="off" placeholder="￥" class="layui-input" value="${checkininfo.checkstate}" readonly>
            </div>
        </div>

    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">该退押金</label>
            <div class="layui-input-inline">
                <input type="text" id="returnDeposit" lay-verify="required|number" autocomplete="off" placeholder="请点击计算该退还押金" class="layui-input">
            </div>
            <button class="layui-btn layui-btn-radius layui-btn-primary" id="calculateReturnDeposit" type="button">计算该退还押金</button>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea id="remark" placeholder="无" class="layui-textarea" value="${checkininfo.remark}" readonly></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="insertBill">立即提交</button>
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

        $('#calculateReturnDeposit').on('click',function () {
            var extraMoney=$('#extraMoney').val();
            var deposit=$('#deposit').val();
            var params='extraMoney='+extraMoney+'&deposit='+deposit;
            $.getJSON('${pageContext.request.contextPath}/order/calculateReturnDeposit.do', params, function(data) {
                $('#returnDeposit').val(data);
            })
        });

        form.on('submit(insertBill)', function(data) {
            var extraMoney=$('#extraMoney').val();
            var deposit=$('#deposit').val();
            var returnDeposit=$('#returnDeposit').val();
            var orderId=$('#orderId').val();
            var remark=$('#remark').val();


            if(returnDeposit===null||returnDeposit===""){
                layer.alert('请先计算该退押金！', {
                    title: '请先计算该退押金',
                    icon: 5,
                    shade: 0.6,
                    anim: 6,
                    offset: '220px'
                });
            }
            else{
                var params='extraMoney='+extraMoney+'&deposit='+deposit+'&returnDeposit='+returnDeposit+'&orderId='+orderId+'&remark='+remark;
                $.post('${pageContext.request.contextPath}/order/insertBillInfo.do', params, function(data) {

                    if(data>'0'){
                        layer.alert('退房成功！', {
                            title: '退房成功',
                            icon: 6,
                            shade: 0.6,
                            anim: 3,
                            offset: '220px'
                        });
                    }
                    else{
                        layer.alert('退房失败，请重新操作！', {
                            title: '退房失败，请重新操作',
                            icon: 5,
                            shade: 0.6,
                            anim: 6,
                            offset: '220px'
                        });
                    }
                });
            }
            return false;
        });


    });
</script>
</body>

</html>
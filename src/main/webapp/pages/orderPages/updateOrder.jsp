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
					<label class="layui-form-label">预定单号</label>
					<div class="layui-input-block">
						<input type="text" id="orderId" class="layui-input" readonly>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">预定人</label>
					<div class="layui-input-inline">
						<input type="text" id="orderName" lay-verify="required" autocomplete="off" placeholder="预定人姓名" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">预定电话</label>
					<div class="layui-input-inline">
						<input type="tel" id="orderPhone" lay-verify="phone" autocomplete="off" placeholder="预定人电话" class="layui-input">
					</div>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">身份证</label>
				<div class="layui-input-block">
					<input type="text" id="orderIDcard" lay-verify="required|identity" placeholder="公民身份证号" autocomplete="off" class="layui-input">
				</div>
			</div>

			<div class="layui-form-item">

				<div class="layui-inline">
					<label class="layui-form-label">入住时长</label>
					<div class="layui-input-inline" style="width: 300px">
						<input type="text" class="layui-input" lay-verify="required" id="orderAllTime" placeholder="抵店时间 - 离店时间" readonly>
					</div>
				</div>

				<div class="layui-inline">
					<label class="layui-form-label">入住人数</label>
					<div class="layui-input-inline">
						<input type="text" id="checkNum" lay-verify="number" autocomplete="off" placeholder="实际入住人数" class="layui-input">
					</div>
				</div>

				<div class="layui-inline">
					<label class="layui-form-label">房间类型</label>
					<div class="layui-input-inline">
						<input type="text" id="typeId" lay-verify="required" autocomplete="off" placeholder="房间类型" class="layui-input" readonly>
					</div>
					<button type="button" class="layui-btn layui-btn-primary" id="buttonTypeId"><i class="layui-icon">&#xe654;</i> 选择</button>
				</div>

			</div>

			<div class="layui-form-item">

				<div class="layui-inline">
					<label class="layui-form-label">客房价格</label>
					<div class="layui-input-inline">
						<input type="text" id="price" lay-verify="number" autocomplete="off" placeholder="￥" class="layui-input">
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
						<input type="text" id="discount" lay-verify="number" autocomplete="off" placeholder="折扣请输入，无折扣置空" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">折扣原因</label>
					<div class="layui-input-inline">
						<input type="text" id="discountReason" autocomplete="off" placeholder="请输入折扣原因" class="layui-input">
					</div>
				</div>

			</div>

			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">是否加床</label>
					<div class="layui-input-inline">
						<input type="radio" name="addBed" value="Y" title="是" lay-filter="addBedYes">
						<input type="radio" name="addBed" value="N" title="否" lay-filter="addBedNo" checked>
					</div>
				</div>
				<div class="layui-inline">
					<div id="addBed" class="layui-inline layui-hide">
						<label class="layui-form-label">加床价格</label>
						<div class="layui-input-inline">
							<input type="text" id="addBedPrice" lay-verify="number" autocomplete="off" placeholder="￥" class="layui-input">
						</div>
					</div>
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">预收款</label>
					<div class="layui-input-inline">
						<input type="text" id="orderMoney" lay-verify="required|number" autocomplete="off" placeholder="￥" class="layui-input" readonly>
					</div>
					<button class="layui-btn layui-btn-radius layui-btn-primary" id="calculateMoney" type="button">计算预收款</button>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">押金</label>
					<div class="layui-input-inline">
						<input type="text" id="deposit" lay-verify="required|number" autocomplete="off" placeholder="￥" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">单据状态</label>
					<div class="layui-input-inline">
						<select name="city" class="layui-input-inline" id="orderState">
							<option value="未退押金">未退押金</option>
							<option value="已退押金">已退押金</option>
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
					<button class="layui-btn" lay-submit lay-filter="updateOrder">立即提交</button>
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

				var orderId = getCookie("orderId");
				deleteCookie("orderId"); //取到值就麻溜的删
				var queryId = "orderId=" + orderId;
				var oldRoomId="";


				// 开始赋值
				$.post('${pageContext.request.contextPath}/order/beforeUpdateOrder.do', queryId, function(orderInfo) {
					var obj = JSON.parse(orderInfo);
					var discount =obj.discount/10;//将折扣转化为小数
					oldRoomId=obj.roomid;
					$("#orderId").val(orderId);
					$("#orderName").val(obj.checkname);
					$("#orderPhone").val(obj.checkphone);
					$("#orderIDcard").val(obj.checkidcard);
					$("#typeId").val(obj.roomid);
					$("#orderAllTime").val(obj.arriretime + " | " + obj.leavetime);

					$("#orderState").val(obj.checkstate); //<--需要处理
					form.render("select"); //重新渲染select

					$("#checkNum").val(obj.checknum);
					$("#price").val(obj.price);
					$("#checkPrice").val(obj.checkprice);
					$("#discount").val(discount);
					$("#discountReason").val(obj.discountreason);
					$("#deposit").val(obj.deposit);

					isAddBed = obj.addbed; //<--需要处理，默认选中的是否
					if(isAddBed === "false") {
						$('#addBed').removeClass("layui-show");
						$('#addBed').addClass("layui-hide");
					} else {
						$('#addBed').removeClass("layui-hide");
						$('#addBed').addClass("layui-show");
						$("input[name='addBed'][value='Y']").prop("checked", true); //把 是 给主动选上
						form.render('radio'); //重新渲染
					}

					$("#addBedPrice").val(obj.addbedprice);
					$("#orderMoney").val(obj.checkmoney);
					$("#operatorId").val(obj.operatorId);
					$("#remark").val(obj.remark);

				});

				//日期
				laydate.render({
					elem: '#arrireDate'
				});
				laydate.render({
					elem: '#leaveDate'
				});
				laydate.render({
					elem: '#orderAllTime',
					type: 'datetime',
					min: -30,
					range: '|',
					format: 'yyyy-MM-dd',
					calendar: true
				});

				//一个属性的显隐，直接通过修改class实现，使用了layui的class属性
				form.on('radio(addBedYes)', function() {
					$('#addBed').removeClass("layui-hide");
					$('#addBed').addClass("layui-show");
					isAddBed = true;
				});
				form.on('radio(addBedNo)', function() {
					$('#addBed').removeClass("layui-show");
					$('#addBed').addClass("layui-hide");
					isAddBed = false;
				});

				//房间类型的选择
				$('#buttonTypeId').on('click', function() {
					layer.open({
						type: 2,
						title: '请选择房间类型',
						btn: ['确定', '取消'],
						area: ['880px', '440px'],
						fixed: form,
						content: '${pageContext.request.contextPath}/pages/roomPages/selectRoomType.jsp',
						yes: function(index, layero) {
							typeId.value = $(layero).find('iframe')[0].contentWindow.tId.value; //将子窗口中的 tId 抓过来
							price.value = $(layero).find('iframe')[0].contentWindow.tPrice.value;
							layer.close(index); //关闭弹窗
						},
						btn2: function(index) {
							layer.close(index);
						},
						success: function(layero, index) {
							var obj = $(layero).find('iframe')[0].contentWindow;
						}
					});
				});

				//计算要交金额
				$('#calculateMoney').on('click',function () {
					//先获取值
					//返回数据类型： yyyy-mm-dd hh:mm:ss
					var orderAllTime = ($('#orderAllTime').val()).split(" | ");
					var arrireDate = orderAllTime[0];
					var leaveDate = orderAllTime[1];

					var price = $('#price').val();
					var discount = $('#discount').val();
					//加床：true 不加：false
					var addBed = isAddBed;
					var addBedPrice = $('#addBedPrice').val();
					var deposit =$('#deposit').val();

					if(arrireDate===null||arrireDate===""){
						layer.alert('请先设置入住时长！', {
							title: '请先设置入住时长',
							icon: 5,
							shade: 0.6,
							anim: 6,
							offset: '220px'
						});
					}
					else if (price===null||price===""){
						layer.alert('请先选择房间！', {
							title: '请先选择房间',
							icon: 5,
							shade: 0.6,
							anim: 6,
							offset: '220px'
						});
					}
					else if(deposit===null||deposit===""){
						layer.alert('请先设置押金！', {
							title: '请先设置押金',
							icon: 5,
							shade: 0.6,
							anim: 6,
							offset: '220px'
						});
					}
					else{
						var params = "arrireDate=" + arrireDate + "&leaveDate=" + leaveDate +
								"&price=" + price + "&discount=" + discount +
								"&addBed=" + addBed + "&addBedPrice=" + addBedPrice +
								"&deposit=" + deposit;
						$.getJSON('${pageContext.request.contextPath}/order/calculateMoney.do', params, function(data) {
							if(data.flag === '1') {
								$('#orderMoney').val(data.orderPrice);
							} else if(data.flag === '-1') {
								layer.alert('折扣应在0和1之间', {
									title: '折扣应在0和1之间',
									icon: 5,
									shade: 0.6,
									anim: 6,
									offset: '220px'
								});
							} else {
								layer.alert('计算错误，请检查数据', {
									title: '计算错误，请检查数据',
									icon: 5,
									shade: 0.6,
									anim: 6,
									offset: '220px'
								});
							}
						});
					}

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
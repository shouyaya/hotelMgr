<%@ page contentType="text/html;charset=UTF-8" %>
<html>

	<head>
		<meta charset="utf-8">
		<title>宾馆管理系统</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/js/layui/css/layui.css" media="all">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/MAIN/component/font-awesome-4.7.0/css/font-awesome.min.css">
		<script src="${pageContext.request.contextPath}/js/layui/layui.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
		<script src="${pageContext.request.contextPath}/js/Cookie.js"></script>
		<style>
			body {
				margin: 10px;
			}
			
			.layui-elem-field legend {
				font-size: 14px;
			}
			
			.layui-field-title {
				margin: 25px 0 15px;
			}
		</style>
	</head>

	<body>
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
			<legend>
				<div>
					<div class="layui-inline">
						<div class="layui-input-inline">
							<input class="layui-input" id="inputSearch1" placeholder="账单id">
						</div>
						<button class="layui-btn fa fa-search layui-btn-normal" id="searchButton1"> 搜索</button>
					</div>

					<div class="layui-inline">
						<div class="layui-input-inline">
							<input class="layui-input" id="inputSearch2" placeholder="订单id">
						</div>
						<button class="layui-btn fa fa-search layui-btn-normal" id="searchButton2"> 搜索</button>
					</div>

					<div class="layui-inline">
						<button class="layui-btn fa fa-refresh layui-btn-normal" id="refreshButton"> 刷新</button>
					</div>
<%--					<div class="layui-inline">--%>
<%--						<button class="layui-btn fa fa-save layui-btn-normal" id="toXlsButton"> 导出</button>--%>
<%--					</div>--%>
				</div>
			</legend>
		</fieldset>
		<div id="toxlsTable">
			<table id="tableID"></table>
		</div>
		<script type="text/html" id="barAuth">
			<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		</script>
		<script>
			layui.use(['util', 'layer', 'table'], function() {
				$(document).ready(function() {
					var table = layui.table,
						layer = layui.layer,
						util = layui.util;
					var countNum;
					var tableIns = table.render({
						elem: '#tableID',
						id: 'tableID',
						url: '${pageContext.request.contextPath}/room/findAllRoom.do',
						cols: [
							[{
								field: 'roomid',
								title: '房间号',
								width: 180,
								sort: true,
								fixed: true
							}, {
								field: 'typeid',
								title: '房间类型号',
								sort: true,
								width: 180
							}, {
								field: 'floorid',
								title: '楼层号',
								width: 180
							}, {
								field: 'status',
								title: '房间状态',
								width: 200
							}, {
								field: 'bednum',
								title: '床数',
								width: 180
							}, {
								field: 'roomdescription',
								title: '客房描述',
								width: 300
							}, {
								field: 'remark',
								title: '备注',
								width: 180
							}, {
								field: 'right',
								title: '管理',
								align: 'center',
								toolbar: '#barAuth',
								width: 150,
								fixed: 'right'
							}]
						],
						page: true,
						where: {
							make: 0
						},
						done: function(res, curr, count) {
							countNum = count;
						}
					});

					//监听工具条
					table.on('tool', function(obj) {
						var data = obj.data,
							layEvent = obj.event;
						var roomId = data.roomid;
						var roomStatus=data.status;
						// layer.alert(roomId);

						if(layEvent === 'del') {
							layer.confirm('您确定要删除该条数据吗？', {
								offset: '180px',
								btn: ['确定', '取消']
							}, function() {
								if(roomStatus==="已住"){
									layer.alert('无法删除已住的房间！', {
										title: '删除失败',
										icon: 5,
										shade: 0.6,
										anim: 6,
										offset: '220px'
									});
								} else {
									table.reload('tableID', {
										where: {
											make: 4,
											roomId: roomId
										}
									});
									layer.msg('删除结果如下', {
										offset: '250px',
										icon: 1
									});
									tableIns.reload({
										where: {
											make: 0,
											page: 1
										}
									});
								}
							}, function() {
								layer.msg('删除操作已取消', {
									offset: '250px'
								});
							});
						} else if(layEvent === 'edit') {

							// emmm 父子传参只会写儿子传递给父亲的
							// 其实 用jsp那套session啥的传参或许更好
							setCookie("roomId", roomId);

							//编辑
							layer.open({
								title: "提交",
								btn: ['关闭'],
								yes: function(index) {
									tableIns.reload({
										where: {
											make: 0
										}
									});
									layer.close(index); //关闭弹窗
								},
								type: 2,
								area: ['1080px', '520px'],
								fixed: false,
								maxmin: true,
								content: '${pageContext.request.contextPath}/room/updateRoomProm.do',
								cancel: function() {
									tableIns.reload({
										where: {
											make: 0
										}
									});
								}
							});

						}
					});

					//搜索
					$('#searchButton1').click(function() {
						var select = $('#inputSearch1').val();
						tableIns.reload({
							where: {
								make: 1,
								// page: 1,
								select: select
							}
						});
					});

					$('#searchButton2').click(function() {
						var select = $('#inputSearch2').val();
						tableIns.reload({
							where: {
								make: 2,
								// page: 1,
								select: select
							}
						});
					});

					//刷新
					$('#refreshButton').click(function() {
						tableIns.reload({
							where: {
								make: 0
								// page: 1
							}
						});
					});

					//导出
					// $('#toXlsButton').click(function() {
					// 	location.href = baseUrl + '/OrderInfoExcelServlet';
					// 	layer.alert('Excel文件生成完成！', {
					// 		title: '成功',
					// 		icon: 6,
					// 		anim: 1,
					// 		offset: '250px'
					// 	});
					// });

					//回到顶端
					util.fixbar({
						showHeight: 2,
						click: function(type) {
							console.log(type);
						}
					});
				});
			});
		</script>
	</body>

</html>
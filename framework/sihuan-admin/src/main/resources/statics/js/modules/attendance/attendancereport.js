$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'attendance/attendancedaily/report',
        datatype: "json",
        colModel: [
			{ label: 'userId', name: 'userId', index: 'userId', width: 50, key: true, hidden: true },
			{ label: '考勤明细', name: 'status', index: 'status', width: 110, edittype: "button", formatter: cmgStatusFormat},
			{ label: '考勤统计单位名称', name: 'companyName', index: 'company_name', width: 140 },
			{ label: '部门名称', name: 'deptName', index: 'dept_Name', width: 140 },
			{ label: '姓名', name: 'empName', index: 'emp_name', width: 140 },
			{ label: '应出勤天数', name: 'yccSum', index: 'ycc_sum', width: 100 },
			{ label: '实际出勤天数', name: 'sjccSum', index: 'sjcc_sum', width: 100 },
			{ label: '旷工天数', name: 'kgSum', index: 'kg_sum', width: 80 },
			{ label: '缺卡天数', name: 'qkSum', index: 'qk_sum', width: 80 },
            { label: '迟到早退次数', name: 'cdZtSum', index: 'cd_zt_sum', width: 100 },
			{ label: '调休（小时）', name: 'txOurs', index: 'tx_ours', width: 90 },
			{ label: '年假（天）', name: 'njDays', index: 'nj_days', width: 90 },
			{ label: '病假（小时）', name: 'bjOurs', index: 'bj_ours', width: 90 },
			{ label: '病假（天）', name: 'bjDays', index: 'bj_days', width: 90 },
			{ label: '事假（小时）', name: 'shijOurs', index: 'shij_ours', width: 90 },
			{ label: '事假（天）', name: 'shijDays', index: 'shij_days', width: 90 },
			{ label: '婚假（天）', name: 'hjDays', index: 'hj_days', width: 90 },
			{ label: '产前检查假（天）', name: 'cjjDays', index: 'cjj_days', width: 120 },
			{ label: '产假（天）', name: 'cjDays', index: 'cj_days', width: 90 },
			{ label: '节育假（天）', name: 'jyjDays', index: 'jyj_days', width: 90 },
			{ label: '陪产假（天）', name: 'pcjDays', index: 'pcj_days', width: 90 },
			{ label: '丧假（天）', name: 'shangjDays', index: 'shangj_days', width: 90 },
			{ label: '工伤假（天）', name: 'gsjDays', index: 'gsj_days', width: 90 },
			{ label: '请假合计（天）', name: 'sumLeaveDays', index: 'sum_leave_days', width: 110 },
			{ label: '请假合计（小时）', name: 'sumLeaveOurs', index: 'sum_leave_ours', width: 120 },
			{ label: '加班合计（小时）', name: 'jbOurs', index: 'jb_ours', width: 120 },
			{ label: '出差合计（天）', name: 'ccDays', index: 'cc_days', width: 110 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		width: "100%",
		rowList : [10,30,50],
        rownumbers: false,
        rownumWidth: 25,
		autowidth: true,
		shrinkToFit: false,
		autoScroll: true,
        multiselect: false,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
			JQGridResize('jqGrid', 'jqGridPager');
        	//底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "scroll" });
        }
    });

	$("#datetimepicker1").datetimepicker({
		format: "YYYY-MM",
		locale: moment.locale("zh-cn")
	});

	//隐藏的同时绑定数据
	$("#datetimepicker1").datetimepicker().on('dp.hide', function (ev) {
		console.log("val====" + $("#selMonth").val());
		vm.q.selMonth = $("#selMonth").val();
	});
});

function cmgStatusFormat(cellValue, grid, rows, status) {
	var html = '<a class="btn btn-primary btn-sm " onclick="vm.detailView(\'' + rows.userId + '\')">查看</a>';
	return html;
};

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			selMonth: null,
			empName: null,
			companyName: null
		},
		showList: true,
		detailShow: false,
		title: null,
		details: []
	},
	methods: {
		query: function () {
			vm.reload();
		},
		exportExcel: function (event) {
			var url = "/attendance/attendancedaily/export?1=1";
			if (vm.q.selMonth && vm.q.selMonth != null) {
				url += "&selMonth=" + vm.q.selMonth;
			}
			if (vm.q.empName && vm.q.empName != null) {
				url += "&empName=" + vm.q.empName;
			}
			if (vm.q.companyName && vm.q.companyName != null) {
				url += "&companyName=" + vm.q.companyName;
			}
			window.location.href = baseURL + url;
		},
		detailView: function (userId) {
			vm.getInfo(userId);
			console.log("aaaa" + JSON.stringify(vm.details));
			vm.showList = false;
			vm.detailShow = true;
		},
		getInfo: function(userId){
			$.ajaxSettings.async = false;
			console.log("userId====" + userId);
			console.log("selMonth====" + vm.q.selMonth);
			var url = "/attendance/attendancedaily/info/";
			$.get(baseURL + url, {'userId': userId, 'selMonth': vm.q.selMonth}, function(r){
				vm.details = r.details;
			});
			$.ajaxSettings.async = true;
		},
		detailCancle: function () {
			vm.showList = true;
			vm.detailShow = false;
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{'selMonth': vm.q.selMonth, 'empName': vm.q.empName, 'companyName': vm.q.companyName},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
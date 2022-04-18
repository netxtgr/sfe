$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'attendance/attendancedaily/reportdetail',
        datatype: "json",
        colModel: [			
			{ label: '考勤统计单位名称', name: 'companyName', index: 'company_name', width: 140, sortable: false },
			{ label: '部门名称', name: 'deptName', index: 'dept_Name', width: 140, sortable: false },
			{ label: '姓名', name: 'empName', index: 'emp_name', width: 140, sortable: false },
			{ label: '考勤日期', name: 'attDate', index: 'attDate', width: 100, sortable: false },
			{ label: '是否应出勤', name: 'shouldAttendance', index: 'shouldAttendance', width: 100, sortable: false },
			{ label: '上班打卡时间', name: 'onDutyTime', index: 'onDutyTime', width: 80, sortable: false },
			{ label: '下班打卡时间', name: 'ofDutyTime', index: 'ofDutyTime', width: 80, sortable: false },
            { label: '考勤情况', name: 'calcType', index: 'calcType', width: 100, sortable: false },
			{ label: '请假类型', name: 'oaLeaveType', index: 'oaLeaveType', width: 90, sortable: false },
			{ label: '请假时长', name: 'oaLeaveTimes', index: 'oaLeaveTimes', width: 90, sortable: false },
			{ label: '外出时长', name: 'wcTimes', index: 'wcTimes', width: 90, sortable: false },
			{ label: '出差时长', name: 'ccTimes', index: 'ccTimes', width: 90, sortable: false },
			{ label: '加班时长', name: 'jbTimes', index: 'jbTimes', width: 90, sortable: false }
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
        	console.log("12333333333333333");
			JQGridResize('jqGrid', 'jqGridPager');
        	//底部滚动条
        	//$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "scroll" });
        }
    });

	$("#datetimepicker1").datetimepicker({
		format: "YYYY-MM-DD",
		locale: moment.locale("zh-cn")
	});

	//隐藏的同时绑定数据
	$("#datetimepicker1").datetimepicker().on('dp.hide', function (ev) {
		vm.q.startDate = $("#startDate").val();
	});

	$("#datetimepicker2").datetimepicker({
		format: "YYYY-MM-DD",
		locale: moment.locale("zh-cn")
	});

	//隐藏的同时绑定数据
	$("#datetimepicker2").datetimepicker().on('dp.hide', function (ev) {
		vm.q.endDate = $("#endDate").val();
	});

});


var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			startDate: null,
			endDate: null,
			empName: null,
			companyName: null,
			calcType: null
		},
		showList: true,
		title: null,
		attendanceReport: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		exportExcel: function (event) {
			var url = "/attendance/attendancedaily/exportdetail?1=1";
			if (vm.q.startDate && vm.q.startDate != null) {
				url += "&startDate=" + vm.q.startDate;
			}
			if (vm.q.endDate && vm.q.endDate != null) {
				url += "&endDate=" + vm.q.endDate;
			}
			if (vm.q.empName && vm.q.empName != null) {
				url += "&empName=" + vm.q.empName;
			}
			if (vm.q.calcType && vm.q.calcType != null) {
				url += "&calcType=" + vm.q.calcType;
			}
			if (vm.q.companyName && vm.q.companyName != null) {
				url += "&companyName=" + vm.q.companyName;
			}
			window.location.href = baseURL + url;
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{'startDate': vm.q.startDate, 'endDate': vm.q.endDate, 'calcType': vm.q.calcType, 'empName': vm.q.empName, 'companyName': vm.q.companyName},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
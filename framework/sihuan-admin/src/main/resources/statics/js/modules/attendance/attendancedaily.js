$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'attendance/attendancedaily/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '钉钉用户ID', name: 'userId', index: 'user_id', width: 80 },
			{ label: '考勤日期', name: 'attDate', index: 'att_date', width: 80 },
			{ label: '考勤排班', name: 'attClass', index: 'att_class', width: 80 },
			{ label: '应出勤 1：是 0：否', name: 'shouldAttendance', index: 'should_attendance', width: 80 },
			{ label: '上班打卡时间', name: 'onDutyUserCheckTime', index: 'on_duty_user_check_time', width: 80 },
			{ label: '上班打卡结果', name: 'onDutyUserCheckResult', index: 'on_duty_user_check_result', width: 80 },
			{ label: '下班打卡时间', name: 'offDutyUserCheckTime', index: 'off_duty_user_check_time', width: 80 },
			{ label: '下班打卡结果', name: 'offDutyUserCheckResult', index: 'off_duty_user_check_result', width: 80 },
			{ label: '是否旷工 1：是 0：否', name: 'isAbsenteeism', index: 'is_absenteeism', width: 80 },
			{ label: '是否迟到 1：是 0：否', name: 'isLate', index: 'is_late', width: 80 },
			{ label: '是否早退 1：是 0：否', name: 'isLeaveEarly', index: 'is_leave_early', width: 80 },
			{ label: '是否严重迟到 1：是 0：否', name: 'isSeriousLate', index: 'is_serious_late', width: 80 },
			{ label: '考勤结果', name: 'attResult', index: 'att_result', width: 80 },
			{ label: '创建时间', name: 'created', index: 'created', width: 80 },
			{ label: '更新时间', name: 'updated', index: 'updated', width: 80 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
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
        	//隐藏grid底部滚动条
        	//$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		attendanceDaily: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.attendanceDaily = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.attendanceDaily.id == null ? "attendance/attendancedaily/save" : "attendance/attendancedaily/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.attendanceDaily),
                    success: function(r){
                        if(r.code === 0){
                             layer.msg("操作成功", {icon: 1});
                             vm.reload();
                             $('#btnSaveOrUpdate').button('reset');
                             $('#btnSaveOrUpdate').dequeue();
                        }else{
                            layer.alert(r.msg);
                            $('#btnSaveOrUpdate').button('reset');
                            $('#btnSaveOrUpdate').dequeue();
                        }
                    }
                });
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			var lock = false;
            layer.confirm('确定要删除选中的记录？', {
                btn: ['确定','取消'] //按钮
            }, function(){
               if(!lock) {
                    lock = true;
		            $.ajax({
                        type: "POST",
                        url: baseURL + "attendance/attendancedaily/delete",
                        contentType: "application/json",
                        data: JSON.stringify(ids),
                        success: function(r){
                            if(r.code == 0){
                                layer.msg("操作成功", {icon: 1});
                                $("#jqGrid").trigger("reloadGrid");
                            }else{
                                layer.alert(r.msg);
                            }
                        }
				    });
			    }
             }, function(){
             });
		},
		getInfo: function(id){
			$.get(baseURL + "attendance/attendancedaily/info/"+id, function(r){
                vm.attendanceDaily = r.attendanceDaily;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});
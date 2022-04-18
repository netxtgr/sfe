$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sfe/agentmeeting/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true, hidden: true },
			{ label: '代理商编号', name: 'agentNumber', index: 'agent_number', width: 80 }, 			
			{ label: '代理商名称', name: 'agentName', index: 'agent_name', width: 80 }, 			
			{ label: '机构编号', name: 'divisionNumber', index: 'division_number', width: 80 }, 			
			{ label: '机构名称', name: 'divisionName', index: 'division_name', width: 80 }, 			
			{ label: '会议名称', name: 'meetingName', index: 'meeting_name', width: 80 }, 			
			{ label: '会议内容', name: 'topic', index: 'topic', width: 80 }, 			
			{ label: '会议人数', name: 'number', index: 'number', width: 80 }, 			
			{ label: '会议时间', name: 'meetingTime', index: 'meeting_time', width: 80 }, 			
			{ label: '会议总结', name: 'summary', index: 'summary', width: 80 }, 			
			{ label: '创建人编号', name: 'creatorCode', index: 'creator_code', width: 80 }, 			
			{ label: '创建人姓名', name: 'creatorName', index: 'creator_name', width: 80 }, 			
			{ label: '创建时间', name: 'created', index: 'created', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: false,
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
            JQGridResize('jqGrid', 'jqGridPager');
        }
    });
    initImport(baseURL + "sfe/agentmeeting/import","sfe/agentmeeting", "导入模板");
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		agentMeeting: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.agentMeeting = {};
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
                var url = vm.agentMeeting.id == null ? "sfe/agentmeeting/save" : "sfe/agentmeeting/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.agentMeeting),
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
                        url: baseURL + "sfe/agentmeeting/delete",
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
			$.get(baseURL + "sfe/agentmeeting/info/"+id, function(r){
                vm.agentMeeting = r.agentMeeting;
            });
		},
        exportExcel: function (event) {
            var url = "sfe/agentmeeting/export?1=1";

            window.location.href = baseURL + url;
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
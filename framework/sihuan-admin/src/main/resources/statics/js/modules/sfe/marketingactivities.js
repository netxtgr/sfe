$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sfe/marketingactivities/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true, hidden: true },
			{ label: '机构编号', name: 'divisionNumber', index: 'division_number', width: 80 }, 			
			{ label: '机构名称', name: 'divisionName', index: 'division_name', width: 80 }, 			
			{ label: '活动名称', name: 'activityName', index: 'activity_name', width: 80 }, 			
			{ label: '活动时间', name: 'activityTime', index: 'activity_time', width: 80 }, 			
			{ label: '活动目的', name: 'activityIntent', index: 'activity_intent', width: 80 }, 			
			{ label: '客户类型', name: 'consumerType', index: 'consumer_type', width: 80 }, 			
			{ label: '事项内容', name: 'material', index: 'material', width: 80 }, 			
			{ label: '活动说明', name: 'description', index: 'description', width: 80 }, 			
			{ label: '活动开始时间', name: 'startTime', index: 'start_time', width: 80 }, 			
			{ label: '活动结束时间', name: 'endTime', index: 'end_time', width: 80 }, 			
			{ label: '创建人编号', name: 'creatorCode', index: 'creator_code', width: 80 }, 			
			{ label: '创建人姓名', name: 'creatorName', index: 'creator_name', width: 80 }, 			
			{ label: '创建时间', name: 'created', index: 'created', width: 80 }, 			
			{ label: '数据有效（1：数据有效|0：逻辑删除）', name: 'valid', index: 'valid', width: 80 }			
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
    initImport(baseURL + "sfe/marketingactivities/import","sfe/marketingactivities", "导入模板");
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		marketingActivities: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.marketingActivities = {};
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
                var url = vm.marketingActivities.id == null ? "sfe/marketingactivities/save" : "sfe/marketingactivities/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.marketingActivities),
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
                        url: baseURL + "sfe/marketingactivities/delete",
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
			$.get(baseURL + "sfe/marketingactivities/info/"+id, function(r){
                vm.marketingActivities = r.marketingActivities;
            });
		},
        exportExcel: function (event) {
            var url = "sfe/marketingactivities/export?1=1";

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
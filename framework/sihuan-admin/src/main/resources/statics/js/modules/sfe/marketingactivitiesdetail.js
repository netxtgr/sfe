$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sfe/marketingactivitiesdetail/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true, hidden: true },
			{ label: '活动标识', name: 'activityId', index: 'activity_id', width: 80 }, 			
			{ label: '活动名称', name: 'activityName', index: 'activity_name', width: 80 }, 			
			{ label: '活动状态（未开始|进行中|已结束）', name: 'status', index: 'status', width: 80 }, 			
			{ label: '活动反馈', name: 'feedback', index: 'feedback', width: 80 }, 			
			{ label: '反馈人编号', name: 'creatorCode', index: 'creator_code', width: 80 }, 			
			{ label: '反馈人姓名', name: 'creatorName', index: 'creator_name', width: 80 }, 			
			{ label: '反馈时间', name: 'created', index: 'created', width: 80 }, 			
			{ label: '关联图片标识', name: 'imageId', index: 'image_id', width: 80 }			
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
    initImport(baseURL + "sfe/marketingactivitiesdetail/import","sfe/marketingactivitiesdetail", "导入模板");
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		marketingActivitiesDetail: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.marketingActivitiesDetail = {};
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
                var url = vm.marketingActivitiesDetail.id == null ? "sfe/marketingactivitiesdetail/save" : "sfe/marketingactivitiesdetail/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.marketingActivitiesDetail),
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
                        url: baseURL + "sfe/marketingactivitiesdetail/delete",
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
			$.get(baseURL + "sfe/marketingactivitiesdetail/info/"+id, function(r){
                vm.marketingActivitiesDetail = r.marketingActivitiesDetail;
            });
		},
        exportExcel: function (event) {
            var url = "sfe/marketingactivitiesdetail/export?1=1";

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
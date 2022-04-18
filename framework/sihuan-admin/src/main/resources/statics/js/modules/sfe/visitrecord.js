$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sfe/visitrecord/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true, hidden: true },
			{ label: '机构编号', name: 'divisionNumber', index: 'division_number', width: 80 }, 			
			{ label: '机构名称', name: 'divisionName', index: 'division_name', width: 80 }, 			
			{ label: '客户编号', name: 'linkmanCode', index: 'linkman_code', width: 80 }, 			
			{ label: '客户姓名', name: 'linkmanName', index: 'linkman_name', width: 80 }, 			
			{ label: '客户电话', name: 'linkmanMobile', index: 'linkman_mobile', width: 80 }, 			
			{ label: '拜访时间', name: 'visitTime', index: 'visit_time', width: 80 }, 			
			{ label: '拜访说明', name: 'description', index: 'description', width: 80 }, 			
			{ label: '拜访人编号', name: 'creatorCode', index: 'creator_code', width: 80 }, 			
			{ label: '拜访人姓名', name: 'creatorName', index: 'creator_name', width: 80 }			
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
    initImport(baseURL + "sfe/visitrecord/import","sfe/visitrecord", "导入模板");
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		visitRecord: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.visitRecord = {};
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
                var url = vm.visitRecord.id == null ? "sfe/visitrecord/save" : "sfe/visitrecord/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.visitRecord),
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
                        url: baseURL + "sfe/visitrecord/delete",
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
			$.get(baseURL + "sfe/visitrecord/info/"+id, function(r){
                vm.visitRecord = r.visitRecord;
            });
		},
        exportExcel: function (event) {
            var url = "sfe/visitrecord/export?1=1";

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
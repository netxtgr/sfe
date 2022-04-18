$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sfe/terminallinkman/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true, hidden: true },
			{ label: '姓名', name: 'linkmanName', index: 'linkman_name', width: 80 }, 			
			{ label: '性别', name: 'gender', index: 'gender', width: 80 }, 			
			{ label: '电话', name: 'mobile', index: 'mobile', width: 80 }, 			
			{ label: '籍贯', name: 'nativePlace', index: 'native_place', width: 80 }, 			
			{ label: '机构编号', name: 'divisionNumber', index: 'division_number', width: 80 }, 			
			{ label: '机构名称', name: 'divisionName', index: 'division_name', width: 80 }, 			
			{ label: '职位', name: 'position', index: 'position', width: 80 }, 			
			{ label: '状态', name: 'onJob', index: 'on_job', width: 80 }, 			
			{ label: '创建人编号', name: 'creatorCode', index: 'creator_code', width: 80 }, 			
			{ label: '创建人姓名', name: 'creatorName', index: 'creator_name', width: 80 }, 			
			{ label: '创建时间', name: 'created', index: 'created', width: 80 }, 			
			{ label: '数据有效', name: 'valid', index: 'valid', width: 80 }			
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
    initImport(baseURL + "sfe/terminallinkman/import","sfe/terminallinkman", "导入模板");
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		terminalLinkman: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.terminalLinkman = {};
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
                var url = vm.terminalLinkman.id == null ? "sfe/terminallinkman/save" : "sfe/terminallinkman/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.terminalLinkman),
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
                        url: baseURL + "sfe/terminallinkman/delete",
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
			$.get(baseURL + "sfe/terminallinkman/info/"+id, function(r){
                vm.terminalLinkman = r.terminalLinkman;
            });
		},
        exportExcel: function (event) {
            var url = "sfe/terminallinkman/export?1=1";

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
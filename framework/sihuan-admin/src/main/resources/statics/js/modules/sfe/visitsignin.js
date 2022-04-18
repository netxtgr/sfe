$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sfe/visitsignin/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true, hidden: true },
			{ label: '机构编号', name: 'divisionNumber', index: 'division_number', width: 80 }, 			
			{ label: '机构名称', name: 'divisionName', index: 'division_name', width: 80 }, 			
			{ label: '机构地址（参照：mykj_terminal_detail.terminal_address）', name: 'terminalAddress', index: 'terminal_address', width: 80 }, 			
			{ label: '位置坐标（例如，经纬度：39.916527,116.397128）', name: 'coordinate', index: 'coordinate', width: 80 }, 			
			{ label: '打卡时间', name: 'signInTime', index: 'sign_in_time', width: 80 }, 			
			{ label: '签到人编号', name: 'creatorCode', index: 'creator_code', width: 80 }, 			
			{ label: '签到人姓名', name: 'creatorName', index: 'creator_name', width: 80 }, 			
			{ label: '手机号码', name: 'mobile', index: 'mobile', width: 80 }, 			
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
    initImport(baseURL + "sfe/visitsignin/import","sfe/visitsignin", "导入模板");
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		visitSignIn: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.visitSignIn = {};
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
                var url = vm.visitSignIn.id == null ? "sfe/visitsignin/save" : "sfe/visitsignin/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.visitSignIn),
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
                        url: baseURL + "sfe/visitsignin/delete",
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
			$.get(baseURL + "sfe/visitsignin/info/"+id, function(r){
                vm.visitSignIn = r.visitSignIn;
            });
		},
        exportExcel: function (event) {
            var url = "sfe/visitsignin/export?1=1";

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
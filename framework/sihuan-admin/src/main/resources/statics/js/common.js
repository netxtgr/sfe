//jqGrid的配置信息
$.jgrid.defaults.width = 1000;
$.jgrid.defaults.responsive = true;
$.jgrid.defaults.styleUI = 'Bootstrap';

var baseURL = "../../";

//工具集合Tools
window.T = {};

// 获取请求参数
// 使用示例
// location.href = http://localhost:8080/index.html?id=123
// T.p('id') --> 123;
var url = function(name) {
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  unescape(r[2]); return null;
};
T.p = url;

//全局配置
$.ajaxSetup({
	dataType: "json",
	cache: false
});

//重写alert
window.alert = function(msg, callback){
	parent.layer.alert(msg, function(index){
		parent.layer.close(index);
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}

//重写confirm式样框
window.confirm = function(msg, callback){
	parent.layer.confirm(msg, {btn: ['确定','取消']},
	function(){//确定事件
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}


//选择一条记录
function getSelectedRow() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if(!rowKey){
    	alert("请选择一条记录");
    	return ;
    }
    
    var selectedIDs = grid.getGridParam("selarrrow");
    if(selectedIDs.length > 1){
    	alert("只能选择一条记录");
    	return ;
    }
    
    return selectedIDs[0];
}

//选择多条记录
function getSelectedRows() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if(!rowKey){
    	alert("请选择一条记录");
    	return ;
    }
    
    return grid.getGridParam("selarrrow");
}

//判断是否为空
function isBlank(value) {
    return !value || !/\S/.test(value)
}


var JQGridResize = function(item, pName) {
	item = item.replace('#', '');
	$('#gbox_' + item).css('width', 'auto');
	var tds,
		arr = [],
		colword = '',
		tmpcolwidit = 0,
		total=0,
		col,
		colwidth,
		paddinglr=24,
		wordlength=15;
	var rowobj = $('#gbox_' + item + ' .ui-jqgrid-htable tr,#' + item + ' tr:gt(0)');
	for (var i = 0, len = rowobj.length; i < len; i++) {
		col = rowobj[i];
		col = $(col).find('td,th')
		for (var j = 0, collength = col.length; j < collength; j++) {
			colword = $(col[j]).text();
			colwidth = ($(col[j]).css('display') == 'none') ? 0 : (this.getJQGridStrLenght(colword) / 2 * wordlength);
			colwidth = ($(col[j]).find('span').eq(0).css('display') == 'none') ? 0 : colwidth;
			if (colwidth == 0) {
				colword = ' ';
			}
			colwidth = colword == '' ? $(col[j]).css('width').replace('px', '') : colwidth;
			if (colword.trim() == '' || $(col[j]).hasClass('jqgrid-rownum')) {
				colwidth = 0;
			}
			arr[j] = Math.max(arr[j] ? arr[j] : 0, colwidth);
		}
	}
	//console.log(arr);

	// 调整表头宽度
	$('#gbox_' + item + ' th').each(function (idx) {
		if ($(this).css('display') != 'none' && arr[idx] > 0) {
			$(this).eq(0).css('width', Math.floor(arr[idx] + paddinglr));
			$(this).find('div').eq(0).css('width', 'auto');
			//console.log('表头：' + arr[idx]);
		}
	});

	//调整每列的宽度
	$('#' + item + ' tr:eq(0) td').each(function (idx) {
		if ($(this).css('display') != 'none' && arr[idx] > 0) {
			$(this).css('width', Math.floor(arr[idx] + paddinglr));
			//console.log('行宽：' + arr[idx]);
		}
	});

	/*total=$('#gbox_' + item).css('width').replace('px','');

    total=total>2?total-2:total;

    $('.ui-jqgrid-bdiv,.ui-jqgrid-hdiv').css('width',total);

    if (pName) {
        $(pName).css('width', 'auto');
    }
    else
    {
        pName='';
    }

    $('.ui-jqgrid-view').css('width','auto');*/

	/*if ($('.ui-jqgrid-hbox table').eq(0).css('width')!=$('#' + item).css('width')) {
        $('#' + item).css('width',$('.ui-jqgrid-hbox table').eq(0).css('width'));
    }*/
	console.log('表格动调整完成 tableName:' + item + ' pageName:' + pName);
};


var getJQGridStrLenght = function(message) {
	var strlenght = 0; //初始定义长度为0
	var txtval = $.trim(message);
	for (var i = 0; i < txtval.length; i++) {
		if (this.isCN(txtval.charAt(i)) == true) {
			strlenght = strlenght + 2; //中文为2个字符
		} else {
			if (txtval.charAt(i)==','||txtval.charAt(i)=='.')
			{
				strlenght = strlenght + 0.5;
			}
			else
			{
				strlenght = strlenght + 1;
			}
		}
	}
	return strlenght;
};

var isCN = function (str) {
	var regexCh = /[\u4E00-\u9FA5\uF900-\uFA2D]/;
	return regexCh.test(str);
};



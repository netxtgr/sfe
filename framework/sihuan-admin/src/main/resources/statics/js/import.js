var initImport = function(url, templatePath, fileName) {
	console.log("url========" + url);
	var html = '';
	html += '<div class="modal fade" id="importModal" tabindex="-1" role="dialog" aria-labelledby="importModalLabel" aria-hidden="true">';
	html += '	<div class="modal-dialog">';
	html += '		<div class="modal-content">';
	html += '			<div class="modal-header">';
	html += '				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">';
	html += '					&times;';
	html += '				</button>';
	html += '				<h4 class="modal-title" id="importModalLabel">';
	html += '					数据导入&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
	html += '					<a class="btn btn-primary" onClick="templateDownload(\'' + templatePath + '\',\'' + fileName +'\')">模板下载</a>';
	html += '				</h4>';
	html += '			</div>';
	html += '			<div class="modal-body">';
	html += '				<div id="importResult" style="overflow-y:auto; overflow-x:auto; height:100px;display:none;">';
	html += '				</div>';
	html += '				<input id="input-import" name="input-import" type="file" multiple/>';
	html += '			</div>';
	html += '		</div><!-- /.modal-content -->';
	html += '	</div><!-- /.modal -->';
	html += '</div>';
	$("#rrapp").append(html);
	$("#input-import").fileinput({
		uploadLabel: "导入",
		//uploadUrl: baseURL + "material/material/import",//上传的地址
		uploadUrl: url,//上传的地址
		uploadAsync: true,              //异步上传
		language: "zh",                 //设置语言
		dropZoneTitle: "拖拽文件到这里 …",
		showCaption: true,              //是否显示标题
		showUpload: true,               //是否显示上传按钮
		showRemove: true,               //是否显示移除按钮
		showPreview: true,             //是否显示预览按钮
		browseClass: "btn btn-primary", //按钮样式
		browseOnZoneClick: true,	   //文件显示区域是否可点击
		dropZoneEnabled: true,         //是否显示拖拽区域
		allowedFileExtensions: ["xlsx"], //接收的文件后缀
		maxFileCount: 1,                        //最大上传文件数限制
		previewFileIcon: '<i class="glyphicon glyphicon-file"></i>',
		allowedPreviewTypes: null,
		previewFileIconSettings: {
			'xlsx': '<i class="glyphicon glyphicon-file"></i>'
		}
		//https://blog.csdn.net/u011280778/article/details/94722310 插件文档说明
	}).on('fileuploaded', function (event, data, previewId, index) {
		console.log("data===" + data + "event==" + event + "previewId==" + previewId + "index==" + index);
		console.log("data.response.msg===" + data.response.msg);
		$("#importResult").show();
		$("#importResult").html("");
		if (data.response.code == 0) {
			$.each(data.response.msg, function(index, value) {
				$("#importResult").append("<p><font color='#5cb85c'>" + value + "</font></p>");
			})
		} else {
			$("#importResult").append("<p><font color='red'>导入发生异常</font></p>");
		}
	}).on('filebatchuploadcomplete', function (event, files, extra) {
		console.log("event===" + event + "files==" + files + "extra==" + extra);
		setTimeout(function () { //执行延时关闭
			//closeSelf();
		}, 5000);
	});
}

var templateDownload = function(templatePath, fileName) {
	window.location.href = baseURL + "export/downloadTemplate?templatePath=" + templatePath + "&fileName=" + fileName;
}
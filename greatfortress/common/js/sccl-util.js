/*ajax请求*/
//function ajax(url, param, datat, callback) {  
//	$.ajax({  
//		type: "post",  
//		url: url,  
//		data: param,  
//		dataType: datat,  
//		success: function(data){
//			callback;
//		},  
//		error: function () {  
//			alert("失败.."); 
//		}
//	});  
//} 

	/*设置cookie*/
function setCookie(name, value) {
	var Days = 30;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}
/*获取cookie*/
function getCookie(name) {
	var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
	if(arr = document.cookie.match(reg))
		return unescape(arr[2]);
	else
		return null;
}



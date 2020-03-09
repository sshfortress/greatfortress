Array.prototype.unique = function() {//去重
	var res = [], hash = {};
	for(var i=0, elem; (elem = this[i]) != null; i++)  {
		if (!hash[elem]){
			res.push(elem);
			hash[elem] = true;
		}
	}
	return res;
}
function handleLogStr(s) {
	if(s){
		var strarr = s.split('/'),filterarr=[],newStr=[];
		for(var k=1;k<strarr.length;k++){//过滤掉空值
			var availVal = strarr[strarr.length-k].trim();
			if(availVal){
				filterarr.push(availVal);
			}
		}
		var delRepeat=filterarr.unique(),len = delRepeat.length;
		if(len){
			if(len<6) {
				for (var j = 0; j < len; j++) {
					if(delRepeat[j].trim()){
						newStr.push(delRepeat[j].trim());
					}
				}
			}else{//logs >=6
				for(var i=0;i<5;i++){
					if(delRepeat[i].trim()){
						newStr.push(delRepeat[i].trim());
					}else{
						break;
					}
				}
			}
		}
		return newStr;
	}else{
		return '';
	}
}
function storeLogs(args) {
	if(window.localStorage){
		for(var i=0;i<args.length;i++){
			if(args[i]){
				var key = args[i].split('-')[0],val = args[i].split('-')[1];
				localStorage[key] += '/'+val;
			}
		}
	}else{
	}
}
//show search log
$(document).delegate('.checkLogs','focus',function (event) {
	var logMemu = $(this).next('.dropdown-menu');
	var logs = handleLogStr(localStorage[this.id]);
	for(var i=0,s='';i<logs.length;i++){
		s += '<li><a href="javascript:void(0);">'+logs[i]+'</a></li>';
	}
	logMemu.html(s?s:'<li><i>无记录</i></li>').addClass('show');
});
$(document).delegate('.checkLogs','blur',function (event) {
	var _self = $(this);
	setTimeout(function () {
		_self.next('.dropdown-menu').removeClass('show');
	},200)
});
//点击记录输入到文本框
$(document).delegate('.dropdown-menu a','click',function (event) {
	$(this).parents('.dropdown-menu').prev('input').val($(this).text());
	event.preventDefault();
});


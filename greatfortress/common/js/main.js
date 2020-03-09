//从session里获取ticket
var newCookie=sessionStorage.ticket;
var orderId=sessionStorage.orderId;
//路径上获取参数
	(function ($) {
        $.getUrlParam = function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
        }
    })(jQuery);
    //从路径上获取分页的参数,数字不用加密
    function page(x){
        var x=$.getUrlParam(x);
        if(x=="null"||x==null){
        	x=1;
        }
        return x
    }
    //从路径上获取一般的数字类型参数
    function number(x){
    	var xx=$.getUrlParam(x);
    	if(xx=="null"){
	     	xx=""
	     }
    	return xx;
    }
    //从路径上获取中文参数,需解密
    function txt(x){
    	var xx=decodeURI($.getUrlParam(x));
    	if(xx=="null"){
	     	xx=""
	     }
    	return xx;
    }
    //判断是否进行搜索
    function sou(x){
    	var x=$.getUrlParam(x);
    	 return x
    	
    }
    //加密文字
    function code(x){
    	var xx=encodeURI(encodeURI(x));
    	return xx;
    }
    //删除id点击事件
    function del(target,property,title,url,name,list){
    	//删除
			$(document).delegate(target, 'click', function(event) {
				event.preventDefault();
				var orderId=$(this).attr(property);
				sessionStorage.orderId=orderId;
				$('#'+name+'').dialog({
					title: title,
					onClose: function() {
						$(this).dialog('close');
					},
					buttons: [{
						text: '取消',
						'click': function() {
							$(this).dialog('close');
						}
					}, {
						text: '确定',
						'class': 'btn-primary',
						'click': function() {
							$.ajax({
							type: "post",
							url: url,
							async:true,
							data: list,
							dataType:"json",
							success: function(data) {
								if(data.status==0){
								alert(data.showMessage);
								return;
								}else{
								window.location.reload()
								}
							}
				           });
						}
					}]
				});
			});
        }
    //添加事件
     function add(target,property,title,url,name,list,front,result){
    	//删除
			$(document).delegate(target, 'click', function(event) {
				event.preventDefault();
				var orderId=$(this).attr(property);
				sessionStorage.orderId=orderId;
				front();
				$('#'+name+'').dialog({
					title: title,
					onClose: function() {
						$(this).dialog('close');
					},
					buttons: [{
						text: '取消',
						'click': function() {
							$(this).dialog('close');
						}
					}, {
						text: '确定',
						'class': 'btn-primary',
						'click': function() {
							result();
							$.ajax({
							type: "post",
							url: url,
							async:true,
							data: list,
							dataType:"json",
							success: function(data) {
								if(data.status==0){
								alert(data.showMessage);
								return;
								}else{
								window.location.reload()
								}
							}
				           });
						}
					}]
				});
			});
        }
    

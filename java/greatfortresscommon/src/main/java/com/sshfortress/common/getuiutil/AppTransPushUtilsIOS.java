//package com.sshfortress.common.getuiutil;
//import java.io.IOException;
//import java.util.Map;
//
//import com.gexin.rp.sdk.base.IPushResult;
//import com.gexin.rp.sdk.base.impl.SingleMessage;
//import com.gexin.rp.sdk.base.impl.Target;
//import com.gexin.rp.sdk.base.payload.APNPayload;
//import com.gexin.rp.sdk.exceptions.RequestException;
//import com.gexin.rp.sdk.http.IGtPush;
//import com.gexin.rp.sdk.template.TransmissionTemplate;
//import com.sshfortress.common.util.JsonUtil;
//
//
//public class AppTransPushUtilsIOS {
//	//定义常量, appId、appKey、masterSecret  在"个推控制台"中获得的应用配置  
//    // 由IGetui管理页面生成，是您的应用与SDK通信的标识之一，每个应用都对应一个唯一的AppID  
//    private static String appId = "wuRP1C6n1j6aRvHRnnkO88";
//   	// 预先分配的第三方应用对应的Key，是您的应用与SDK通信的标识之一。  
//       //private static String appKey = "RxO0F4Zt3c8YtP8tIWu4QA";  
//   	private static String appKey = "29vCjDjZFAA100HQLszaA1"; 
//   	 // 个推服务端API鉴权码，用于验证调用方合法性。在调用个推服务端API时需要提供。（请妥善保管，避免通道被盗用）  
//       //private static String masterSecret = "AsTrmD1pzs763G03H31CP6";  
//    private static String masterSecret = "e8glpSbXGk93ZGu2V5CrB9";  
//    
//    private static String host= "https://api.getui.com/apiex.htm";
//
//    /**
//     * <p class="detail">
//     * 功能：穿透的模板
//     * </p>
//     * @param msg
//     * @return
//     */
//	public static TransmissionTemplate getTemplate(Map<String, Object> msg) {
//	    
//		TransmissionTemplate template = new TransmissionTemplate();
//	    template.setAppId(appId);
//	    template.setAppkey(appKey);
//	    msg.put("time", System.currentTimeMillis());
//        
//        //透传内容，不支持转义字符
//	    //template.setTransmissionContent("");
//	    template.setTransmissionContent( JsonUtil.toJson(msg));
//	    template.setTransmissionType(2); //收到消息是否立即启动应用，1为立即启动，2则广播等待客户端自启动
//	    //iOS推送需要在代码中通过TransmissionTemplate的setAPNInfo接口设置相应的APNs通知参数。 透传模板传输的数据最大为是2KB，APNs传输数据最大支持2KB
//	    	    
//	    try {			
//			APNPayload payload = new APNPayload();
//			payload.setAutoBadge("+1");
//			payload.setContentAvailable(1);
//			payload.setSound("default");
//			APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
//			alertMsg.setTitle(msg.get("title").toString());
//			alertMsg.setBody(msg.get("text").toString());
//			payload.setAlertMsg(alertMsg);
//			template.setAPNInfo(payload);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}	    
////	    APNPayload payload = new APNPayload();
////	    //在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，显示指定数字
////	    payload.setAutoBadge("+1"); //设置角标，还可以实现显示数字的自动增减，如"+1"、"-1"、"1"等
////	    payload.setContentAvailable(1); //推送直接带有透传数据
////	    payload.setSound("default");
////	    //payload.setCategory("$由客户端定义"); 在客户端通知栏触发特定的action和button显示
////	    //简单模式APNPayload.SimpleMsg 
////        //payload.setCategory(g.toJson(msg));//增加自定义的数据,Key-Value形式
////	    payload.setAlertMsg(new APNPayload.SimpleAlertMsg(msg.get("title").toString()));
////	    //字典模式使用下者
////	    //payload.setAlertMsg(getDictionaryAlertMsg());
////	    template.setAPNInfo(payload);
//
//	    return template;
//	}
//	
//	/*字典*/
//	@SuppressWarnings("unused")
//	private static APNPayload.DictionaryAlertMsg getDictionaryAlertMsg(Map<String, Object> msg){
//	    APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
//	    alertMsg.setBody("body");
//	    alertMsg.setActionLocKey("ActionLockey");
//	    alertMsg.setLocKey("LocKey");
//	    alertMsg.addLocArg("loc-args");
//	    alertMsg.setLaunchImage("launch-image");
//	    // iOS8.2以上版本支持
//	    alertMsg.setTitle("Title");
//	    alertMsg.setTitleLocKey("TitleLocKey");
//	    alertMsg.addTitleLocArg("TitleLocArg");
//	    return alertMsg;
//	}
//	public void pushMsgToSingle(String cid, Map<String, Object> msg) throws IOException {  
//        // 代表在个推注册的一个 app，调用该类实例的方法来执行对个推的请求  
//        IGtPush push = new IGtPush(host,appKey, masterSecret);  
//    	push.connect();
//        // 创建信息模板  
//        TransmissionTemplate template = getTemplate(msg);        
//        //定义消息推送方式为，单推  
//        SingleMessage message = new SingleMessage();  
//        // 设置推送消息的内容  
//        message.setData(template);  
//        message.setOffline(true);
//	    message.setOfflineExpireTime(24 * 3600 * 1000);
//	    message.setPushNetWorkType(0);
//        // 设置推送目标  
//        Target target = new Target();  
//        target.setAppId(appId);  
//        // 设置cid  
//        target.setClientId(cid);  
//        // 获得推送结果  
//        IPushResult ret = null;
//        try {
//            ret = push.pushMessageToSingle(message, target);
//        } catch (RequestException e) {
//            e.printStackTrace();
//            ret = push.pushMessageToSingle(message, target, e.getRequestId());
//        }
//        if (ret != null) {
//            System.out.println(ret.getResponse().toString());
//        } else {
//            System.out.println("服务器响应异常");
//        }
//        /* 
//         * 1. 失败：{result=sign_error} 
//         * 2. 成功：{result=ok, taskId=OSS-0212_1b7578259b74972b2bba556bb12a9f9a, status=successed_online} 
//         * 3. 异常 
//         */  
//       
//    }  
//	
//}

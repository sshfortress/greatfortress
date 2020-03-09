//package com.sshfortress.common.getuiutil;
//
//
//import java.io.IOException;
//import java.util.Map;
//
//import com.gexin.rp.sdk.base.IPushResult;
//import com.gexin.rp.sdk.base.impl.SingleMessage;
//import com.gexin.rp.sdk.base.impl.Target;
//import com.gexin.rp.sdk.exceptions.RequestException;
//import com.gexin.rp.sdk.http.IGtPush;
//import com.gexin.rp.sdk.template.TransmissionTemplate;
//import com.sshfortress.common.util.JsonUtil;
//
////import com.gexin.rp.sdk.base.IPushResult;
////import com.gexin.rp.sdk.base.impl.SingleMessage;
////import com.gexin.rp.sdk.base.impl.Target;
////import com.gexin.rp.sdk.exceptions.RequestException;
////import com.gexin.rp.sdk.http.IGtPush;
////import com.gexin.rp.sdk.template.NotificationTemplate;
////透传
//public class AppTransPushUtils {
//
//	//定义常量, appId、appKey、masterSecret  在"个推控制台"中获得的应用配置  
//    // 由IGetui管理页面生成，是您的应用与SDK通信的标识之一，每个应用都对应一个唯一的AppID  
//    //private static String appId = "ROvRpOuttd56Aja0Q5QbL5";  
//	 private static String appId = "wuRP1C6n1j6aRvHRnnkO88";
//	// 预先分配的第三方应用对应的Key，是您的应用与SDK通信的标识之一。  
//    //private static String appKey = "RxO0F4Zt3c8YtP8tIWu4QA";  
//	 private static String appKey = "29vCjDjZFAA100HQLszaA1"; 
//	 // 个推服务端API鉴权码，用于验证调用方合法性。在调用个推服务端API时需要提供。（请妥善保管，避免通道被盗用）  
//    //private static String masterSecret = "AsTrmD1pzs763G03H31CP6";  
//    private static String masterSecret = "e8glpSbXGk93ZGu2V5CrB9";  
//    
//    private static String host= "https://api.getui.com/apiex.htm";
//    // 设置通知消息模板  
//    /* 
//     * 1. appId 
//     * 2. appKey 
//     * 3. 要传送到客户端的 msg  安卓透传消息
//     * 3.1 标题栏：key = title,  
//     * 3.2 通知栏内容： key = titleText, 
//     * 3.3 穿透内容：key = transText  
//     */  
//    
//	private static TransmissionTemplate  getNotifacationTemplate(String appId, String appKey, Map<String, Object> msg){  
//        // 在通知栏显示一条含图标、标题等的通知，用户点击后激活您的应用  
//		TransmissionTemplate  template = new TransmissionTemplate();  
//     // 设置APPID与APPKEY
//        template.setAppId(appId);
//        template.setAppkey(appKey);
//     //// 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动   
//        template.setTransmissionType(2);
//        // 设置通知栏标题与内容
//      //  template.setTitle((String) msg.get("title"));
//      //  template.setText((String) msg.get("text"));
//        // 配置通知栏图标
//      //  template.setLogo((String) msg.get("logo"));
//        // 配置通知栏网络图标
//       // template.setLogoUrl((String) msg.get("logourl"));
//        // 设置通知是否响铃，震动，或者可清除
//       // template.setIsRing(true);
//       // template.setIsVibrate(true);
//       // template.setIsClearable(true);
//        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
//        //template.setTransmissionType(2);
//        //透传内容，不支持转义字符
//        msg.put("time", System.currentTimeMillis());
//       
//        template.setTransmissionContent(JsonUtil.toJson(msg)); 
//       // template.setTransmissionContent(""); 
//       // 收到消息是否立即启动应用： 1为立即启动，2则广播等待客户端自启动
//        
//      //  Style0 style = new Style0();
//        // 设置通知栏标题与内容
//      //  style.setTitle(msg.get("title").toString());
//      //  style.setText(msg.get("text").toString());
//        // 配置通知栏图标
//        //style.setLogo("icon.png");
//        // 配置通知栏网络图标
//       // style.setLogoUrl("");
//        // 设置通知是否响铃，震动，或者可清除
//      //  style.setRing(true);
//      //  style.setVibrate(true);
//      //  style.setClearable(true);
//     //   template.setStyle(style);
//        
//        return template;  
//    }  
//    // 对单个用户推送通知消息  
//    /* 
//     * 1. cid 
//     * 2. 要传到客户端的 msg 
//     * 2.1 标题栏：key = title,  
//     * 2.2 通知栏内容： key = titleText, 
//     * 2.3 穿透内容：key = transText  
//     */  
//    public void pushMsgToSingle(String cid, Map<String, Object> msg) throws IOException {  
//        // 代表在个推注册的一个 app，调用该类实例的方法来执行对个推的请求  
//        IGtPush push = new IGtPush(host,appKey, masterSecret);  
//        push.connect();
//        // 创建信息模板  
//        TransmissionTemplate  template = getNotifacationTemplate(appId, appKey, msg);  
//        //定义消息推送方式为，单推  
//        SingleMessage message = new SingleMessage();  
//        // 设置推送消息的内容  
//        message.setData(template);  
//        // 设置推送目标  
//        Target target = new Target();  
//        target.setAppId(appId);  
//        // 设置cid  
//        target.setClientId(cid);  
//        // 获得推送结果  
//        IPushResult ret = null;
//        try {
//        	
//            ret = push.pushMessageToSingle(message, target);
//        } catch (RequestException e) {
//            e.printStackTrace();
//            ret = push.pushMessageToSingle(message, target,e.getRequestId());
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
//}

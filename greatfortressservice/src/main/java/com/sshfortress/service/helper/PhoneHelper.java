//
//package com.sshfortress.service.helper;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import net.sf.json.JSONObject;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.http.HttpResponse;
//import org.apache.http.util.EntityUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.cloopen.rest.sdk.CCPRestSmsSDK;
//import com.taobao.api.ApiException;
//import com.taobao.api.DefaultTaobaoClient;
//import com.taobao.api.TaobaoClient;
//import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
//import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
//import com.sshfortress.common.beans.HuYiSmsJson;
//import com.sshfortress.common.beans.SMSDTO;
//import com.sshfortress.common.beans.SmsSendOrder;
//import com.sshfortress.common.util.DateUtil;
//import com.sshfortress.common.util.HttpUtil;
//import com.sshfortress.common.util.JsonUtil;
//import com.sshfortress.common.util.StringUtil;
//import com.sshfortress.service.init.SysConfig;
//
//
//@Service("phoneHelper")
//public class PhoneHelper {
//
//    /** 日志. */
//    private static final Log logger = LogFactory.getLog(PhoneHelper.class);
//
//    @Autowired
//    private CCPRestSmsSDK    restSmsAPI;
//
//    public boolean sendRegCodeByXYT(String tel, String verifyCode) {
//
//        String result = null;
//        HttpURLConnection httpCon = null;
//        OutputStream os = null;
//        try {
//            URL url = new URL("http://sms.1035.mobi/store_sendSms");
//            httpCon = (HttpURLConnection) url.openConnection();
//            // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在http正文内，因此�?��设为true,
//            // 默认情况下是false;
//            httpCon.setDoInput(true);
//            // 设置是否从httpUrlConnection读入，默认情况下是true;
//            httpCon.setDoOutput(true);
//            // 设定请求的方法为"POST"，默认是GET
//            httpCon.setRequestMethod("POST");
//            // 设定传�?的内容类型是可序列化的java对象
//            // (如果不设此项,在传送序列化对象�?当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
//            httpCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            StringBuilder builder = new StringBuilder();
//            builder.append("username=");
//            builder.append("store");
//            builder.append("&password=");
//            builder.append("store123");
//            builder.append("&tel=");
//            builder.append(tel);
//            builder.append("&content=");
//            builder.append("验证码：" + verifyCode);
//            os = httpCon.getOutputStream();
//            os.write(builder.toString().getBytes("utf-8"));
//            os.flush();
//            if (httpCon.getResponseCode() == 200) {
//                BufferedReader br = new BufferedReader(new InputStreamReader(
//                    httpCon.getInputStream()));
//                httpCon.connect();
//                result = br.readLine();
//                br.close();
//            }
//            System.out.println(result);
//            Map<String, String> resultMap =  JsonUtil.toObject(result, Map.class);
//            if (resultMap != null && resultMap.get("result") != null
//                && resultMap.get("result").toString().equals("0")) {
//                return true;
//            }
//            logger.error("发送短信校验码失败：" + result);
//            return false;
//        } catch (Exception e) {
//            logger.error(tel + "发送校验码失败：" + verifyCode + "; 发送短信结果:" + result, e);
//            return false;
//        } finally {
//            try {
//                if (os != null)
//                    os.close();
//                if (httpCon != null) {
//                    httpCon.disconnect();
//                    httpCon = null;
//                }
//            } catch (IOException e) {
//                logger.error("关闭流异常", e);
//                os = null;
//                httpCon = null;
//            }
//        }
//    }
//
//    /**
//     * <p class="detail">
//     * 功能：容联云通讯-发送短信
//     * </p>
//     * @param tel   手机号码，多个用，分�?
//     * @param verifyCode  验证�?
//     * @param content  短信内容 按短信模板对应占位符变量�?
//     *   Map<Integer, String> content = new HashMap<Integer, String>();
//     *   content.put(2, verifyCode);
//     *   content.put(3, "失效时间");
//     *   content.put(1, "注册");
//     * @return
//     */
//    public boolean sendRegCodeByYTX(SmsSendOrder smsSendOrder) {
//
//        HashMap<String, Object> result = null;
//
//        try {
//            // 请求地址 端口
//            restSmsAPI.init(smsSendOrder.getSmsUrl(), smsSendOrder.getSmsPort());
//            // 接口账号密码
//            restSmsAPI.setAccount(smsSendOrder.getAccountName(), smsSendOrder.getAccountPsd());
//            // 接口注册应用ID
//            restSmsAPI.setAppId(smsSendOrder.getAppId());
//
//            // 接口短信模板id
//            result = restSmsAPI.sendTemplateSMS(smsSendOrder.getReceiverPhone(),
//                smsSendOrder.getSmsTempid(), smsSendOrder.getContents());
//        } catch (Exception e) {
//            logger.error(smsSendOrder.getReceiverPhone() + ":容联云发送短信接口异常", e);
//            return false;
//        }
//
//        if (StringUtils.equals("000000", result.get("statusCode").toString())) {
//            //正常返回输出data包体信息（map�?
//            /*            @SuppressWarnings("unchecked")
//                        Map<String, Object> data = (HashMap<String, Object>) result.get("data");
//
//                        Iterator<Map.Entry<String, Object>> entries = data.entrySet().iterator();
//
//                        while (entries.hasNext()) {
//                            Map.Entry<String, Object> entry = entries.next();
//                            if (logger.isDebugEnabled()) {
//                                logger.debug(entry.getKey() + " = " + entry.getValue());
//                            }
//                        }*/
//            return true;
//        }
//        //异常返回输出错误码和错误信息      
//        logger.error("错误码" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
//
//        return false;
//    }
//    
//    /**
//     * 云通讯
//     * @param smsSendOrder
//     * @return
//     */
//    
//    public boolean sendMessageByYTX(SmsSendOrder smsSendOrder) {
//
//        HashMap<String, Object> result = null;
//
//        try {
//            // 请求地址 端口
//            restSmsAPI.init(smsSendOrder.getSmsUrl(), smsSendOrder.getSmsPort());
//            // 接口账号密码
//            restSmsAPI.setAccount(smsSendOrder.getAccountName(), smsSendOrder.getAccountPsd());
//            // 接口注册应用ID
//            restSmsAPI.setAppId(smsSendOrder.getAppId());
//
//            // 接口短信模板id
//            result = restSmsAPI.sendTemplateSMS(smsSendOrder.getReceiverPhone(),
//                smsSendOrder.getSmsTempid(), smsSendOrder.getContents());
//        } catch (Exception e) {
//            logger.error(smsSendOrder.getReceiverPhone() + ":容联云发送短信接口异常", e);
//            return false;
//        }
//
//        if (StringUtils.equals("000000", result.get("statusCode").toString())) {
//            return true;
//        }
//        //异常返回输出错误码和错误信息      
//        logger.error("错误�?" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
//
//        return false;
//    }
//    
//    
//    
//    /**
//	 * <p class="detail">
//	 * 功能：阿里大鱼短信发送
//	 * </p>
//	 * @param alidayu 短信发送参数dto
//	 * @return
//	 */
//	public static String sendSmsByAliDaYu(SMSDTO alidayu) {
//		
//		
//		
//		if(StringUtil.isNullOrEmpty(SysConfig.getValue("sms_alidayu_open"))||"0".equals(SysConfig.getValue("sms_alidayu_open"))){
//			return "{\"result\":\"0\"}";
//		}
//			TaobaoClient client = new DefaultTaobaoClient(SysConfig.getValue("sms_alidayu_url"), SysConfig.getValue("sms_alidayu_appkey"), SysConfig.getValue("sms_alidayu_secret"));
//			AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
//			req.setExtend(alidayu.getUserId());//userId 响应参数上面会有返回
//			req.setSmsType(alidayu.getSmsType());
//			req.setSmsFreeSignName(alidayu.getSignName());
//			req.setSmsParamString(alidayu.getParam());
//			req.setRecNum(alidayu.getPhoneNum());//最多200个
//			req.setSmsTemplateCode(alidayu.getSmsTemplateCode());
//			AlibabaAliqinFcSmsNumSendResponse rsp=new AlibabaAliqinFcSmsNumSendResponse();
//			try {
//				rsp = client.execute(req);
//				
//				if(rsp.isSuccess()){
//				System.out.print("【短信】大鱼短信，发送结果 ={},参数：手机号={},模板名称="+alidayu.getSmsTemplateCode()+",参数="+alidayu.getParam()+""+rsp.isSuccess()+""+alidayu.getPhoneNum());	
//					return "{\"result\":\"0\"}";
//					
//				}else{
//					String result="";
//					if(rsp.getSubCode().equals("isv.BUSINESS_LIMIT_CONTROL")){
//						result="该手机短信发送太频繁，请稍后再试!";
//					}else if(rsp.getSubCode().equals("isv.isv.MOBILE_NUMBER_ILLEGAL")){
//						result="请输入正确的手机号码";
//					}else if(rsp.getSubCode().equals("isv.isv.MOBILE_COUNT_OVER_LIMIT")){
//						result="手机号码数量超过限制";
//					}else{
//						result="系统繁忙，请稍后再试！";
//					}
//					logger.info(result);
//					return "{\"result\":"+result+"}";
//				}
//				} catch (ApiException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				logger.info(rsp.getErrorCode());
//				return "{\"result\":"+rsp.getErrorCode()+"}";
//			}
//		
//	}
//
//	
//	public static String sendSmsByHuYi(SMSDTO huyi) {
//		System.out.println("执行了互亿无线 开始");
//		String host = "http://106.ihuyi.cn/webservice/sms.php";
//		String path = "";
//		String method = "POST";
//
//		String account = SysConfig.getValue("sms_huyiwx_app_id");
//		String password= SysConfig.getValue("sms_huyiwx_app_key");
//		String mobile=huyi.getPhoneNum();
//		String content= SysConfig.getValue("sms_huyiwx_model").replaceAll("【变量】", huyi.getContent()) ;
//		String time=DateUtil.dateFormat(new Date(), "yyyyMMddHHmmss").substring(0,8);
//		String format="json";
//		System.out.println(account+" "+password+" "+mobile+" "+" "+content);
//		Map<String, String> headers = new HashMap<String, String>();
//		headers.put("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
//
//		Map<String, String> querys = new HashMap<String, String>();
//		querys.put("method", "Submit");		
//		querys.put("account", account);
//		querys.put("password", password);
//		querys.put("mobile", mobile);
//		querys.put("content", content);
//		querys.put("time", time);
//		querys.put("format", format);
//		
//		String jsonStr = "";
//		HuYiSmsJson o=null;
//		try {
//			System.out.println("执行参数开始");
//			HttpResponse response = HttpUtil.doGet(host, path, method, headers,
//					querys);
//			System.out.println(response.toString());
//			jsonStr = EntityUtils.toString(response.getEntity());
//            System.out.println(jsonStr);
//			JSONObject jsonBean = JSONObject.fromObject(jsonStr); 
//			o=(HuYiSmsJson) JSONObject.toBean(jsonBean, HuYiSmsJson.class);
//			System.out.println("执行参数结束");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("执行了互亿无线 结束返回");
//		return o.getCode();
//		
//		
//	}
//	
//	
//	
//	public static void main(String[] args) {
//		System.out.println(new Date());
//		String host = "http://106.ihuyi.cn/webservice/sms.php";
//		String path = "";
//		String method = "POST";
//
//		String account = "cf_baibird";
//		String password= "418b12e0737e07eeb412995b78300bcf";
//		String mobile="15711108898";
//		String content="您的验证码是：1234。请不要把验证码泄露给其他人。如非本人操作，可不用理会！" ;
//		
//		String time=DateUtil.dateFormat(new Date(), "yyyyMMddHHmmss").substring(0,8);
//		String format="json";
//		System.out.println(account+" "+password+" "+mobile+" "+" "+content);
//		Map<String, String> headers = new HashMap<String, String>();
//		headers.put("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
//
//		Map<String, String> querys = new HashMap<String, String>();
//		querys.put("method", "Submit");		
//		querys.put("account", account);
//		querys.put("password", password);
//		querys.put("mobile", mobile);
//		querys.put("content", content);
//		querys.put("time", time);
//		querys.put("format", format);
//		
//		String jsonStr = "";
//		HuYiSmsJson o=null;
//		try {
//			HttpResponse response = HttpUtil.doGet(host, path, method, headers,
//					querys);
//			System.out.println(response.toString());
//			jsonStr = EntityUtils.toString(response.getEntity());
//            System.out.println(jsonStr);
//			JSONObject jsonBean = JSONObject.fromObject(jsonStr); 
//			o=(HuYiSmsJson) JSONObject.toBean(jsonBean, HuYiSmsJson.class);
//           
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(new Date());
//	}
//}

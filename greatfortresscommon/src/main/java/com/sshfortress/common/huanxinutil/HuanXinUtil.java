//package com.sshfortress.common.huanxinutil;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import net.sf.json.JSONObject;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.util.EntityUtils;
//
//import com.gexin.fastjson.JSON;
//import com.sshfortress.common.util.HttpUtil;
//
//public class HuanXinUtil {
//    
//	/**
//	 * 获取环信的Token 
//	 * @return
//	 */
//	public static String token=""; 
//	public static Token getToken(Map<String,Object> m) {
//		String org_name=m.get("orgName").toString();
//		String app_name=m.get("appName").toString();
//        String host="http://a1.easemob.com";
//        String path="/"+org_name+"/"+app_name+"/token";
//        Map<String, String> headers = new HashMap<String, String>();
//        headers.put("Content-Type", "application/json");
//		Map<String, String> querys = new HashMap<String, String>();
//		Map<String, String> bodys = new HashMap<String, String>();
//		bodys.put("grant_type", "client_credentials");
//		bodys.put("client_id", m.get("clientId").toString());
//		bodys.put("client_secret", m.get("clientSecret").toString());
//		
//		HttpResponse response=null;
//		Token o=null;
//		try {
//			//response = HttpUtil.doPost(host, path, "POST", headers,querys,bodys);
//			response=HttpUtil.doPost(host, path,  "POST", headers, querys,JSON.toJSON(bodys).toString());
//			
//			System.out.println(response.toString());
//			int status=response.getStatusLine().getStatusCode();
//			String jsonStr = EntityUtils.toString(response.getEntity());
//			JSONObject jsonBean = JSONObject.fromObject(jsonStr); 
//			o=(Token) JSONObject.toBean(jsonBean, Token.class);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
//		token="Bearer "+o.getAccess_token();
//        return o;
//	}
//	
//	
//	
//	/****
//	 * 
//	 * @param orgName appkey
//	 * @param appName appname
//	 * @param userName 用户userId
//	 * @param passWord 用户密码
//	 * @param nickName 用户昵称
//	 * @return
//	 */
//	public static Reg regUser(Map<String,Object> m,String userName,String passWord,String nickName) {
//		String org_name=m.get("orgName").toString();
//		String app_name=m.get("appName").toString();
//        String host="http://a1.easemob.com";
//        String path="/"+org_name+"/"+app_name+"/users";
//        Map<String, String> headers = new HashMap<String, String>();
//        if(HuanXinUtil.token.equals("")){
//        	HuanXinUtil.getToken(m);
//        }
//        headers.put("Authorization",HuanXinUtil.token);
//        headers.put("Content-Type", "application/json");
//        Map<String, String> querys = new HashMap<String, String>();
//		Map<String, String> bodys = new HashMap<String, String>();
//		bodys.put("username", userName);
//		bodys.put("password", passWord);
//		bodys.put("nickname", nickName);
//		
//		HttpResponse response=null;
//		Reg o=null;
//		try {
//			//response = HttpUtil.doPost(host, path, "POST", headers,querys,bodys);
//			response=HttpUtil.doPost(host, path,  "POST", headers, querys,JSON.toJSON(bodys).toString());
//			
//			System.out.println(response.toString());
//			int status=response.getStatusLine().getStatusCode();
//			String jsonStr = EntityUtils.toString(response.getEntity());
//			JSONObject jsonBean = JSONObject.fromObject(jsonStr); 
//			Map<String, Class> classMap = new HashMap<String, Class>();
//			classMap.put("entities", entity.class);
//			o=(Reg) JSONObject.toBean(jsonBean, Reg.class,classMap);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
//        return o;
//	}
//	
//	
//	
//	/***
//	 * 查看用户好友
//	 * ownerUserName 就是 IM注册的时候的 username参数
//	 * @param huanxinId
//	 * @return
//	 */
//	public  static FindFriend findFriend(Map<String,Object> m,String ownerUserName) {
//		String org_name=m.get("orgName").toString();
//		String app_name=m.get("appName").toString();
//        String host="http://a1.easemob.com";
//        String path="/"+org_name+"/"+app_name+"/users";
//        Map<String, String> headers = new HashMap<String, String>();
//        if(HuanXinUtil.token.equals("")){
//        	HuanXinUtil.getToken(m);
//        }
//        headers.put("Authorization", HuanXinUtil.token);
//        headers.put("Content-Type", "application/json");
//        Map<String, String> querys = new HashMap<String, String>();
//		Map<String, String> bodys = new HashMap<String, String>();
//		bodys.put("owner_username", ownerUserName);
//		
//		HttpResponse response=null;
//		FindFriend o=null;
//		try {
//			//response = HttpUtil.doPost(host, path, "GET", headers,querys,bodys);
//			response=HttpUtil.doPost(host, path,  "POST", headers, querys,JSON.toJSON(bodys).toString());
//			
//			System.out.println(response.toString());
//			int status=response.getStatusLine().getStatusCode();
//			String jsonStr = EntityUtils.toString(response.getEntity());
//			JSONObject jsonBean = JSONObject.fromObject(jsonStr); 
//			o=(FindFriend) JSONObject.toBean(jsonBean, FindFriend.class);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
//        return o;
//	}
//	
//	
//	
//	/***
//	 * 添加IM用户好友
//	 * @param huanxinId
//	 * @return
//	 */
//	public static AddFriend addFriend(Map<String,Object> m,String owerUserName,String friendUserName) {
//		String org_name=m.get("orgName").toString();
//		String app_name=m.get("appName").toString();
//        String host="http://a1.easemob.com";
//        String path="/"+org_name+"/"+app_name+"/users";
//        Map<String, String> headers = new HashMap<String, String>();
//        if(HuanXinUtil.token.equals("")){
//        	HuanXinUtil.getToken(m);
//        }
//        headers.put("Authorization", HuanXinUtil.token);
//        headers.put("Content-Type", "application/json");
//        Map<String, String> querys = new HashMap<String, String>();
//		Map<String, String> bodys = new HashMap<String, String>();
//		bodys.put("owner_username", owerUserName);
//		bodys.put("friend_username", friendUserName);
//		
//		HttpResponse response=null;
//		AddFriend o=null;
//		try {
//			//response = HttpUtil.doPost(host, path, "POST", headers,querys,bodys);
//			response=HttpUtil.doPost(host, path,  "POST", headers, querys,JSON.toJSON(bodys).toString());
//			
//			System.out.println(response.toString());
//			int status=response.getStatusLine().getStatusCode();
//			String jsonStr = EntityUtils.toString(response.getEntity());
//			JSONObject jsonBean = JSONObject.fromObject(jsonStr); 
//			o=(AddFriend) JSONObject.toBean(jsonBean, AddFriend.class);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
//        return o;
//	}
//	
//	
//	/***
//	 * 解除IM用户好友
//	 * @param huanxinId
//	 * @return
//	 */
//	public static RemoveFriend removeFriend(Map<String,Object> m,String owerUserName,String friendUserName) {
//		String org_name=m.get("orgName").toString();
//		String app_name=m.get("appName").toString();
//        String host="http://a1.easemob.com";
//        String path="/"+org_name+"/"+app_name+"/users";
//        Map<String, String> headers = new HashMap<String, String>();
//        if(HuanXinUtil.token.equals("")){
//        	HuanXinUtil.getToken(m);
//        }
//        headers.put("Authorization", HuanXinUtil.token);
//        headers.put("Content-Type", "application/json");
//        Map<String, String> querys = new HashMap<String, String>();
//		Map<String, String> bodys = new HashMap<String, String>();
//		bodys.put("owner_username", owerUserName);
//		bodys.put("friend_username", friendUserName);
//		
//		HttpResponse response=null;
//		RemoveFriend o=null;
//		try {
//			//response = HttpUtil.doPost(host, path, "DELETE", headers,querys,bodys);
//			response=HttpUtil.doPost(host, path,  "POST", headers, querys,JSON.toJSON(bodys).toString());
//			
//			System.out.println(response.toString());
//			int status=response.getStatusLine().getStatusCode();
//			String jsonStr = EntityUtils.toString(response.getEntity());
//			JSONObject jsonBean = JSONObject.fromObject(jsonStr); 
//			o=(RemoveFriend) JSONObject.toBean(jsonBean, RemoveFriend.class);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
//        return o;
//	}
//	
//	
//	
//	/***
//	 * IM添加黑名单 
//	 * @param owerUserName
//	 * @param friendUserName
//	 * @return
//	 */
//	
//	public static Block addBlocks(Map<String,Object> m,String owerUserName,String[] userNames) {
//		String org_name=m.get("orgName").toString();
//		String app_name=m.get("appName").toString();
//        String host="http://a1.easemob.com";
//        String path="/"+org_name+"/"+app_name+"/users";
//        Map<String, String> headers = new HashMap<String, String>();
//        if(HuanXinUtil.token.equals("")){
//        	HuanXinUtil.getToken(m);
//        }
//        headers.put("Authorization", HuanXinUtil.token);
//        headers.put("Content-Type", "application/json");
//        Map<String, String> querys = new HashMap<String, String>();
//		Map<String, String> bodys = new HashMap<String, String>();
//		bodys.put("owner_username", owerUserName);
//		bodys.put("usernames", userNames.toString());
//		
//		HttpResponse response=null;
//		Block o=null;
//		try {
//			//response = HttpUtil.doPost(host, path, "POST", headers,querys,bodys);
//			response=HttpUtil.doPost(host, path,  "POST", headers, querys,JSON.toJSON(bodys).toString());
//			
//			System.out.println(response.toString());
//			int status=response.getStatusLine().getStatusCode();
//			String jsonStr = EntityUtils.toString(response.getEntity());
//			JSONObject jsonBean = JSONObject.fromObject(jsonStr); 
//			o=(Block) JSONObject.toBean(jsonBean, Block.class);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
//        return o;
//	}
//	
//	
//	/***
//	 * IM删除用户
//	 * userName 环信ID
//	 * m 环信的参数 orgName 
//	 * appName 
//	 * @param owerUserName
//	 * @param friendUserName
//	 * @return
//	 */	
//	public static Delete deleteUser(Map<String,Object> m,String userName) {
//		String org_name=m.get("orgName").toString();
//		String app_name=m.get("appName").toString();
//        String host="http://a1.easemob.com";
//        String path="/"+org_name+"/"+app_name+"/users";
//        Map<String, String> headers = new HashMap<String, String>();
//        if(HuanXinUtil.token.equals("")){
//        	HuanXinUtil.getToken(m);
//        }
//        headers.put("Authorization", HuanXinUtil.token);
//        headers.put("Content-Type", "application/json");
//        Map<String, String> querys = new HashMap<String, String>();
//		Map<String, String> bodys = new HashMap<String, String>();
////		bodys.put("username", userName.toString());
////		querys.put("username", userName.toString());
//		path=path+"/"+userName.toString();
//		HttpResponse response=null;
//		Delete o=null;
//		try {
//			//response = HttpUtil.doPost(host, path, "POST", headers,querys,bodys);
//			response=HttpUtil.doDelete(host, path, "DELETE", headers, querys);
//			
//			System.out.println(response.toString());
//			int status=response.getStatusLine().getStatusCode();
//			String jsonStr = EntityUtils.toString(response.getEntity());
//			JSONObject jsonBean = JSONObject.fromObject(jsonStr); 
//			o=(Delete) JSONObject.toBean(jsonBean, Delete.class);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
//        return o;
//	}
//	
//}

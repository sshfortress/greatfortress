package com.sshfortress.common.util;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


/**
 * <p class="detail">
 * 功能：XML字符串处理
 * </p>
 * @ClassName: XMLUtil 
 * @version V1.0  
 */
public class XMLUtil {
private static final String HEAD = "head";
private static final String BODY = "body";


public static void main(String[] args) throws DocumentException {
String xml = "<xml><return_code><![CDATA[SUCCESS]]></return_code>"+
"<return_msg><![CDATA[OK]]></return_msg>"+
"<appid><![CDATA[wx72a9d8637eb96ea1]]></appid>"+
"<mch_id><![CDATA[1341772801]]></mch_id>"+
"<nonce_str><![CDATA[3TDlEw5E031l275A]]></nonce_str>"+
"<sign><![CDATA[31AD18275BD44175BE632FB42317524F]]></sign>"+
"<result_code><![CDATA[SUCCESS]]></result_code>"+
"<prepay_id><![CDATA[wx201701131651591b0eec21fe0188376960]]></prepay_id>"+
"<trade_type><![CDATA[APP]]></trade_type>"+
"</xml>";
System.out.println(doXMLParse(xml).toString());
}

/**
 * <p class="detail">
 * 功能：XML字符串转化成map对象
 * </p>
 * @param xml xml字符串
 * @return
 */
public static Map<String,Object> doXMLParse(String xml) {  
    Map<String,Object> map = new HashMap<String,Object>();  
    Document doc = null;  
    try {  
        doc = DocumentHelper.parseText(xml); // 将字符串转为XML  
        Element rootElt = doc.getRootElement(); // 获取根节点  
        List<Element> list = rootElt.elements();//获取根节点下所有节点  
        for (Element element : list) {  //遍历节点  
            map.put(element.getName(), element.getText()); //节点的name为map的key，text为map的value  
        }  
    } catch (DocumentException e) {  
        e.printStackTrace();  
    } catch (Exception e) {  
        e.printStackTrace();  
    }  
    return map;  
}


}
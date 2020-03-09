package com.sshfortress.common.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sshfortress.common.contants.ViewContants;
import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.securityutil.AES128;
import com.sshfortress.common.util.ResponseObj;

/**
 * <p class="detail">
 * 功能：验证码校验过滤
 * </p>
 * 
 * @ClassName: SecurityFilter
 * @version V1.0
 */
public class IdentifyingCodeFilter implements Filter {
	static Logger log = LoggerFactory.getLogger(IdentifyingCodeFilter.class);

	String[] strUrl = {"/userapp/sendRegCode.do","/userapp/getRegistRegCode.do","/userapp/fastLoginRegCode.do","/userapp/getPasswordRegCode.do","/userapp/sendRegCode.do" };

	@Override
	public void destroy() {
		log.info("执行校验过滤器IdentifyingCodeFilter--destroy方法完成");

	}

	/**
	 * <p class="detail">
	 * 功能：过滤 接口
	 * </p>
	 * 
	 * @param req
	 * @param res
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		req.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		boolean b = false;
		ResponseObj ajaxResponseObj = new ResponseObj(
				ViewShowEnums.ERROR_FAILED.getStatus(),
				ViewContants.PARAMETER_ERORY);
		String retJson = "";
		String userName = req.getParameter("userName");
		String url = request.getRequestURI();
		
		for (int i = 0; i < strUrl.length; i++) {
			if(url.contains(strUrl[i])){
				b = true;
				break;
			}			
		}
		if (b) {
			try {
				/**变更request的参数**/
				String tParam = AES128.decryptAES(userName, "7t3e506j9z10xbd4");
				
				Map<String, String[]> m = new HashMap<String, String[]>(request.getParameterMap());
				m.put("userName", new String[] { tParam });
				ParameterRequestWrapper prw = new ParameterRequestWrapper(request, m);
				request=prw;
			
				chain.doFilter(request, response);
				
			} catch (Exception e) {
				ajaxResponseObj.setStatus(ViewShowEnums.ERROR_FAILED
						.getStatus());
				ajaxResponseObj.setShowMessage("参数校验未通过");
				retJson = ajaxResponseObj.toAppJson();
				PrintWriter writer = response.getWriter();
				writer.write(retJson);
				writer.flush();
				writer.close();
				return;
			}
		} else {
			chain.doFilter(request, response);
		}
		// String sysApiIsopenDebug=SysConfig.getValue("sys_api_debug_level");
		// sysApiIsopenDebug=(sysApiIsopenDebug==null)?"2":sysApiIsopenDebug;
		// String debug=req.getParameter("debug");

		// if("2".equals(sysApiIsopenDebug)){
		// chain.doFilter(request, response);
		// }else if("1".equals(sysApiIsopenDebug) && debug!=null &&
		// "1".equals(debug)){
		// chain.doFilter(request, response);
		// }else{
		// String url=request.getRequestURI();
		// log.debug("api sign url:",url);
		// Map<String,Object> map=getParameterMap(request);
		// log.debug("api sign signature:{}",map.get("signature"));
		// SignCheckResultEnum signResult=CheckSecurity.sign(map, url);
		// if(signResult.isEquals(SignCheckResultEnum.SIGN_ISNULL.getValue())){
		// resObj.setStatus(AjaxResponseObj.ERROR_FAILED);
		// resObj.setShowMessage("当前接口必须进行安全签名");
		// retJson= resObj.getJsonpData(request, response);
		// PrintWriter writer=response.getWriter();
		// writer.write(retJson);
		// writer.flush();
		// writer.close();
		// }else
		// if(signResult.isEquals(SignCheckResultEnum.SIGN_CHECK_FAIL.getValue())){
		// resObj.setStatus(AjaxResponseObj.ERROR_FAILED);
		// resObj.setShowMessage("接口安全校验未通过");
		// retJson= resObj.getJsonpData(request, response);
		// PrintWriter writer=response.getWriter();
		// writer.write(retJson);
		// writer.flush();
		// writer.close();
		// }else{
		// chain.doFilter(request, response);
		// }
		// }
		log.info("执行校验过滤器IdentifyingCodeFilter--doFilter方法完成");

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		log.info("执行校验过滤器IdentifyingCodeFilterr--init方法完成");

	}

	/**
	 * <p class="detail">
	 * 功能：将request 参数转化成map
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getParameterMap(HttpServletRequest request) {
		// 参数Map
		Map<String, String[]> properties = request.getParameterMap();
		// 返回值Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Iterator<Entry<String, String[]>> entries = properties.entrySet()
				.iterator();
		Map.Entry<String, String[]> entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry<String, String[]>) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		return returnMap;
	}
}

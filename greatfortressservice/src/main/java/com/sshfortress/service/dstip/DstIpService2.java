package com.sshfortress.service.dstip;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshfortress.common.entity.DstipLogin;
import com.sshfortress.common.entity.DstipLoginHistory;
import com.sshfortress.common.model.DetailByCountry;
import com.sshfortress.common.model.ListByLoginDstIp;
import com.sshfortress.common.util.GeoIPUtil;
import com.sshfortress.common.util.StringUtil;
import com.sshfortress.dao.common.mapper.CommonMapper;
import com.sshfortress.dao.dstip.mapper.DstipLoginHistoryMapper;
import com.sshfortress.dao.dstip.mapper.DstipLoginMapper;

@Service
public class DstIpService2 {

	@Autowired
	CommonMapper commonMapper;

	@Autowired
	DstipLoginHistoryMapper dstipLoginHistoryMapper;

	@Autowired
	DstipLoginMapper dstipLoginMapper;

	public Map<String,Object> addDstIpLogin(DstipLogin dstipLogin,String minTime,String maxTime){
		Map<String,Object> m=new HashMap<String, Object>();
		//保存历史登录信息
		DstipLoginHistory dstipLoginHistory=new DstipLoginHistory();
		//表示web页面初始地图
		if(StringUtil.isNullOrEmpty(dstipLogin.getDstIp())){
			if(!StringUtil.isNullOrEmpty(minTime) || !StringUtil.isNullOrEmpty(maxTime)){
				m.put("minTime", minTime);
				m.put("maxTime", maxTime);
			}else{
				m.put("nowTime", "nowTime");
			}
			return this.resultMap(m);
		}else{
			String dstIp=dstipLogin.getDstIp();
			Integer loginStatus=dstipLogin.getLoginStatus();
			String userId=dstipLogin.getUserId();
			//GeoIP解析
			Map<String, Object> addressMap=GeoIPUtil.getIPDetailByGeoIP(dstIp);
			//解析出来是内网IP，直接返回
			if(addressMap.isEmpty()){
				m.put("nowTime", "nowTime");
				return this.resultMap(m);
			}
			String countryEnName=addressMap.get("countryEnName").toString();
			//中国
			if(countryEnName.equals("China")){
				m.put("countryCn", "中国");
				//城市，只有中国才有
				String city=addressMap.get("city").toString();
				m.put("countryEn", city);
				dstipLogin.setCity(city);
				dstipLoginHistory.setCity(city);
			}
			//外国
			else{
				m.put("countryEn", countryEnName);
				dstipLogin.setCity(countryEnName);
				dstipLoginHistory.setCity(countryEnName);
			}
			
			m.put("sourceIp", dstIp);

			//通过IP解析获取国家相关信息
			DetailByCountry detailByCountry=commonMapper.getCountryDetailByIp(m);

			//国家中文
			String countryCn=detailByCountry.getCountryCn();
			//国家英文
			String countryEn=detailByCountry.getCountryEn();
			//纬度
			String lat=addressMap.get("lat").toString();
			//经度
			String lon=addressMap.get("lon").toString();
			Long userGroup=dstipLogin.getUserGroup();

			
			dstipLogin.setCountryCn(countryCn);
			dstipLogin.setCountryEn(countryEn);
			dstipLogin.setUserGroup(userGroup);
			dstipLogin.setUserId(userId);
			dstipLogin.setLat(lat);
			dstipLogin.setLoginStatus(loginStatus);
			dstipLogin.setLon(lon);
			//是否登陆过
			m.put("dstIp", dstipLogin.getDstIp());
			List<Long> isExistDstIpLogin=dstipLoginMapper.isExistDstIpLogin(m);
			if(isExistDstIpLogin!=null && !isExistDstIpLogin.isEmpty()){
				dstipLogin.setUpdateTime(new Date());
				dstipLoginMapper.updateDstIpLogin(dstipLogin);
			}else{
				dstipLogin.setUpdateTime(new Date());
				dstipLogin.setCreateTime(new Date());
				dstipLoginMapper.addDstIpLogin(dstipLogin);
			}
			
			
			dstipLoginHistory.setCountryCn(countryCn);
			dstipLoginHistory.setCountryEn(countryEn);
			dstipLoginHistory.setCreateTime(new Date());
			dstipLoginHistory.setDstIp(dstIp);
			dstipLoginHistory.setLat(lat);
			dstipLoginHistory.setLoginStatus(loginStatus);
			dstipLoginHistory.setLon(lon);
			dstipLoginHistory.setRemark("");
			dstipLoginHistory.setUserGroup(userGroup);
			dstipLoginHistory.setUserId(userId);
			dstipLoginHistoryMapper.addDstIpLoginHistory(dstipLoginHistory);

			/**查询某段时间的登录用户集合(默认当前时间一天内)*/
			m.put("nowTime", "nowTime");

			Map<String,Object> resultMap=this.resultMap(m);
			return resultMap;
		}
	}

	public Map<String,Object> resultMap(Map<String,Object> m){
		//登录的历史记录
		List<ListByLoginDstIp> loginDstIpList=dstipLoginMapper.getLoginDstIpListByNow(m);

		/**固定的数据*/
		ArrayList<Double> hangzhouArrayList=new ArrayList<Double>();
		//经度
		hangzhouArrayList.add(120.15515);
		//纬度
		hangzhouArrayList.add(30.27415);
		Map<String, Object> hangzhouCityMap = new HashMap<String, Object>();
		hangzhouCityMap.put("Hangzhou", hangzhouArrayList.toArray());

		Map<String, Object> hangZhouMap = new HashMap<String, Object>();
		hangZhouMap.put("name", "Hangzhou");

		//存放所有登录地区的点的集合
		Map<String,Object> geoCoordMap=new HashMap<String,Object>();
		geoCoordMap.put("Hangzhou", hangzhouArrayList.toArray());

		//存放国家/城市对应的登录的数量
		List<List<Map<String,Object>>> mmList=new ArrayList<List<Map<String,Object>>>();

		if(loginDstIpList!=null && !loginDstIpList.isEmpty()){
			for(int i=0;i<loginDstIpList.size();i++){
				Double lonByLogin=Double.valueOf(loginDstIpList.get(i).getLon());
				Double latByLogin=Double.valueOf(loginDstIpList.get(i).getLat());
				String countryCnByLogin=loginDstIpList.get(i).getCountryCn();
				String countryEnByLogin=loginDstIpList.get(i).getCountryEn();
				String cityByLogin=loginDstIpList.get(i).getCity();
				//存放国家/城市名称及其对应的经纬度
				Map<String, Object> cityMap = new HashMap<String, Object>();
				//存放经度纬度
				ArrayList<Double> latAndLonArrayList=new ArrayList<Double>();
				latAndLonArrayList.add(lonByLogin);
				latAndLonArrayList.add(latByLogin);
				//绑定国家/城市和经纬度的关系
				if(countryCnByLogin.indexOf("中国")>=0){
					geoCoordMap.put(countryEnByLogin, latAndLonArrayList.toArray());
				}else{
					geoCoordMap.put(countryEnByLogin, latAndLonArrayList.toArray());
				}

				/**查询某段时间内某个国家/城市登录的数量(默认当前时间一天内)*/
				m.put("countryCn", countryCnByLogin);
				if(countryCnByLogin.indexOf("中国")>=0){
					m.put("loginCity", cityByLogin);
				}else{
					m.remove("loginCity");
				}
				Integer loginNumber=dstipLoginMapper.getLoginDstIpNumberBycountry(m);

				List<Map<String,Object>> numberist=new ArrayList<Map<String,Object>>();
				//国家/城市对应的数量
				Map<String, Object> numberMap = new HashMap<String, Object>();
				numberMap.put("name", countryEnByLogin);
				numberMap.put("value", loginNumber);
				numberist.add(numberMap);
				numberist.add(hangZhouMap);
				mmList.add(numberist);
			}
		}else{
			geoCoordMap.put("name", "Hangzhou");
		}
		
		//杭州登录用户数量
		Map<String,Object> hzLoginNumberMap=new HashMap<String, Object>();
		hzLoginNumberMap.put("countryCn", "中国");
		hzLoginNumberMap.put("loginCity", "Hangzhou");
		if(null!=m.get("nowTime")){
			hzLoginNumberMap.put("nowTime", "nowTime");
		}
		if(null!=m.get("minTime") || null!=m.get("maxTime")){
			hzLoginNumberMap.put("minTime", m.get("minTime"));
			hzLoginNumberMap.put("maxTime", m.get("maxTime"));
		}
		Integer hzLoginNumber=dstipLoginMapper.getLoginDstIpNumberBycountry(hzLoginNumberMap);
		
		Map<String,Object> resultMap=new HashMap<String, Object>();
		resultMap.put("geoCoord", geoCoordMap);
		resultMap.put("mm", mmList);
		resultMap.put("hostNumber", dstipLoginMapper.getHostNumber());
		resultMap.put("onlineUser", dstipLoginMapper.getNowLoginIpNumber());
		resultMap.put("newIPList", dstipLoginMapper.getNewLoginIPList());
		resultMap.put("hzNumber", hzLoginNumber);
		return resultMap;
	}

}

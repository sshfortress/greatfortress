package com.sshfortress.service.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshfortress.common.beans.SimpleAreaDo;
import com.sshfortress.common.model.GroupDto;
import com.sshfortress.common.model.SysAreaDto;
import com.sshfortress.common.model.SysAreaGroupDto;
import com.sshfortress.dao.system.mapper.SysAreaGroupMapper;
import com.sshfortress.dao.system.mapper.SysAreaMapper;

@Service("areaService")
public class AreaService {
	@Autowired
	private SysAreaMapper    sysAreaMapper;
	
	@Autowired
	private SysAreaGroupMapper    sysAreaGroupMapper;
	
	public List<SimpleAreaDo> getSimpleAreas(long areId) {

		List<SimpleAreaDo> simpleAreas = new ArrayList<SimpleAreaDo>();

		SimpleAreaDo simpleArea = sysAreaMapper.selectSimplAreaByPrimaryKey(areId);

		if (null != simpleArea) {
			Map<String,Object> params = new HashMap<>();
			params.put("leftId", simpleArea.getLeftId());
			params.put("rightId", simpleArea.getRightId());
			params.put("nlevel", simpleArea.getNlevel());
			SimpleAreaDo simpleCity = sysAreaMapper.selectParantSimpleArea(params);

			if (null != simpleCity) {

				
				Map<String,Object> cityParams = new HashMap<>();
				params.put("leftId", simpleCity.getLeftId());
				params.put("rightId", simpleCity.getRightId());
				params.put("nlevel", simpleCity.getNlevel());
				SimpleAreaDo simpleProvince = sysAreaMapper.selectParantSimpleArea(cityParams);
				if (null != simpleProvince) {
					simpleAreas.add(simpleProvince);
				}
				simpleAreas.add(simpleCity);
			}
			simpleAreas.add(simpleArea);
		}
		return simpleAreas;
	}
	
	
	/**
	 * <p class="detail">
	 * 功能：得到分组区域的情况
	 * </p>
	 * @param areId
	 * @return
	 */
	public GroupDto getAreaGroup() {
		GroupDto l= new GroupDto();     
		l=sysAreaGroupMapper.selectAreaGroup();
		return l;
	}
	
	
	public Map<String,List<Map<String,Object>>> getAreaGroupNew() {
		
		//List<Map<String,List<Map<String,Object>>>> jg=new ArrayList<Map<String,List<Map<String,Object>>>>();
		Map<String,List<Map<String,Object>>> r=new  HashMap<String,List<Map<String,Object>>>();
		List<Map<String,Object>> l=sysAreaGroupMapper.getAreaGroup();
		if(l!=null && !l.isEmpty()){
			for(int i=0;i<l.size();i++){
				
				
				List<Map<String,Object>> d=new ArrayList<Map<String,Object>>();
				String[] pid=l.get(i).get("provinceId").toString().split(";");
				String[] pns=l.get(i).get("provinceNames").toString().split(";");
			    if(pid.length==pns.length){
				for(int j=0;j<pid.length;j++){
				
			    Map<String,Object> m=new HashMap<String,Object>();
			    //m.put(pid[j], pns[j]);
			    m.put("provinceId", pid[j]);
			    m.put("provinceName", pns[j]);
			    d.add(m);
			    }
			    }
			    r.put( l.get(i).get("groupName").toString(), d);
			    //jg.add(r);
			}
		}
		return r;
	}
	
	
	
	
public List<GroupDto> getAreaGroupWeb() {
	     
	List<GroupDto> r=new ArrayList<GroupDto>();
		//List<Map<String,List<Map<String,Object>>>> jg=new ArrayList<Map<String,List<Map<String,Object>>>>();
		//Map<String,List<Map<String,Object>>> r=new  HashMap<String,List<Map<String,Object>>>();
		List<Map<String,Object>> l=sysAreaGroupMapper.getAreaGroup();
		if(l!=null && !l.isEmpty()){
			for(int i=0;i<l.size();i++){
				
				GroupDto gd=new GroupDto();
//				List<Map<String,Object>> d=new ArrayList<Map<String,Object>>();
				List<SysAreaDto> sadlist=new ArrayList<SysAreaDto>();
				String[] pid=l.get(i).get("provinceId").toString().split(";");
				String[] pns=l.get(i).get("provinceNames").toString().split(";");
			    if(pid.length==pns.length){
				for(int j=0;j<pid.length;j++){
				
//			    Map<String,Object> m=new HashMap<String,Object>();
//			    
//			    m.put("provinceId", pid[j]);
//			    m.put("provinceName", pns[j]);
//			    d.add(m);
			    
			    SysAreaDto sad=new SysAreaDto();
			    sad.setCode(pid[j]);
			    sad.setId(Long.parseLong(pid[j].toString()));
			    sad.setName(pns[j]);
			    sadlist.add(sad);
			    
			  
			    }

			    }
				 gd.setGroupName(l.get(i).get("groupName").toString());
				 gd.setId(Long.parseLong( l.get(i).get("id").toString()));
				 gd.setSad(sadlist);
				 r.add(gd);
			    //r.put( l.get(i).get("groupName").toString(), d);
			    //jg.add(r);
			}
		}
		return r;
	}
	
	/**
	 * <p class="detail">
	 * 功能：得到全部城市的下拉列表
	 * </p>
	 * @param areId
	 * @return
	 */
	public List<Map<String,Object>> getAreaByCity(Map<String,Object> m) {
		List<Map<String,Object>> l=sysAreaMapper.selectAreaByCity(m);
		return l;
	}
	
}

/**
 * 
 */
package com.sshfortress.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshfortress.common.beans.SimpleAreaDo;
import com.sshfortress.common.beans.SysArea;
import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.model.SysAreaModel;
import com.sshfortress.common.util.ResponseObj;
import com.sshfortress.common.util.TreeDataUtil;
import com.sshfortress.dao.system.mapper.SysAreaMapper;
import com.sshfortress.service.system.AreaService;

/** <p class="detail">
 * 功能：区域操作
 * </p>
 * @ClassName: AreaApp 
 * @version V1.0  
 */
@Controller
@RequestMapping(value = "/areaApp")
public class AreaApp {
	
    @Autowired
    private SysAreaMapper    sysAreaMapper;

    @Autowired
    private AreaService     areaService;

    
     /** <p class="detail">
     * 功能：区域回调
     * </p>
     * @param areId
     * @param request
     * @return    
     */
    @RequestMapping(value = "/initArea.do", produces = { "application/json;charset=utf-8" },method=RequestMethod.POST)
    @ResponseBody
    public String initArea(Long areId, HttpServletRequest request) {
        if(areId==null)
        {
        	 ResponseObj ajaxResponseObj = new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(),
        	            ViewShowEnums.ERROR_FAILED.getDetail());
        	 return ajaxResponseObj.toAppJson();
        }
        List<SimpleAreaDo> simpleAreas = areaService.getSimpleAreas(areId);
        ResponseObj ajaxResponseObj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
            ViewShowEnums.INFO_SUCCESS.getDetail(), simpleAreas);
        return ajaxResponseObj.toAppJson();

    }
    
     /** <p class="detail">
     * 功能：获取子区域
     * </p>
     * @param request
     * @param nlevel
     * @param parentId
     * @return    
     */
    @RequestMapping(value = "/getChildrenArea.do", produces = { "application/json;charset=utf-8" })
    @ResponseBody
    public String getChildrenArea(HttpServletRequest request, Integer nlevel, Long parentId) {

        if (null == nlevel) {
            return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "请选择要查询的地区级?")
                .toAppJson();
        }

        if (null != parentId) {
            SimpleAreaDo simpleParen = sysAreaMapper.selectSimplAreaByPrimaryKey(parentId);
            
            Map<String,Object> params = new HashMap<>();
			params.put("leftId",simpleParen.getLeftId());
			params.put("rightId", simpleParen.getRightId());
			params.put("nlevel",nlevel);
            List<SimpleAreaDo> areas = sysAreaMapper.selectChildren(params);

            return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
                ViewShowEnums.INFO_SUCCESS.getDetail(), areas).toAppJson();
        }
        Map<String,Object> params = new HashMap<>();
        params.put("nlevel",nlevel);
        List<SimpleAreaDo> areas = sysAreaMapper.selectChildren(params);
        return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
            ViewShowEnums.INFO_SUCCESS.getDetail(), areas).toAppJson();
    }
    
    
     /** <p class="detail">
     * 功能：根据地区的父节点查询地区子节点
     * </p>
     * @param request
     * @param id
     * @return    
     */
    @RequestMapping(value = "/getAreaListByParamId.do", produces = { "application/json;charset=utf-8" })
    @ResponseBody
    public String getAreaListByParamId( HttpServletRequest request, Long id) {
    	if(id==null)
    	{
    		 ResponseObj ajaxResponseObj = new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(),"父节点ID不能为空?");
    		 ajaxResponseObj.toAppJson();
    	}
        SysArea area = sysAreaMapper.selectByPrimaryKey(id);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("nlevel", area.getNlevel() + 1);
        List<SysArea> areaList = sysAreaMapper.selectListByParantId(params);
        ResponseObj ajaxResponseObj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
            ViewShowEnums.INFO_SUCCESS.getDetail(), areaList);
        return ajaxResponseObj.toAppJson();
    }
    
    
     /** <p class="detail">
     * 功能：返回树形结构的地区信息
     * </p>
     * @param request
     * @param response
     * @return    
     */
    @RequestMapping(value = "/getTreeChildrenArea.do", produces = { "application/json;charset=utf-8" })
    @ResponseBody
    public String getTreeChildrenArea(HttpServletRequest request, HttpServletResponse response) {
        List<SysAreaModel> areasList = sysAreaMapper.selectAllArea();
        StringBuffer result = new StringBuffer();
        result.append("[");
        TreeDataUtil.getAppTreeJson(result, TreeDataUtil.convertDataList(areasList, "getAname"));
        result.append("]");
        ResponseObj ajaxResponseObj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
            ViewShowEnums.INFO_SUCCESS.getDetail(), result);
        return ajaxResponseObj.toWebDataString(request, response);
    }
    
    
    /** <p class="detail">
     * 功能：根据地区的三级联动的全部树形数据
     * </p>
     * @param request
     * @param id
     * @return    
     */
//    @RequestMapping(value = "/getAreaLinkList.do", produces = { "application/json;charset=utf-8" })
//    @ResponseBody
//    public String getAreaLinkList( HttpServletRequest request) {
//    	
//    	Long id=1l;
//        SysArea area = sysAreaMapper.selectByPrimaryKey(id);
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("id", id);
//        params.put("nlevel", area.getNlevel() + 1);
//        List<SysArea> areaList = sysAreaMapper.selectListByParantId(params);
//        
//        List<ProvinceList> sheng=new ArrayList<ProvinceList>();
//        
//        for(int k=0;k<areaList.size();k++){
//        	
//        	//省
//        	ProvinceList pl=new ProvinceList();
//        	pl.setAname(areaList.get(k).getAname());
//        	pl.setId(areaList.get(k).getId());
//        	pl.setNlevel(areaList.get(k).getNlevel());
//        	
//        	params.put("id", areaList.get(k).getId());
//        	params.put("nlevel", areaList.get(k).getNlevel()+1);
//        	List<SysArea> areaCityList=sysAreaMapper.selectListByParantId(params);
//        	List<CityList> chengs=new ArrayList<CityList>(); //获取城市
//        	
//        	for(int n=0;n<areaCityList.size();n++){
//            	//市
//            	CityList cl=new CityList();
//            	cl.setAname(areaCityList.get(n).getAname());
//            	cl.setId(areaCityList.get(n).getId());
//            	cl.setNlevel(areaCityList.get(n).getNlevel());
//            	List<CountyList> quxian=new ArrayList<CountyList>(); //获取县区
//            	params.put("id", areaCityList.get(n).getId());
//            	params.put("nlevel", areaCityList.get(n).getNlevel()+1);            	
//            	List<SysArea> countyList=sysAreaMapper.selectListByParantId(params);
//            	for(int r=0;r<countyList.size();r++){
//            		//县区 
//            		CountyList ctl=new CountyList();
//            		ctl.setAname(countyList.get(r).getAname());
//            	    ctl.setId(countyList.get(r).getId());
//            	    ctl.setNlevel(countyList.get(r).getNlevel());
//            	    quxian.add(ctl);
//            	}  
//            	cl.setCounty(quxian);
//            	chengs.add(cl);
//            }
//            pl.setCity(chengs);
//            sheng.add(pl);
//        }
//        ResponseObj ajaxResponseObj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
//            ViewShowEnums.INFO_SUCCESS.getDetail(), sheng);
//        return ajaxResponseObj.toAppJson();
//    }
    
    /**
     * <p class="detail">
     * 功能：根据城市名称 获取全部的区县情况
     * </p>
     * @param request
     * @param areaName
     * @return
     */
    @RequestMapping(value = "/getCountyList.do", produces = { "application/json;charset=utf-8" })
    @ResponseBody
    public String getCountyList( HttpServletRequest request, String areaName) {
    	 ResponseObj ajaxResponseObj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
    	            ViewShowEnums.INFO_SUCCESS.getDetail());
    	Map<String, Object> m = new HashMap<String, Object>();
        m.put("searchText", areaName);
    	List<Map<String,Object>> l=sysAreaMapper.selectAreaByCity(m);
        if(l.isEmpty()){
        	ajaxResponseObj.setShowMessage(ViewShowEnums.ERROR_FAILED.getDetail());
        	ajaxResponseObj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
            return ajaxResponseObj.toAppJson();
        }
    	
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", l.get(0).get("id"));
        params.put("nlevel", 4);
        List<SysArea> areaList = sysAreaMapper.selectListByParantId(params);
        List<Map<String,Object>> rl=new ArrayList<Map<String,Object>>();
        if(areaList!=null && !areaList.isEmpty()){
        for(int k=0;k<areaList.size();k++){
        	Map<String,Object> cl=new HashMap<String,Object>();
        	cl.put("id", areaList.get(k).getId());
        	cl.put("countyName", areaList.get(k).getAname());        	
            rl.add(cl);
        }
        }
        ajaxResponseObj.setData(rl);
        return ajaxResponseObj.toAppJson();
    }
    
}

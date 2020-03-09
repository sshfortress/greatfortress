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

import com.sshfortress.common.model.GroupDto;

import com.sshfortress.common.model.SysAreaGroupDto;
import com.sshfortress.common.model.SysAreaModel;
import com.sshfortress.common.util.ResponseObj;
import com.sshfortress.common.util.TreeDataUtil;
import com.sshfortress.dao.system.mapper.SysAreaMapper;
import com.sshfortress.service.system.AreaService;

@Controller
@RequestMapping(value = "/areaWeb")
public class AreaWeb {

    @Autowired
    private SysAreaMapper    sysAreaMapper;

    @Autowired
    private AreaService     areaService;

    
     /** <p class="detail">
     * 功能：区域
     * </p>
     * @param areId
     * @param request
     * @return    
     */
    @RequestMapping(value = "/initArea.web", produces = { "application/json;charset=utf-8" },method=RequestMethod.POST)
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
    @RequestMapping(value = "/getChildrenArea.web", produces = { "application/json;charset=utf-8" })
    @ResponseBody
    public String getChildrenArea(HttpServletRequest request, Integer nlevel, Long parentId) {

        if (null == nlevel) {
            return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "请选择要查询的地区级")
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
    		 ResponseObj ajaxResponseObj = new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(),"父节点ID不能为空");
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
    @RequestMapping(value = "/getTreeChildrenArea.web", produces = { "application/json;charset=utf-8" })
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
    
    
    /**
     * <p class="detail">
     * 功能：得到分组的区域信息
     * </p>
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getAreaGroup.web", produces = { "application/json;charset=utf-8" })
    @ResponseBody
    public ResponseObj getAreaGroup(HttpServletRequest request, HttpServletResponse response) {
    	List<GroupDto> areasList=new ArrayList<GroupDto>();
    	areasList =areaService.getAreaGroupWeb();
    	//Map<String,List<Map<String,Object>>> areasList=areaService.getAreaGroupNew();
        ResponseObj ajaxResponseObj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),ViewShowEnums.INFO_SUCCESS.getDetail());
        ajaxResponseObj.setData(areasList);
        return ajaxResponseObj;
    }
    
    
    /**
     * <p class="detail">
     * 功能：获取全部的城市列表
     * 可以通过名称 和 拼音去查询
     * </p>
     * @param seachText
     * @param request
     * @param response
     * @return
     */
    
    @RequestMapping(value = "/getAreaCity.do", produces = { "application/json;charset=utf-8" })
    @ResponseBody
    public ResponseObj getAreaCity(String searchText,HttpServletRequest request, HttpServletResponse response) {
    	ResponseObj ajaxResponseObj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),ViewShowEnums.INFO_SUCCESS.getDetail());
        Map<String,Object> m=new HashMap<String,Object>();
        m.put("searchText", searchText);
        List<Map<String,Object>> l=areaService.getAreaByCity(m);
    	ajaxResponseObj.setData(l);
        return ajaxResponseObj;
    }
    
    
    
   
    
}

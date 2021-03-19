//
//package com.sshfortress.controller.user;
//
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.sshfortress.common.beans.RegCodeForm;
//import com.sshfortress.common.beans.SysUser;
//import com.sshfortress.common.enums.RegCodeType;
//import com.sshfortress.common.enums.UserNameType;
//import com.sshfortress.common.enums.UserStatus;
//import com.sshfortress.common.enums.ViewShowEnums;
//import com.sshfortress.common.util.OperationContextHolder;
//import com.sshfortress.common.util.ResponseObj;
//import com.sshfortress.common.util.UserNameTypeUtils;
//import com.sshfortress.common.util.UserTypesUtil;
//import com.sshfortress.common.util.ValidateUtil;
//import com.sshfortress.service.helper.SendRegCodeHelper;
//import com.sshfortress.service.system.SysUserService;
//
///** <p class="detail">
// * 功能：这里写类描述
// * </p>
// * @ClassName: RegCodeApp 
// * @version V1.0  
// */
//@Controller
//@Scope(value = "prototype")
//@RequestMapping(value = "/userapp")
//public class RegCodeApp {
//
//	@Autowired
//	SysUserService sysUserService;
//    
//    @Autowired
//    private SendRegCodeHelper sendRegCodeHelper;
//
//
//    
//     /** <p class="detail">
//     * 功能：获取注册验证码
//     * </p>
//     * @param regCodeForm
//     * @param result
//     * @return    
//     */
//    @RequestMapping(value = "/getRegistRegCode.do", produces = { "application/json;charset=UTF-8" })
//    @ResponseBody
//    public String getRegistRegCode(@Valid RegCodeForm regCodeForm, BindingResult result) {
//
//        if (result.hasErrors()) {
//            ResponseObj appResponseObj = new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(),
//                result.getAllErrors().get(0).getDefaultMessage());
//
//            return appResponseObj.toAppJson();
//        }
//
//        UserNameType userNameType = UserNameTypeUtils.getTypeByUserName(regCodeForm.getUserName());
//        ResponseObj appResponseObj = new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(),
//            "账号为手机号码或邮箱");
//
//        if (null == userNameType) {
//            return appResponseObj.toAppJson();
//        }
//
//        // 验证账号 或绑定号
//        List<SysUser> list = sysUserService.queryByUserName(regCodeForm.getUserName(),  UserTypesUtil.getAppuserTypes(),null);
//        if (!CollectionUtils.isEmpty(list)) {
//            return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "该账号已被注册").toAppJson();
//        }
//
//        return sendRegCodeHelper.sendRegCode(userNameType,
//            regCodeForm.getUserName(), RegCodeType.REGIST).toAppJson();
//
//    }
//    
//     /** <p class="detail">
//     * 功能：获取找回密码验证码
//     * </p>
//     * @param regCodeForm
//     * @param result
//     * @return    
//     */
//    @RequestMapping(value = "/getPasswordRegCode.do", produces = { "application/json;charset=UTF-8" })
//    @ResponseBody
//    public String getPasswordRegCode(@Valid RegCodeForm regCodeForm, BindingResult result) {
//
//        if (result.hasErrors()) {
//            ResponseObj appResponseObj = new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(),
//                result.getAllErrors().get(0).getDefaultMessage());
//
//            return appResponseObj.toAppJson();
//        }
//
//        UserNameType userNameType = UserNameTypeUtils.getTypeByUserName(regCodeForm.getUserName());
//
//        ResponseObj appResponseObj = new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(),
//            "账号为手机号码或邮箱");
//
//        if (null == userNameType) {
//            return appResponseObj.toAppJson();
//        }
//
//        // 找回密码账号必须存在
//
//        List<SysUser> list = sysUserService.queryByUserName(regCodeForm.getUserName(),  UserTypesUtil.getAppuserTypes(),UserStatus.OPEN.getCode());
//        
//        if (CollectionUtils.isEmpty(list)) {
//            return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "账号不存在").toAppJson();
//        }
//
//        return sendRegCodeHelper.sendRegCode(userNameType, 
//            regCodeForm.getUserName(), RegCodeType.BACK_PASSWORD).toAppJson();
//
//    }
//
//    
//    /** <p class="detail">
//     * 功能：获取快捷登录验证码
//     * </p>
//     * @param regCodeForm
//     * @param result
//     * @return    
//     */
//    @RequestMapping(value = "/fastLoginRegCode.do", produces = { "application/json;charset=UTF-8" })
//    @ResponseBody
//    public String fastLoginRegCode(@Valid RegCodeForm regCodeForm, BindingResult result) {
//
//        if (result.hasErrors()) {
//            ResponseObj appResponseObj = new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(),
//                result.getAllErrors().get(0).getDefaultMessage());
//
//            return appResponseObj.toAppJson();
//        }
//
//        UserNameType userNameType = UserNameTypeUtils.getTypeByUserName(regCodeForm.getUserName());
//
//        ResponseObj appResponseObj = new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(),
//            "账号为手机号码或邮箱");
//
//        if (null == userNameType) {
//            return appResponseObj.toAppJson();
//        }
//
//        // 找回密码账号必须存在
//
//        List<SysUser> list = sysUserService.queryByUserName(regCodeForm.getUserName(),  UserTypesUtil.getAppuserTypes(),UserStatus.OPEN.getCode());
//        
//        if (CollectionUtils.isEmpty(list)) {
//            return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "账号不存在").toAppJson();
//        }
//
//        return sendRegCodeHelper.sendRegCode(userNameType, 
//            regCodeForm.getUserName(), RegCodeType.BACK_PASSWORD).toAppJson();
//
//    }
//    
//    /**
//     * <p class="detail">
//     * 功能：生成验证码的方式
//     * </p>
//     * @param regCodeForm
//     * @param result
//     * @param type
//     * @return
//     */
//    
//    @RequestMapping(value = "/sendRegCode.do", produces = { "application/json;charset=UTF-8" })
//    @ResponseBody
//    public String sendRegCode(@Valid RegCodeForm regCodeForm, BindingResult result,String type ) {
//
//        if (result.hasErrors()) {
//            ResponseObj appResponseObj = new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(),
//                result.getAllErrors().get(0).getDefaultMessage());
//
//            return appResponseObj.toAppJson();
//        }
//
//        UserNameType userNameType = UserNameTypeUtils.getTypeByUserName(regCodeForm.getUserName());
//
//        ResponseObj appResponseObj = new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(),
//            "账号为手机号码或邮箱");
//
//        if (null == userNameType) {
//            return appResponseObj.toAppJson();
//        }
//        return sendRegCodeHelper.sendRegCode(userNameType, 
//            regCodeForm.getUserName(), RegCodeType.BACK_PASSWORD).toAppJson();
//
//    }
//    
//    
//     /** <p class="detail">
//     * 功能：登录后获取绑定手机号码验证
//     * </p>
//     * @param phone
//     * @return    
//     */
//    @RequestMapping(value = "/bindPhoneRegCode.app", produces = { "application/json;charset=UTF-8" }, method = { RequestMethod.POST })
//    @ResponseBody
//    public String bindPhoneRegCode(String phone) {
//
//        if (!ValidateUtil.isValidMobile(phone)) {
//            return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "手机号码错误").toAppJson();
//        }
//
//        if (StringUtils.equals(phone, OperationContextHolder.getUserName())) {
//            return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "该手机您已绑定").toAppJson();
//        }
//
//        // 验证手机号码是否已经被绑定过
//        
//        List<SysUser> list = sysUserService.queryByUserName(phone,  UserTypesUtil.getAppuserTypes(),null);
//
//
//        if (!CollectionUtils.isEmpty(list)) {
//            return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "该手机已被绑定").toAppJson();
//        }
//
//        return sendRegCodeHelper.sendRegCode(UserNameType.PHONE, phone, RegCodeType.BIND_PHONE).toAppJson();
//
//    }
//
//    
//     /** <p class="detail">
//     * 功能：登录后获取绑定email验证
//     * </p>
//     * @param email
//     * @return    
//     */
//    @RequestMapping(value = "/bindEmailRegCode.app", produces = { "application/json;charset=UTF-8" }, method = { RequestMethod.POST })
//    @ResponseBody
//    public String bindEmailRegCode(String email) {
//
//        if (!ValidateUtil.isValidEmail(email)) {
//            return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "email格式错误").toAppJson();
//        }
//
//        if (StringUtils.equals(email, OperationContextHolder.getUserName())) {
//            return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "该email您已绑定")
//                .toAppJson();
//        }
//
//        // 验证email是否已经被绑定过
//        
//        List<SysUser> list = sysUserService.queryByUserName(email,  UserTypesUtil.getAppuserTypes(),null);
//        if (!CollectionUtils.isEmpty(list)) {
//            return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "该email已被绑定")
//                .toAppJson();
//        }
//        return sendRegCodeHelper.sendRegCode(UserNameType.EMAIL, email, RegCodeType.BIND_EMAIL).toAppJson();
//
//    }
//
//}

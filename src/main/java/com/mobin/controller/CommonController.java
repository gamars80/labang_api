package com.mobin.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobin.model.ApiResponse;
import com.mobin.model.AuthToken;
import com.mobin.model.CommonAppVO;
import com.mobin.service.CommonService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/common")
public class CommonController {
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private MessageSource msg;
	
	/**
	  * @Method Name : sellerLogin
	  * @작성일 : 2019. 2. 18.
	  * @작성자 : user
	  * @변경이력 : 
	  * @Method 설명 :
	  * @param commonAppVO
	  * @param req
	  * @param locale
	  * @return
	*/
	@SuppressWarnings("unchecked")
	@GetMapping("/get/appinfo")
    public ApiResponse<AuthToken> sellerLogin(
    		@RequestBody CommonAppVO commonAppVO,
    		HttpServletRequest req,
    		Locale locale
    	){
		CommonAppVO commpnAppInfo = commonService.selectAppVersionInfo(commonAppVO.getDevice_type());
		
		JSONObject appInfo = new JSONObject();
		appInfo.put("app_v", commpnAppInfo.getApp_v());
		
		
		return new ApiResponse<>(HttpStatus.OK.value(),msg.getMessage("get.success",null, locale)
				,appInfo);
		
    }
}

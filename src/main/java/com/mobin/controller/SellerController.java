package com.mobin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobin.common.CodeConstants;
import com.mobin.config.JwtTokenUtil;
import com.mobin.model.ApiResponse;
import com.mobin.model.AuthToken;
import com.mobin.model.BroadcastVO;
import com.mobin.model.LoginVO;
import com.mobin.model.SellerPushVO;
import com.mobin.model.SellerSaleInfoVO;
import com.mobin.model.SellerVO;
import com.mobin.service.SellerService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/seller")
public class SellerController {
	
	@Autowired
	private MessageSource msg;
 
    @Autowired
    private SellerService sellerService;
    
    @Autowired
	private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    private AuthenticationManager authenticationManager;
	
    @Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	/**
	  * @Method Name : sellerLogin
	  * @작성일 : 2019. 2. 11.
	  * @작성자 : user
	  * @변경이력 : 
	  * @Method 설명 : 셀러 로그인
	  * @param loginVO
	  * @param req
	  * @param locale
	  * @return
	*/
	@GetMapping("/get/login")
    public ApiResponse<AuthToken> sellerLogin(
    		//@ModelAttribute("loginVO")LoginVO loginVO,
    		@RequestBody LoginVO loginVO,
    		HttpServletRequest req,
    		Locale locale
    	){
		
		AuthToken loginInfo = null;
		SellerVO sellerVO = new SellerVO();
		sellerVO.setId(loginVO.getSeller_id());
		sellerVO.setPasswd(loginVO.getSeller_pwd());
		
	
		
		loginInfo = sellerService.sellerLogin(sellerVO);
		if(loginInfo ==null) {
			return new ApiResponse<>(CodeConstants.NOT_LOGIN,msg.getMessage("login.fail",null, locale),loginInfo);
		}else {
			String pwd = loginInfo.getPasswd();
			if(!bcryptEncoder.matches(loginVO.getSeller_pwd(), pwd)) {
				return new ApiResponse<>(CodeConstants.NOT_LOGIN_PWD,msg.getMessage("login.notmatch.pwd",null, locale),loginInfo);
			}
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginVO.getSeller_id(), loginVO.getSeller_pwd()));
			
			sellerVO.setUid(loginInfo.getSid());
			String token = jwtTokenUtil.generateToken(sellerVO);
			loginInfo.setToken(token);
			

		}
		
		return new ApiResponse<>(HttpStatus.OK.value(),msg.getMessage("login.success",null, locale),loginInfo);
		
    }
	
	/**
	  * @Method Name : updateAgreeFlag
	  * @작성일 : 2019. 2. 11.
	  * @작성자 : user
	  * @변경이력 : 
	  * @Method 설명 :셀러 이용약관동의
	  * @param req
	  * @param locale
	  * @return
	*/
	@PutMapping("/put/agreeflag")
    public ApiResponse<LoginVO> updateAgreeFlag(

    		HttpServletRequest req,
    		Locale locale) {
		Long uid = (Long)req.getAttribute("sid");
		
		sellerService.updateAgreeFlag(uid);

        return new ApiResponse<>(HttpStatus.OK.value(), msg.getMessage("upd.success",null, locale),null);
    }
	
	/**
	  * @Method Name : updateSellerPasswd
	  * @작성일 : 2019. 2. 11.
	  * @작성자 : user
	  * @변경이력 : 
	  * @Method 설명 :최초로그인시 셀러 비번 변경
	  * @param req
	  * @param loginVO
	  * @param locale
	  * @return
	*/
	@PutMapping("/put/password")
    public ApiResponse<LoginVO> updateSellerPasswd(
    		HttpServletRequest req,
    		@RequestBody LoginVO loginVO,
    		Locale locale) {
		Long uid = (Long)req.getAttribute("sid");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("passwd", bcryptEncoder.encode(loginVO.getSeller_pwd()));
		
		sellerService.updateSellerPasswd(map);

        return new ApiResponse<>(HttpStatus.OK.value(), msg.getMessage("upd.success",null, locale),null);
    }
	
	
	/**
	  * @Method Name : selectSellerSaleInfo
	  * @작성일 : 2019. 2. 13.
	  * @작성자 : user
	  * @변경이력 : 
	  * @Method 설명 : 햄버거 메뉴 클릭시 이달의 토탈 월 판매액/주문수
	  * @param req
	  * @param locale
	  * @return
	*/
	@GetMapping("/get/saleinfo")
    public ApiResponse<SellerSaleInfoVO> selectSellerSaleInfo(
		HttpServletRequest req,
		Locale locale
	){
		SellerSaleInfoVO saleInfo = null;
		Long uid = (Long)req.getAttribute("sid");
		saleInfo = sellerService.selectSellerSaleInfo(uid);
	
		return new ApiResponse<>(HttpStatus.OK.value(),msg.getMessage("get.success",null, locale),saleInfo);
	
	}
	
	/**
	  * @Method Name : updateSellerDeviceToken
	  * @작성일 : 2019. 2. 13.
	  * @작성자 : user
	  * @변경이력 : 
	  * @Method 설명 : 셀라 푸시 디바이토큰값 업데이트
	  * @param sellerVO
	  * @param req
	  * @param locale
	  * @return
	*/
	@PutMapping("/put/apptoken")
    public ApiResponse<String> updateSellerDeviceToken(
    		@RequestBody SellerVO sellerVO,
    		HttpServletRequest req,
    		Locale locale) {
		Long uid = (Long)req.getAttribute("sid");
		sellerVO.setUid(uid);
		sellerService.updateAgreeFlag(uid);

        return new ApiResponse<>(HttpStatus.OK.value(), msg.getMessage("upd.success",null, locale),null);
    }
	
	
	/**
	  * @Method Name : myActivityList
	  * @작성일 : 2019. 2. 13.
	  * @작성자 : user
	  * @변경이력 : 
	  * @Method 설명 : 셀러용 나의 액티비티 리스트
	  * @param locale
	  * @param req
	  * @return
	*/
	@GetMapping("/get/list/activity")
    public ApiResponse<List<BroadcastVO>> myActivityList(
    		Locale locale,
    		HttpServletRequest req){
    	List<SellerPushVO> list = null;
    	Long uid = (Long)req.getAttribute("sid");
		
		
		list = sellerService.selectMyActivityList(uid);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("plist", list);
        return new ApiResponse<>(HttpStatus.OK.value(), msg.getMessage("get.success",null, locale),resultMap);
    }
	
	/**
	  * @Method Name : updateSellerActivityFlag
	  * @작성일 : 2019. 2. 13.
	  * @작성자 : user
	  * @변경이력 : 
	  * @Method 설명 : 셀러용 액티비티 읽음 상태 업데이트
	  * @param pushVO
	  * @param req
	  * @param locale
	  * @return
	*/
	@PutMapping("/put/activity/flag")
    public ApiResponse<String> updateSellerActivityFlag(
    		@RequestBody SellerPushVO pushVO,
    		HttpServletRequest req,
    		Locale locale) {
		Long uid = (Long)req.getAttribute("sid");
		pushVO.setUid(uid);
		sellerService.updateActivityFlag(pushVO);

        return new ApiResponse<>(HttpStatus.OK.value(), msg.getMessage("upd.success",null, locale),null);
    }

}

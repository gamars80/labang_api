package com.mobin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobin.model.ApiResponse;
import com.mobin.model.BroadcastCehckVO;
import com.mobin.model.BroadcastDetailVO;
import com.mobin.model.BroadcastHistVO;
import com.mobin.model.BroadcastReviewVO;
import com.mobin.model.BroadcastSalesVO;
import com.mobin.model.BroadcastVO;
import com.mobin.model.PageVO;
import com.mobin.model.UserBroadcastVO;
import com.mobin.service.BroadcastService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/broadcast")
public class BraodcastController {
	@Autowired
	private MessageSource msg;
 
    @Autowired
    private BroadcastService broadcastService;
    
    /**
      * @Method Name : myBroadcastList
      * @작성일 : 2019. 2. 12.
      * @작성자 : user
      * @변경이력 : 
      * @Method 설명 : 나의 브로드캐스트 목록 (방송중,방송예정)
      * @param broadcastVO
      * @param locale
      * @param req
      * @return
    */
    @GetMapping("/get/list/my")
    public ApiResponse<List<BroadcastVO>> myBroadcastList(
    		@RequestBody PageVO pageVO,
    		Locale locale,
    		HttpServletRequest req){
    	List<BroadcastVO> list = null;
		Long uid = (Long)req.getAttribute("sid");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("start_p", (pageVO.getStart_p()-1)*pageVO.getCount());
		map.put("end_p", pageVO.getCount());
		list = broadcastService.selectMyBroadcastList(map);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("blist", list);
        return new ApiResponse<>(HttpStatus.OK.value(), msg.getMessage("get.success",null, locale),resultMap);
    }
    

	/**
	  * @Method Name : myBroadcastCheck
	  * @작성일 : 2019. 2. 12.
	  * @작성자 : user
	  * @변경이력 : 
	  * @Method 설명 : 셀러가 본인 방송 인증하기
	  * @param vo
	  * @param locale
	  * @param req
	  * @return
	*/
	@PostMapping("/post/checkkey")
    public ApiResponse<BroadcastDetailVO> myBroadcastCheck(
    		@RequestBody BroadcastCehckVO vo,
    		Locale locale,
    		HttpServletRequest req){
		
		Long uid = (Long)req.getAttribute("sid");
		
        return broadcastService.checkMyBroadcast(locale, uid, vo.getBroadcast_key(), vo.getBid());
    }
	
	/**
	  * @Method Name : broadcastDetail
	  * @작성일 : 2019. 2. 12.
	  * @작성자 : user
	  * @변경이력 : 
	  * @Method 설명 : 방송정보 상세
	  * @param bid
	  * @param locale
	  * @param req
	  * @return
	*/
	@GetMapping("/get/detail/{bid}")
    public ApiResponse<BroadcastDetailVO> broadcastDetail(
    		@PathVariable Long bid,
    		Locale locale,
    		HttpServletRequest req){
		
		return broadcastService.broadcastDetail(locale, bid);
    }
	
	
	
	
	/**
	  * @Method Name : myBroadcastHistoryList
	  * @작성일 : 2019. 2. 13.
	  * @작성자 : user
	  * @변경이력 : 
	  * @Method 설명 :셀러 나의 방송내역리스트
	  * @param pageVO
	  * @param locale
	  * @param req
	  * @return
	*/
	@GetMapping("/get/list/my/history")
    public ApiResponse<List<BroadcastHistVO>> myBroadcastHistoryList(
    		@RequestBody PageVO pageVO,
    		Locale locale,
    		HttpServletRequest req){
    	List<BroadcastHistVO> list = null;
		Long uid = (Long)req.getAttribute("sid");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("start_p", (pageVO.getStart_p()-1)*pageVO.getCount());
		map.put("end_p", pageVO.getCount());
		list = broadcastService.selectMyBroadcastHistList(map);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("blist", list);
        return new ApiResponse<>(HttpStatus.OK.value(), msg.getMessage("get.success",null, locale),resultMap);
    }
	
	
	/**
	  * @Method Name : reviewBroadcastList
	  * @작성일 : 2019. 2. 13.
	  * @작성자 : user
	  * @변경이력 : 
	  * @Method 설명 :방송내역에서 다시보기시 해당 상품
	  * @param bids
	  * @param locale
	  * @param req
	  * @return
	*/
	@GetMapping("/get/list/review/{bids}")
    public ApiResponse<BroadcastDetailVO> reviewBroadcastList(
    		@PathVariable String bids,
    		Locale locale,
    		HttpServletRequest req){
		
		List<BroadcastReviewVO> list = null;
		HashMap<String, Object> map  = new HashMap<String, Object>();
		map.put("bids", bids);
		list = broadcastService.selectReviewBroadcastList(map);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("reviewlist", list);
		
		return new ApiResponse<>(HttpStatus.OK.value(), msg.getMessage("get.success",null, locale),resultMap);
    }
	
	
	/**
	  * @Method Name : myBroadcastSalesList
	  * @작성일 : 2019. 2. 13.
	  * @작성자 : user
	  * @변경이력 : 
	  * @Method 설명 : 내 매출현황
	  * @param pageVO
	  * @param locale
	  * @param req
	  * @return
	*/
	@GetMapping("/get/list/my/sales")
    public ApiResponse<List<BroadcastSalesVO>> myBroadcastSalesList(
    		@RequestBody PageVO pageVO,
    		Locale locale,
    		HttpServletRequest req){
    	List<BroadcastSalesVO> list = null;
		Long uid = (Long)req.getAttribute("sid");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("start_p", (pageVO.getStart_p()-1)*pageVO.getCount());
		map.put("end_p", pageVO.getCount());
		list = broadcastService.selectMyBroadcastSalesList(map);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("blist", list);
        return new ApiResponse<>(HttpStatus.OK.value(), msg.getMessage("get.success",null, locale),resultMap);
    }
	
	
	
	/**
	  * @Method Name : todayBroadcast
	  * @작성일 : 2019. 2. 13.
	  * @작성자 : user
	  * @변경이력 : 
	  * @Method 설명 :
	  * @param pageVO
	  * @param locale
	  * @param req
	  * @return
	*/
	@GetMapping("/get/user/list/today")
   public ApiResponse<List<BroadcastVO>> todayBroadcast(
   		@RequestBody PageVO pageVO,
   		Locale locale,
   		HttpServletRequest req){
   	List<UserBroadcastVO> list = null;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start_p", (pageVO.getStart_p()-1)*pageVO.getCount());
		map.put("end_p", pageVO.getCount());
		list = broadcastService.selectUserBroadcastList(map);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("blist", list);
       return new ApiResponse<>(HttpStatus.OK.value(), msg.getMessage("get.success",null, locale),resultMap);
   }
	
	@GetMapping("/get/user/list/daily")
    public ApiResponse<List<BroadcastVO>> dailyBroadcast(
    		@RequestBody PageVO pageVO,
    		Locale locale,
    		HttpServletRequest req){
    	List<UserBroadcastVO> list = null;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start_p", (pageVO.getStart_p()-1)*pageVO.getCount());
		map.put("end_p", pageVO.getCount());
		map.put("ymd", pageVO.getYmd());
		list = broadcastService.selectUserBroadcastList(map);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("blist", list);
        return new ApiResponse<>(HttpStatus.OK.value(), msg.getMessage("get.success",null, locale),resultMap);
    }
}

package com.mobin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.mobin.model.ApiResponse;
import com.mobin.model.BroadcastDetailVO;
import com.mobin.model.BroadcastHistVO;
import com.mobin.model.BroadcastReviewVO;
import com.mobin.model.BroadcastSalesVO;
import com.mobin.model.BroadcastVO;
import com.mobin.model.UserBroadcastVO;

public interface BroadcastService {
	public List<BroadcastVO> selectMyBroadcastList(HashMap<String, Object> map);
	
	public List<BroadcastVO> selectMyBroadcastMonthlyList(Long uid);
	
	public ApiResponse<BroadcastDetailVO> checkMyBroadcast(Locale locale,Long uid,String key,Long bid);
	
	public ApiResponse<BroadcastDetailVO> broadcastDetail(Locale locale,Long bid);
	
	public List<BroadcastHistVO> selectMyBroadcastHistList(HashMap<String, Object> map);
	
	public List<BroadcastReviewVO> selectReviewBroadcastList(HashMap<String, Object> map);
	
	public List<BroadcastSalesVO> selectMyBroadcastSalesList(HashMap<String, Object> map);
	
	public List<UserBroadcastVO> selectUserBroadcastList(HashMap<String, Object> map);
	
	
	
}

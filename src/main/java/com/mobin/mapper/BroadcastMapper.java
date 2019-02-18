package com.mobin.mapper;

import java.util.HashMap;
import java.util.List;

import com.mobin.model.BroadcastDetailVO;
import com.mobin.model.BroadcastHistVO;
import com.mobin.model.BroadcastReviewVO;
import com.mobin.model.BroadcastSalesVO;
import com.mobin.model.BroadcastVO;
import com.mobin.model.UserBroadcastVO;

public interface BroadcastMapper {
	public List<BroadcastVO> selectMyBroadcastList(HashMap<String, Object> map);
	
	public List<BroadcastVO> selectMyBroadcastMonthlyList(Long uid);
	
	public int selectSellerBroKeyCnt(HashMap<String, Object> map);
	
	public int selectBroadcastCnt(Long bid);
	
	public BroadcastDetailVO selectBroadCastInfo(Long bid);
	
	public List<BroadcastHistVO> selectMyBroadcastHistList(HashMap<String, Object> map);
	
	public List<BroadcastReviewVO> selectReviewBroadcastList(HashMap<String, Object> map);
	
	public List<BroadcastSalesVO> selectMyBroadcastSalesList(HashMap<String, Object> map);
	
	public List<UserBroadcastVO> selectUserBroadcastList(HashMap<String, Object> map);
	
	
}

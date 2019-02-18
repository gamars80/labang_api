package com.mobin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobin.common.CodeConstants;
import com.mobin.common.util.StringUtil;
import com.mobin.mapper.BroadcastMapper;
import com.mobin.mapper2.WmOrderProductMapper;
import com.mobin.model.ApiResponse;
import com.mobin.model.BroadcastDetailVO;
import com.mobin.model.BroadcastHistVO;
import com.mobin.model.BroadcastReviewVO;
import com.mobin.model.BroadcastSalesVO;
import com.mobin.model.BroadcastVO;
import com.mobin.model.UserBroadcastVO;
import com.mobin.model.wisa.WmProductSalesVO;
import com.mobin.service.BroadcastService;

@Service(value = "broadcastService")
@Transactional(rollbackFor = { Exception.class }) 
public class BroadcastServiceImpl implements BroadcastService {
	
	@Autowired
	BroadcastMapper broadcastMapper;
	
	@Autowired
	WmOrderProductMapper wmOrderProductMapper;
	
	
	@Autowired
	private MessageSource msg;
	
	public List<BroadcastVO> selectMyBroadcastList(HashMap<String, Object> map) {
		List<BroadcastVO> list = new ArrayList<>();
		broadcastMapper.selectMyBroadcastList(map).iterator().forEachRemaining(list::add);
		return list;
	}
	
	public List<BroadcastVO> selectMyBroadcastMonthlyList(Long uid) {
		List<BroadcastVO> list = new ArrayList<>();
		broadcastMapper.selectMyBroadcastMonthlyList(uid).iterator().forEachRemaining(list::add);
		return list;
	}
	
	public ApiResponse<BroadcastDetailVO> checkMyBroadcast(Locale locale,Long uid,String key,Long bid) {
		BroadcastDetailVO detailVO = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("bro_key", key);
		
		int checkKey = broadcastMapper.selectSellerBroKeyCnt(map);
		if(checkKey  == 0) {
			return new ApiResponse<>(CodeConstants.NOT_BROADCAST_KEY, msg.getMessage("broadcast.not.key",null, locale),detailVO);
		}
		
		int checkBroadcast = broadcastMapper.selectBroadcastCnt(bid);
		if(checkBroadcast == 0) {
			return new ApiResponse<>(CodeConstants.NOT_BROADCAST_CASTING, msg.getMessage("broadcast.not.casting",null, locale),detailVO);	
		}
		
		detailVO = broadcastMapper.selectBroadCastInfo(bid);
		
		map = new HashMap<String, Object>();
		map.put("pid", detailVO.getPid());
		map.put("start", detailVO.getStartymd());
		map.put("end", detailVO.getEndymd());
		
		
		WmProductSalesVO salesVO = wmOrderProductMapper.selectProductSalesInfo(map);
		detailVO.setTotal_price(StringUtil.addComma(Integer.parseInt(salesVO.getTotal_price())));
		detailVO.setOrder_product(salesVO.getOrder_product());
		
		return new ApiResponse<>(HttpStatus.OK.value(), msg.getMessage("broadcast.auth.success",null, locale),detailVO);
	}
	
	
	public ApiResponse<BroadcastDetailVO> broadcastDetail(Locale locale,Long bid) {
		BroadcastDetailVO detailVO = null;
	
		
		
		int checkBroadcast = broadcastMapper.selectBroadcastCnt(bid);
		if(checkBroadcast == 0) {
			return new ApiResponse<>(CodeConstants.NOT_BROADCAST_CASTING, msg.getMessage("broadcast.not.casting",null, locale),detailVO);	
		}
		
		detailVO = broadcastMapper.selectBroadCastInfo(bid);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = new HashMap<String, Object>();
		map.put("pid", detailVO.getPid());
		map.put("start", detailVO.getStartymd());
		map.put("end", detailVO.getEndymd());
		
		
		WmProductSalesVO salesVO = wmOrderProductMapper.selectProductSalesInfo(map);
		detailVO.setTotal_price(StringUtil.addComma(Integer.parseInt(salesVO.getTotal_price())));
		detailVO.setOrder_product(salesVO.getOrder_product());
		
		return new ApiResponse<>(HttpStatus.OK.value(), msg.getMessage("get.success",null, locale),detailVO);
	}
	
	
	public List<BroadcastHistVO> selectMyBroadcastHistList(HashMap<String, Object> map) {
		List<BroadcastHistVO> list = new ArrayList<>();
		broadcastMapper.selectMyBroadcastHistList(map).iterator().forEachRemaining(list::add);
		return list;
	}
	
	public List<BroadcastReviewVO> selectReviewBroadcastList(HashMap<String, Object> map) {
		List<BroadcastReviewVO> list = new ArrayList<>();
		broadcastMapper.selectReviewBroadcastList(map).iterator().forEachRemaining(list::add);
		return list;
	}
	
	public List<BroadcastSalesVO> selectMyBroadcastSalesList(HashMap<String, Object> map) {
		List<BroadcastSalesVO> list = new ArrayList<>();
		broadcastMapper.selectMyBroadcastSalesList(map).iterator().forEachRemaining(list::add);
		
		for(int i=0;i<list.size();i++) {
			BroadcastSalesVO vo = (BroadcastSalesVO)list.get(i);
			HashMap<String, Object> smap = new HashMap<String, Object>();
			smap.put("pid", vo.getPid());
			smap.put("start", vo.getStartymd());
			smap.put("end", vo.getEndymd());
			WmProductSalesVO salesVO = wmOrderProductMapper.selectProductSalesInfo(smap);
			vo.setTotal_price(StringUtil.addComma(Integer.parseInt(salesVO.getTotal_price())));
			vo.setOrder_product(salesVO.getOrder_product());
		}
		
		return list;
	}
	
	public List<UserBroadcastVO> selectUserBroadcastList(HashMap<String, Object> map) {
		List<UserBroadcastVO> list = new ArrayList<>();
		broadcastMapper.selectUserBroadcastList(map).iterator().forEachRemaining(list::add);
		return list;
	}
	
}

package com.mobin.service;

import java.util.HashMap;
import java.util.List;

import com.mobin.model.AuthToken;
import com.mobin.model.SellerPushVO;
import com.mobin.model.SellerSaleInfoVO;
import com.mobin.model.SellerVO;


public interface SellerService {
	
	public AuthToken sellerLogin(SellerVO sellerVO);
	
	public SellerVO selectSellerInfoById(String id);
	
	public void updateAgreeFlag(Long uid);
	
	public void updateSellerPasswd(HashMap<String, Object> map);
	
	public SellerSaleInfoVO selectSellerSaleInfo(Long uid);
	
	public void updateSellerAppToken(SellerVO sellerVO);
	
	public List<SellerPushVO> selectMyActivityList(Long uid);
	
	public void updateActivityFlag(SellerPushVO sellerPushVO);
}

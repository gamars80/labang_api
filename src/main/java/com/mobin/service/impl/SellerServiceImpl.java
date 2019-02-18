package com.mobin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobin.common.util.StringUtil;
import com.mobin.mapper.BroadcastMapper;
import com.mobin.mapper.SellerMapper;
import com.mobin.mapper2.WmOrderProductMapper;
import com.mobin.model.AuthToken;
import com.mobin.model.BroadcastVO;
import com.mobin.model.SellerPushVO;
import com.mobin.model.SellerSaleInfoVO;
import com.mobin.model.SellerVO;
import com.mobin.model.wisa.WmProductSalesVO;
import com.mobin.service.SellerService;

@Service(value = "sellerService")
@Transactional(rollbackFor = { Exception.class }) 
public class SellerServiceImpl  implements SellerService {
	
	@Autowired
	SellerMapper sellerMapper;
	
	@Autowired
	BroadcastMapper broadcastMapper;
	
	@Autowired
	WmOrderProductMapper wmOrderProductMapper;


	public AuthToken sellerLogin(SellerVO sellerVO){
		AuthToken loginInfo = null;
		loginInfo = sellerMapper.sellerLogin(sellerVO);		
		return loginInfo;
		
        
    }

	public SellerVO selectSellerInfoById(String id){	
		SellerVO sellerInfo = null;		
		sellerInfo = sellerMapper.selectSellerInfoById(id);
		return sellerInfo; 
    }
	
	public void updateAgreeFlag(Long uid){	
		sellerMapper.updateAgreeFlag(uid);
	}
	
	public void updateSellerPasswd(HashMap<String, Object> map){	
		sellerMapper.updateSellerPasswd(map);
	}
	
	
	public SellerSaleInfoVO selectSellerSaleInfo(Long uid){	
		SellerSaleInfoVO info = new SellerSaleInfoVO();		
		List<BroadcastVO> list = null;
		list = broadcastMapper.selectMyBroadcastMonthlyList(uid);
		int totalMothSales = 0;
		int totalMonthOrder = 0;
		for(int i=0;i<list.size();i++) {
			BroadcastVO vo = (BroadcastVO)list.get(i);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("pid", vo.getPid());
			map.put("start", vo.getStartymd());
			map.put("end", vo.getEndymd());
			WmProductSalesVO salesVO = wmOrderProductMapper.selectProductSalesInfo(map);
			totalMothSales  += Integer.parseInt(salesVO.getTotal_price());
			totalMonthOrder += Integer.parseInt(salesVO.getOrder_product());
		}
		
		info.setMonth_sales(StringUtil.addComma(totalMothSales));
		info.setMonth_order(String.valueOf(totalMonthOrder));
		return info; 
    }
	
	public void updateSellerAppToken(SellerVO sellerVO){	
		sellerMapper.updateSellerAppToken(sellerVO);
	}
	
	public List<SellerPushVO> selectMyActivityList(Long uid) {
		List<SellerPushVO> list = new ArrayList<>();
		sellerMapper.selectMyActivityList(uid).iterator().forEachRemaining(list::add);
		return list;
	}
	
	public void updateActivityFlag(SellerPushVO sellerPushVO){	
		sellerMapper.updateActivityFlag(sellerPushVO);
	}
	
}

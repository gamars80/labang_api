package com.mobin.mapper2;

import java.util.HashMap;

import com.mobin.model.wisa.WmProductSalesVO;

public interface WmOrderProductMapper {
	public WmProductSalesVO selectProductSalesInfo(HashMap<String, Object> map);
}

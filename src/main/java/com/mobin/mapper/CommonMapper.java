package com.mobin.mapper;

import com.mobin.model.CommonAppVO;

public interface CommonMapper {
	public CommonAppVO selectAppVersionInfo(String device_type);
}

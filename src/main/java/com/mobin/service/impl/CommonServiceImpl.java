package com.mobin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobin.mapper.CommonMapper;
import com.mobin.model.CommonAppVO;
import com.mobin.service.CommonService;

@Service(value = "commonService")
@Transactional(rollbackFor = { Exception.class }) 
public class CommonServiceImpl implements CommonService {
	@Autowired
	CommonMapper commonMapper;
	
	public CommonAppVO selectAppVersionInfo(String device_type){	
		CommonAppVO app_ver = null;		
		app_ver = commonMapper.selectAppVersionInfo(device_type);
		return app_ver; 
    }
}

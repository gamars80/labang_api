package com.mobin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mobin.config.JwtTokenUtil;
import com.mobin.model.ApiResponse;
import com.mobin.model.AuthToken;
import com.mobin.model.LoginVO;
import com.mobin.model.SellerVO;
import com.mobin.service.SellerService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private SellerService sellerService;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ApiResponse<AuthToken> register(@RequestBody LoginVO loginUser) throws AuthenticationException {
    	//test
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getSeller_id(), loginUser.getSeller_pwd()));
    	//authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
    	final SellerVO seller = sellerService.selectSellerInfoById(loginUser.getSeller_id());
        final String token = jwtTokenUtil.generateToken(seller);
        return new ApiResponse<>(200, "success",new AuthToken(token, seller.getSeller_flag(),seller.getUid()));
    }

}

//package com.mobin.controller;
//
//import java.util.Locale;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.mobin.model.ApiResponse;
//import com.mobin.model.SellerVO;
//import com.mobin.service.UserService;
//
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//    
//    @Autowired
//	private MessageSource msg;
//
//    
//  
//	@PutMapping("/put/apptoken")
//   public ApiResponse<String> updateSellerDeviceToken(
//   		@RequestBody SellerVO sellerVO,
//   		HttpServletRequest req,
//   		Locale locale) {
//		Long uid = (Long)req.getAttribute("sid");
//		sellerVO.setUid(uid);
//		sellerService.updateAgreeFlag(uid);
//
//       return new ApiResponse<>(HttpStatus.OK.value(), msg.getMessage("upd.success",null, locale),null);
//   }
//	
//}

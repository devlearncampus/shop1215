package com.ch.shop.controller.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//유저가 이용하게되는 쇼핑몰의 메인페이지와 관련된 요청을 처리하는 컨트롤러
@Controller
public class MainController {
	
	//메인 요청 처리 
	@GetMapping("/shop/main")
	public String getMain() {
		
		return "shop/index";
	}
}











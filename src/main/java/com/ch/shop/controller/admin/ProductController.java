package com.ch.shop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch.shop.dto.Product;
import com.ch.shop.model.topcategory.TopCategoryService;

import lombok.extern.slf4j.Slf4j;

/*쇼핑몰의 관리자에서 상품과 관련된 요청을 처리하는 하위 컨트롤러 */
@Controller
@Slf4j
public class ProductController {
	
	//서비스 보유(느슨하게 보유)
	@Autowired
	private TopCategoryService topCategoryService;
	
	//상품 등록폼 요청 처리 
	@GetMapping("/product/registform")
	public String getRegistForm(Model model) {
		//3단계: 상품페이지에 출력할 상위카테고리 가져오기
		//List topList=topCategoryService.getList();
		
		//4단계: 결과저장 (request직접 해야 하지만, 스프링에서는 Model객체를 이용하면 간적접으로 저장이 됨 ) 
		//jsp까지 topList를 살려서 가야하므로, 포워딩 처리해야 함.. 스프링 개발자가 redirect 를 명시하지 않으면 디폴트가 포워딩 
		//model.addAttribute("topList", topList); //request.setAttribute("topList", topList); 와 동일 
		
		return "admin/product/regist";
	}
	
	//상품 등록 요청 처리 
	/*	클라이언트가 전송한 데이터의 Content-Type이 multipart/form-data즉 텍스트 뿐만 아니라, 바이너리가 포함된 경우
	 	기존의 HttpServletRequest 객체로 바로 받지 못한다, 따라서 개발자가 스트림을 직접 제어하거나, 아니면 기존에 이미 개발되어진
	 	파일업로드 컴포넌트를 이용해야  하는데, 자바 분야에서는 apache 에서 개발한 common fileupload 라이브러리를 많이 사용한다.
	 	따라서 스프링 프레임웍도 apache commons fileupload를 내부적으로 사용한다..
	 */
	@PostMapping("/product/regist")
	@ResponseBody
	public String regist(Product product) {
		//매개변수로 지정된 객체와 , html문서의 폼에 지정된 파라미터명이 일치한다면 자동 매핑이 이루어짐 
		log.debug("상품명"+product.getProduct_name());
		
		return "ok";
	}
}










package com.ch.shop.util;

import org.springframework.web.multipart.MultipartFile;

/*
  이 클래스의 정의목적?  
  기존 코드에서는 컨트롤러가 업로드를 처리하고 있었으므로, 문제였음 
  파일업로드는 모델 영역의 업무이므로, 절대 컨트롤러가 부담하면 안됨 , 따라서 모델영역의 객체이면서 
  DataBase 업무를 다루지 않는 객체이다..(DAO 아님)
*/
public class FileManager {

	/*--------------------------------------------------
	
	--------------------------------------------------*/
	public void save(MultipartFile mf, String path, String filename) {
		 
	}
	 
}








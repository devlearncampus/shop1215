package com.ch.shop.model.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch.shop.dto.Color;
import com.ch.shop.dto.Product;
import com.ch.shop.dto.ProductColor;
import com.ch.shop.dto.ProductSize;
import com.ch.shop.dto.Size;
import com.ch.shop.exception.ProductException;

import lombok.extern.slf4j.Slf4j;

/*
  서비스의 존재 이유? 
  1) 컨트롤러가 모델 영역의 디테일한 업무를 하지 못하게 방지	
      만일 컨트롤러가 디테일한 업무를 하게 되면, 모델영역의 업무를 부담하게되므로, MVC경계가 무너져 버린다..
      모델 영역을 분리시킬수 없으므로, 재사용성이 떨어지게 된다..
      
  2) 트랜잭션 처리 시 핵심 역할 
      서비스는 직접 일하지는 않지만 모델 영역의 DAO등에게 업무를 분담하는 능력을 가짐 
      특히 데이터베이스와 관련되어서는 각 DAO들의 업무 수행 결과에 따라 트랜잭션을 commit or rollback 결정 짓는 주체!!
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDAO productDAO; 
	
	@Autowired
	private ProductColorDAO productColorDAO;
	
	@Autowired
	private ProductSizeDAO productSizeDAO;
	
	@Override
	//등록 시 발생하는 예외를 여기서 잡아버리면, 서비스 영역에서 예외는 원인이 소멸되어버림...
	//우리의 목적은 개발자가 아닌 일반 사용자까지 예외 원인을 전달하는게 목적이므로, 컨트롤러에게까지 예외를 전달해야 한다..
	@Transactional
	public void regist(Product product) throws ProductException{
		//상품 등록이라는 커다란 업무영역은 총 4가지의 세부업무로 구성되어있음 ]
		
		/*------------------------------------------------
		세부 업무1) Product 테이블에 insert 하기
		------------------------------------------------*/
		log.debug("insert 하기 직전에 product의 product_id값은 "+ product.getProduct_id());
		productDAO.insert(product);
		log.debug("insert 직후 mybatis의 selectKey 동작 후 product의 product_id값은 "+ product.getProduct_id());
		
		/*------------------------------------------------
		세부 업무2) ProductColor 테이블에 insert 하기
		------------------------------------------------*/
		for(Color color : product.getColorList()) {
			ProductColor productColor = new ProductColor();
			productColor.setProduct(product);
			productColor.setColor(color);
			productColorDAO.insert(productColor);
		}
		
		/*------------------------------------------------
		세부 업무3) ProductSize 테이블에 insert 하기
		------------------------------------------------*/
		for( Size size : product.getSizeList()) {
			
			ProductSize productSize = new ProductSize();
			productSize.setProduct(product);//어떤 상품에 대해?
			productSize.setSize(size); //어떤 색상을?
			
			productSizeDAO.insert(productSize);
		}
		
	}
	
}










package com.ch.shop.dto;

import java.util.List;

import lombok.Data;

@Data
public class Product {
	
	private int product_id;
	private String product_name;
	private String brand;
	private int price;
	private int discount;
	private List<Color> colorList;//색상
	private List<Size> sizeList;//사이즈
	private String introduce;
	private String detail;
	private SubCategory subCategory;//하위카테고리
	
}







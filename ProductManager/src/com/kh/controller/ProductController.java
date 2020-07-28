package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.ProductService;
import com.kh.model.vo.Product;
import com.kh.view.ProductMenu;

public class ProductController {
	
	public void selectList() {
		ArrayList<Product> list = new ProductService().selectList();
		
		if(list.isEmpty()) {
			new ProductMenu().displayNoData("조회된 데이터가 없습니다");
		}else {
			new ProductMenu().displayProduct(list);
		}
	}
	
	public void insertProduct(Product p) {
		int result = new ProductService().insertProduct(p);
		
		if(result == 0) {
			new ProductMenu().displayFail("데이터 삽입에 실패했습니다");
		}else {
			new ProductMenu().displaySuccess("데이터 삽입에 성공했습니다");
		}
	}
	
	public void updateProduct(Product p) {
		int result = new ProductService().updateProduct(p);
		
		if(result == 0) {
			new ProductMenu().displayFail("수정 실패");
		}else {
			new ProductMenu().displaySuccess("수정 성공");
		}
	}
	
	public void deleteProduct(String pId) {
		int result = new ProductService().deleteProduct(pId);
		
		if(result != 0) {
			new ProductMenu().displaySuccess("데이터 삭제 성공 ");
		}else {
			new ProductMenu().displayFail("데이터 삭제 실패");
		}
	}
	
	public void selectProduct(String keyword) {
		ArrayList<Product> list = new ProductService().selectProduct(keyword);
		
		if(list.isEmpty()) {
			new ProductMenu().displayNoData("데이터가 없습니다");
		}else {
			new ProductMenu().displayProduct(list);
		}
	}
}

package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.model.dao.ProductDao;
import com.kh.model.vo.Product;
import com.kh.view.ProductMenu;

public class ProductService {
	
	public ArrayList<Product> selectList() {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Product> list = new ProductDao().selectList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public int insertProduct(Product p) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new ProductDao().insertProduct(conn, p);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public int updateProduct(Product p) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new ProductDao().updateProduct(conn, p);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public int deleteProduct(String pId) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new ProductDao().deleteProduct(conn, pId);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public ArrayList<Product> selectProduct(String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Product> list = new ProductDao().selectProduct(conn, keyword);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
}

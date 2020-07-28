package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.ProductController;
import com.kh.model.vo.Product;

public class ProductMenu {
	
	private ProductController pc = new ProductController();
	private Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		int menu;
		while(true) {
			System.out.println("==== 상품 메뉴 ====");
			System.out.println("1. 전체 조회");
			System.out.println("2. 상품 추가");
			System.out.println("3. 상품 수정");
			System.out.println("4. 상품 삭제");
			System.out.println("5. 상품 검색");
			System.out.println("0. 프로그램 종료");
			
			System.out.print("메뉴 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();
			
			switch(num) {
			case 1 : pc.selectList(); break;
			case 2 : insertProduct(); break;
			case 3 : updateProduct(); break;
			case 4 : pc.deleteProduct(inputProductId()); break;
			case 5 : pc.selectProduct(inputProductName()); break;
			case 0 : System.out.print("정말로 끝내겠습니까?(Y/N): ");
					 if(sc.nextLine().toUpperCase().charAt(0) == 'Y'){
							 System.out.println("프로그램을 종료합니다");
							 return;
					 }else {
						 break;
					 }
			default : System.out.println("번호를 잘못입력했습니다. 다시 입력해주세요");
		
			}
		}
	}
	// 2. 상품 추가
	public void insertProduct() {
		
		System.out.print("상품 아이디 : ");
		String pId = sc.nextLine();
		System.out.print("상품 이름: ");
		String pName = sc.nextLine();
		System.out.print("상품 가격 : ");
		int price = sc.nextInt();
		sc.nextLine();
		System.out.print("상품상세정보 : ");
		String des = sc.nextLine();
		System.out.print("재고 : ");
		int stock = sc.nextInt();
		sc.nextLine();
		
		Product p =  new Product(pId, pName, price, des, stock);
		
		pc.insertProduct(p);
	}
	public void updateProduct() {
		System.out.println("\n== 상품 수정 ==");
		Product p = new Product();
		p.setProductId(inputProductId());
		
		System.out.print("변경할 상품이름 : ");
		p.setP_Name(sc.nextLine());
		System.out.print("변경할 가격 값: ");
		p.setPrice(sc.nextInt());
		sc.nextLine();
		System.out.print("변경할 상품정보: ");
		p.setDescription(sc.nextLine());
		System.out.print("변경할 재고: ");
		p.setStock(sc.nextInt());
		sc.nextLine();
		
		pc.updateProduct(p);
	}
	public String inputProductId() {
		System.out.print("조회 할 상품 아이디 : ");
		return sc.nextLine();
	}
	public String inputProductName() {
		System.out.println("조회 할 상품 이름 : ");
		return sc.nextLine();
	}
	
	
// 사용자가 서비스 요청 후 보게되는 응답화면
//	1. 서비스 요청 성공 했을 때!
	public void displaySuccess(String message) {
		System.out.println("\n 서비스 요청 성공 : " + message);
	}
//	2. 서비스 요청 실패 했을 때
	public void displayFail(String message) {
		System.out.println("\n 서비스 요청 실패: " + message);
	}
//	3. 조회 요청 시 데이터가 없을 때
	public void displayNoData(String message) {
		System.out.println("\n" + message);
	}
//	4. 조회 요청 시 데이터 한 행만 있을 때
	public void displayProduct(Product p) {
		System.out.println("\n조회된 상품 정보는 다음과 같습니다");
		System.out.println(p);
	}
//	5. 조회 요청시 여러 행 조회되었을 때
	public void displayProduct(ArrayList<Product> list) {
		System.out.println("\n조회된 상품 정보는 다음과 같습니다.");
		for(Product p : list) {
			System.out.println(p);
		}
	}
}

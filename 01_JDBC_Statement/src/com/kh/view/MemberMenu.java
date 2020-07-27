package com.kh.view;

import java.util.Scanner;

import com.kh.controller.MemberController;
import com.kh.model.vo.Member;

// View : 사용자가 보게 될 시각적인 요소 담당 (입력 및 출력)
public class MemberMenu {

	//Scanner 객체 생성 (전역으로 입력받을 수 있게)
	private Scanner sc = new Scanner(System.in);
	// MemberController 객체 생성 (전역에서 바로 요청할 수 있게)
	private MemberController mc = new MemberController();
	
	
	/**
	 *	사용자가 보게 될 첫 화면
	 */
	public void mainMenu() {
		
		while(true) {
			
			System.out.println("\n == 회원 관리 프로그램 ==");
			System.out.println("1.회원가입");
			System.out.println("2.회원 전체 조회");
			System.out.println("3.회원 아이디로 검색");
			System.out.println("4.회원 이름으로 검색");
			System.out.println("5.회원 정보 변경");
			System.out.println("6.회원 탈퇴");
			System.out.println("0.프로그램 종료");
			System.out.print("번호 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1: insertMember(); break;
			case 2:  break;
			case 3:  break;
			case 4:  break;
			case 5:  break;
			case 6:  break;
			case 0: System.out.println("프로그램을 종료합니다"); return;
			default: System.out.println("번호를 잘못입력했습니다, 다시 입력해 주세요.");
			
			}
		}
	}
	/**
	 * 회원가입 창(화면)
	 * 
	 * 즉, 회원의 정보를 입력받는 메소드
	 */
	public void insertMember() {
		
		System.out.println("\n ==== 회원 가입 ====");
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		
		System.out.print("비밀번호 : ");
		String userPwd = sc.nextLine();
		
		System.out.print("이름 : ");
		String userName = sc.nextLine();
		
		System.out.print("성별(M/F) : ");
		String gender = sc.nextLine().toUpperCase();
		
		System.out.print("나이 : ");
		int age = sc.nextInt();
		sc.nextLine();
		
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		
		System.out.print("전화번호(-빼고 입력) : ");
		String phone = sc.nextLine();
		
		System.out.print("주소 : ");
		String address = sc.nextLine();
		
		System.out.print("취미(,로 공백없이 나열) : ");
		String hobby = sc.nextLine();
		
		// Member 객체 생성 후 하나씩 담기
		Member m = new Member(userId, userPwd, userName, gender, age, email, phone, address, hobby);
		
		// 회원가입 요청 (Controller 메소드 호출)
		mc.insertMember(m);
		
	}
	
	// 서비스 요청 처리 후 사용자가 보게 될 응답 화면들
	
	public void displaySuccess(String message) {
		System.out.println("서비스 요청 성공 : " + message);
	}
	
	public void displayFail(String message) {
		System.out.println("서비스 요청 실패 : " + message);
	}
	
}

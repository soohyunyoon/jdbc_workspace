package com.kh.controller;


import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

public class MemberController {

	/**
	 * 사용자가 회원 가입 요청 시 실행되는 메소드
	 * @param m --> 회원가입 시 입력한 회원의 정보들이 다 담겨있는 객체
	 */
	public void insertMember(Member m) {
		
		int result = new MemberDao().insertMember(m);
		
		if(result > 0) {
			new MemberMenu().displaySuccess("성공적으로 회원 가입 되었습니다");
		}else {
			new MemberMenu().displayFail("회원 가입에 실패했습니다.");
		}
	}
	
	public void selectList() {
		ArrayList<Member> list = new MemberDao().selectList();
		
		// list에 값이 없으면 조회된 결과 없다고 출력
		if(list.isEmpty()) {
			new MemberMenu().displayNoData("조회된 결과가 없습니다");
		}else {
			new MemberMenu().displayMemberList(list);
		}
	}
	
	public void selectByUserId(String userId) {
		Member m = new MemberDao().selectByUserId(userId);
		
		if(m == null) {
			new MemberMenu().displayNoData("조회된 아이디가 없습니다.");
		}else {
			new MemberMenu().displayMember(m);
		}
	}
	
	public void selectByUserName(String userName) {
		Member m = new MemberDao().selectByUserName(userName);
		
		if(m == null) {
			new MemberMenu().displayNoData("조회된 이름이 없습니다");
		}else {
			new MemberMenu().displayMember(m);
		}
		
	}
	
	public void updateMember(Member m) {
		int result = new MemberDao().updateMember(m);
	
		if (result > 0) {
			new MemberMenu().displayMember(m);
		}else {
			new MemberMenu().displayFail("실패했습니다");
		}
	}
	
	
	public void deleteMember(String userId) {
		int result = new MemberDao().deleteMember(userId);
		
		if(result > 0) {
			new MemberMenu().displaySuccess("성공적으로 탈퇴되었습니다");
		}else {
			new MemberMenu().displayFail("실패했습니다. 다시 탈퇴해주세요");
		}
	}
	
}

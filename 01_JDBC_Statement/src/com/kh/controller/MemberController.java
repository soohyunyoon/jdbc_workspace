package com.kh.controller;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

// Controller : View를 통해서 요청한 기능 처리하는 담당
// 				해당 메소드로 전달된 데이터를 가공처리한 후 Dao로 전달  (Dao 메소드 호출)
//				Dao로 부터 반환받은 결과에 따라 View(출력할 화면)을 결정

public class MemberController {
	/**
	 * 사용자의 회원가입 요청을 처리해주는 기능
	 * m --> 사용자가 입력한 정보들이 담겨있는 Member 객체
	 */
	public void insertMember(Member m) {
		
		int result = new MemberDao().insertMember(m);
		
		if(result > 0) { // 성공 했을 경우
			new MemberMenu().displaySuccess("회원가입 성공");
		}else { // 실패 했을 경우
			new MemberMenu().displayFail("회원가입 실패");
		}
	}
	
}

package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.kh.model.vo.Member;

// Dao (Data Access Object) : DB 직접적으로 접근하는 담당
// 1) DB에 접속(연결)하여
// 2) SQL문 실행 및 결과 받기 (select문 경우 : 조회된 ResultSet / dml문 경우 : 처리된 행의 개수)
//	    만일 dml문 수행했다면 트랜잭션 처리하기(성공이면 commit, 실패면 rollback)
// 3) 결과 반환
public class MemberDao {
/*
	<JDBC 객체>
	- Connection : DB의 연결정보를 담는 객체
	- [Prepared]Statement : DB에 SQL문을 전달해서 실행하고 그 결과를 받아내는 객체
			> Statement : 실행시킬 sql문이 완성형태여야됨!
			> PreparedStatment : 실행시킬 sql문이 미완성상태여도 됨
	- ResultSet : Select문 수행 후 조회된 결과들이 담겨있는 객체
	
	<처리 순서>
	1) jdbc driver 등록 : 해당 DBMS가 제공하는 클래서로 등록
	2) Connection 객체 생성 : 연결하고자 하는 DB정보를 입력해서 DB와 연결하며 생성
	3) Statement 객체 생성 : Connection 객체를 이용해서 생성(sql문 실행 및 결과를 담당하는 객체)
	4) SQL문 전송 후 실행 및 결과 받기 : Statement 객체를 이용해서 sql문 실행 후 결과 받기
	5) 실행결과 --> select 문일 경우 --> ResultSet객체(조회된 데이터들이 담겨있음)	--> 6_1)
			 --> dml 문일 경우	  --> int(처리된 행의 개수)					--> 6_2)
	6_1) ResultSet에 담겨있는 데이터를 하나씩 하나씩 뽑아서 vo객체에 담기
	6_2) 트랜잭션 처리 (성공이면 commit, 실패면 rollback)

	7) 다 쓴 JDBC용 객체를 반드시 자원반납(close) --> 생성된 역순으로 자원반납(close)
	
*/
	public int insertMember(Member m) { // insert문
		
		// 필요한 변수들 먼저 셋팅
		
		// 처리된 결과(처리된 행의 개수)를 받아줄 변수
		int result = 0;
		// DB의 연결 정보를 담는 객체 선언
		Connection conn = null;
		// SQL문을 전송해서 실행 후 결과 받는 객체 선언
		Statement stmt = null;
		
		// 실행할 sql문 (완성형태로 만들어줄 것) --> 끝에 세미콜론(;)있으면 안됨!
		// INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, 'user02','pass02','혼공자','F',20,,..) 
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL," 
										 + "'" + m.getUserId()   + "', " 
										 + "'" + m.getUserPwd()  + "', "
					                     + "'" + m.getUserName() + "', "
					                     + "'" + m.getGender()   + "', "
					                     +       m.getAge()      + ", "
					                     + "'" + m.getEmail()    + "', "
					                     + "'" + m.getPhone()    + "', "
					                     + "'" + m.getAddress()  + "', "
										 + "'" + m.getHobby()    + "', SYSDATE)";
	
		//System.out.println(sql);
	
		
		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 오타있거나, ojdbc6.jar파일이 추가되어있지 않을 경우(Class Not Found exception)
			
			// 2) Connection 객체 생성 (DB에 연결 --> url, 계정명, 계정비밀번호)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","JDBC","JDBC");
			
			// 3) Statement 객체 생성
			stmt = conn.createStatement();
			
			// 4, 5) sql문 해당 db에 전달 후 실행 --> 결과 받기 (처리된 행의 개수)
			result = stmt.executeUpdate(sql); // --> insert, update, delete구문일 경우 executeUpdate 메소드로 sql문 실행
			
			System.out.println(result);
			
			//6_2) 트랜잭션 처리
			if(result > 0) { // 성공했을 경우 commit
				conn.commit();
			}else { // 그게 아닐 경우 rollback
				conn.rollback();
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 7) 다 쓴 JDBC용 객체 자원반납(close) 단, 생성된 역순으로
			try {
				stmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	
	
	
	
	
	}
	

}

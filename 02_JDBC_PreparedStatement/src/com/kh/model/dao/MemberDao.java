package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Member;

public class MemberDao {
/*
	Statememnt 와 preparedStatement의 차이점 --> 상속구조! preparedStatement가 자식~
	Statement 같은 경우 완성된 sql문을 바로 실행하는 객체 (sql문을 완성형태로 만들어야됨)
	preparedStatement 같은 경우 "미완성된 sql문"을 잠시 보관해둘 수 있는 객체
		해당 sql문 실행하기 전 완성형태로 만든 후 실행하면 됨
	
	기존의 Statement 방식
	Connection 객체를 통해 Statement 객체 생성	: stmt = conn.createStatement();
	Statement 객체를 통해 완성된 sql문 실행 및 결과	:  결과 = stmt.executeXXX(완성된 sql);
	
	PreparedStatement 방식
	Connection 객체를 통해 PreparedStatement 객체 생성
				(단, 미완성된 sql문을 담은 채로 생성)		: pstmt = conn.priparedStatement(미완성된 sql);
	PreparedStatement 에서 제공하는 메소드를 통해 완성형태로	: pstmt.setXXX(1, "대체할 값"); ... 
	그리고 나서 해당 완성된 sql문 실행 및 결과					: 결과 = pstmt.executeXXX();
	
	
*/	
	public int insertMember(Member m) { //insert문 quary	--> 처리된 행의 개수 (int)
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		// 실행하고자 하는 sql문 미완성된 형태로 올 수 있음 --> 미리 사용자가 입력한 값을 들어갈 수 있게 공간(? --> 공간확보하는 키워드)만 확보
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
		
		
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
				pstmt = conn.prepareStatement(sql); // PreparedStatement 객체 생성시 sql문 담은 채로 생성
				
				// 현재 공간 sql문 미완성된 상태기 떄문에 바로 실행은 불가 --> 각각의 공간에 실제 값 대체한 후 실행
				// pstmt.setString(1, user01); //--> setString으로 대체 시 'user01'(양옆에 홑따옴표 붙어서 들어감)
				pstmt.setString(1, m.getUserId());
				pstmt.setString(2, m.getUserPwd());
				pstmt.setString(3, m.getUserName());
				pstmt.setString(4, m.getGender());
				pstmt.setInt(5, m.getAge()); //--> setInt로 대체시 20 (홑따옴표 안붙어서 들어감)
				pstmt.setString(6, m.getEmail());
				pstmt.setString(7, m.getPhone());
				pstmt.setString(8, m.getAddress());
				pstmt.setString(9, m.getHobby());
				
				
				result = pstmt.executeUpdate();
				
				if(result > 0) {
					conn.commit();
				}else {
					conn.rollback();
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
		return result;
	}
	
	public ArrayList<Member> selectList() {
		
		ArrayList<Member> list = new ArrayList<>();
		ResultSet rset = null;
		Connection conn = null;
		Statement stmt = null;
		
		String sql = ("SELECT * FROM MEMBER");
		/*
		<처리 순서>
		1) jdbc driver 등록 : 해당 DBMS가 제공하는 클래스로 등록
		2) Connection 객체 생성 : 연결하고자 하는 DB정보를 입력해서 DB와 연결하며 생성
		3) Statement 객체 생성 : Connection 객체를 이용해서 생성(sql문 실행 및 결과를 담당하는 객체)
		4) SQL문 전송 후 실행 및 결과 받기 : Statement 객체를 이용해서 sql문 실행 후 결과 받기
		5) 실행결과 --> select 문일 경우 --> ResultSet객체(조회된 데이터들이 담겨있음)	--> 6_1)
				 --> dml 문일 경우	  --> int(처리된 행의 개수)					--> 6_2)
		6_1) ResultSet에 담겨있는 데이터를 하나씩 하나씩 뽑아서 vo객체에 담기
		6_2) 트랜잭션 처리 (성공이면 commit, 실패면 rollback)

		7) 다 쓴 JDBC용 객체를 반드시 자원반납(close) --> 생성된 역순으로 자원반납(close)
*/
		try {
			//1)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "JDBC", "JDBC");
			//3)
			stmt = conn.createStatement();
			//4)
			rset = stmt.executeQuery(sql);
			//6_1)
			while(rset.next()) {
				Member m = new Member();
				m.setUserNo(rset.getInt("userno"));
				m.setUserId(rset.getString("userid"));
				m.setUserPwd(rset.getString("userpwd"));
				m.setUserName(rset.getString("username"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				
				list.add(m);
				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return list;
		
	}
	
	public Member selectByUserId(String userId) {
		Member m = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "SELECT * FROM MEMBER WHERE USERID = ?" ;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				m = new Member (rset.getInt("UserNo"),
								rset.getString("UserId"),
								rset.getString("UserPwd"),
								rset.getString("UserName"),
								rset.getString("Gender"),
								rset.getInt("Age"),
								rset.getString("Email"),
								rset.getString("Phone"),
								rset.getString("Address"),
								rset.getString("Hobby"),
								rset.getDate("EnrollDate"));
									
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return m;
	}
	
	public ArrayList<Member> selectByUserName(String userName) {
		ArrayList<Member> m = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '%'||?||'%'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				 m = new ArrayList<Member>(
								rset.getString("USERID"),
								rset.getString("USERPWD"),
								rset.getString("UserName"),
								rset.getString("Gender"),
								rset.getInt("Age"),
								rset.getString("Email"),
								rset.getString("Phone"),
								rset.getString("Address"),
								rset.getString("Hobby")
								);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return m;
	}
	
	public int updateMember(Member m) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = "UPDATE MEMBER SET USERPWD = ?, EMAIL = ?, PHONE = ?, ADDRESS = ? WHERE USERID = ?";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserPwd());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getUserId());
			
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public int deleteMember(String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "DELETE FROM MEMBER WHERE USERID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
}






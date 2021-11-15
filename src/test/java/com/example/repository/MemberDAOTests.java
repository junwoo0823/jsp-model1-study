package com.example.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.example.domain.MemberVO;

public class MemberDAOTests {
	
	// 픽스처(fixture)
	private MemberDAO dao;
	private MemberVO member1, member2;
	
	@Before
	public void setUp() {
		dao = MemberDAO.getInstance();
		dao.deleteAll();
		
		member1 = new MemberVO();
		member1.setId("aaa");
		member1.setPasswd("1234");
		member1.setName("홍길동");
		member1.setBirthday("19810720");
		member1.setGender("M"); // M 남자  F 여자  N 선택안함
		member1.setEmail("aaa@a.com");
		member1.setRecvEmail("Y");
		member1.setRegDate(new Timestamp(System.currentTimeMillis()));
		
		member2 = new MemberVO();
		member2.setId("bbb");
		member2.setPasswd("1234");
		member2.setName("성춘향");
		member2.setBirthday("19791122");
		member2.setGender("F"); // M 남자  F 여자  N 선택안함
		member2.setEmail("bbb@b.com");
		member2.setRecvEmail("N");
		member2.setRegDate(new Timestamp(System.currentTimeMillis()));
	} // setUp

	@Test
	public void testConnection() {
		// MySQL DB접속정보
		String url = "jdbc:mysql://localhost:3306/jspdb?useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Asia/Seoul";
		String user = "jspid";
		String passwd = "jsppass";
		try {
			// 1단계. JDBC 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 드라이버 이름
			// 2단계. DB연결
			Connection con = DriverManager.getConnection(url, user, passwd);
			
			assertNotNull(con);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // testConnection
	
	@Test
	public void testDeleteAll() {
		dao.deleteAll();
		assertEquals(0, dao.getCountAll());
	}
	
	@Test
	public void testInsert() throws Exception {
		dao.insert(member1); //
		
		MemberVO member = dao.getMemberById(member1.getId());
		
		assertEquals(member1.toString(), member.toString());
	}
	
	@Test
	public void testGetMembers() throws Exception {
		Object[] memberArr = { member1, member2 };
		
		dao.insert(member1);
		dao.insert(member2);
		
		List<MemberVO> memberList = dao.getMembers();
		Object[] memberArr2 = memberList.toArray();
		
		//assertArrayEquals(memberArr, memberArr2);
	}
	
	@Test
	public void testGetMemberById() throws Exception {
		dao.insert(member1);
		
		MemberVO member = dao.getMemberById(member1.getId()); //
		
		assertEquals(member1.toString(), member.toString());
	}
	
	@Test
	public void testUpdateById() throws Exception {
		dao.insert(member1);
		
		MemberVO member = new MemberVO();
		member.setId("aaa"); // 동일 아이디로 수정
		member.setPasswd("5678");
		member.setName("이몽룡");
		member.setBirthday("19820227");
		member.setGender("F"); // M 남자  F 여자  N 선택안함
		member.setEmail("aaa11@a11.com");
		member.setRecvEmail("N");
		member.setRegDate(new Timestamp(System.currentTimeMillis()));
		
		dao.updateById(member);
		
		MemberVO updatedMember = dao.getMemberById(member.getId());
		
		assertEquals(member.toString(), updatedMember.toString());
	}
	
	@Test
	public void testDeleteById() throws Exception {
		dao.insert(member1);
		dao.insert(member2);
		assertEquals(2, dao.getCountAll());
		
		dao.deleteById(member1.getId());
		assertEquals(1, dao.getCountAll());
		
		dao.deleteById(member2.getId());
		assertEquals(0, dao.getCountAll());
	}
}






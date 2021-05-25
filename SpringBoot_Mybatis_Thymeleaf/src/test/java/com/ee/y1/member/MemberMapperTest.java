package com.ee.y1.member;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberMapperTest {
	
	@Autowired
	private MemberMapper memberMapper; 
	
	@Test
	void getMemberLoginTest() throws Exception{
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername("id8");
		
		memberVO = memberMapper.memberLogin(memberVO);
		
		for(RoleVO roleVO : memberVO.getRoles()) {
			System.out.println(roleVO.getRoleName());
		}
		
		assertNotNull(memberVO);
	}
	
	//@Test
	void setMemberRoleTest() throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "id8");
		map.put("roleName", "MEMBER");
		int result = memberMapper.setMemberRole(map);
		
		assertEquals(1, result);
	}
	

	//@Test
	void test() throws Exception{
		MemberVO memberVO = new MemberVO();
		
		memberVO.setUsername("id8");
		memberVO.setPassword("id8");
		memberVO.setName("id8");
		memberVO.setPhone("01088888888");
		memberVO.setEmail("id8@naver.com");
		memberVO.setEnabled(true);
		
		int result = memberMapper.setMemberJoin(memberVO);
		
		assertEquals(1, result);
		
	}

}

package com.ee.y1.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import com.ee.y1.util.FileManager;

//Spring security에서 사용하는 Service
//UserDetailsService 구현 (interface)
@Service
public class MemberService implements UserDetailsService{

	@Autowired
	private MemberMapper memberMapper;

	@Autowired
	private FileManager fileManager;
	
	//패스워드를 암호화시켜주는 메서드
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Value("${member.filePath}")
	private String filePath;
	
	// Login
	//개발자가 호출 X, 자동으로 호출
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO memberVO = new MemberVO();
		
		System.out.println("UserName : "+username);
		
		memberVO.setUsername(username);
		memberVO = memberMapper.memberLogin(memberVO);
		
		return memberVO;
	}
	
	
	//검증 메서드
		public boolean memberError(MemberVO memberVO, Errors errors)throws Exception{
			boolean result = false;
			
			//기본 제공 검증  결과
//			if(errors.hasErrors()) {
//				result = true;
//			}
			result = errors.hasErrors();
			
			//password가 일치 여부 검증
			if(!memberVO.getPassword().equals(memberVO.getPassword1())) {
				errors.rejectValue("password1", "memberVO.password.notEqual");
							     //(form path, field 명, properties의 code(key));
				result=true;
			}
			
			//UserName 중복 여부
			MemberVO checkMember = memberMapper.getUsername(memberVO);
			//checkMember 가 null이면 중복 X
			//checkMember 가 null이 아니면 중복
			if(checkMember != null) {
				errors.rejectValue("username", "member.id.equal");
				result = true;
			}
			
			
			
			return result;
		}

	// Login 위의 오버라이드된 메서드가 대체됨
//	public MemberVO memberLogin(MemberVO memberVO) throws Exception {
//		memberVO = memberMapper.memberLogin(memberVO);
//
//		return memberVO;
//	}

	// Join
	@Transactional(rollbackFor = Exception.class)
	public int setMemberJoin(MemberVO memberVO, MultipartFile avatar) throws Exception {

		//0. 사전작업
		//a. password 암호화						//암호화하고싶은걸 넣음
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		
		//b. 사용자 계정 활성화
		memberVO.setEnabled(true);
		
		//1. Member Table 저장
		int result = memberMapper.setMemberJoin(memberVO);
		
		//2. Role Table 저장
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", memberVO.getUsername());
		map.put("roleName", "ROLE_MEMBER");
		result = memberMapper.setMemberRole(map); 
		
		//2. HDD에 저장
		String filePath = this.filePath;
		
		if(avatar.getSize() != 0) {
		 
		 String fileName = fileManager.save(filePath, avatar);
		 
		 System.out.println("fileName : "+fileName);
		  
		 MemberFileVO memberFileVO = new MemberFileVO();
		  
		 memberFileVO.setUsername(memberVO.getUsername());
		 memberFileVO.setFileName(fileName);
		 memberFileVO.setOriName(avatar.getOriginalFilename());		 
		  
		 // 3. Memeberfiles table 저장
		 result = memberMapper.setFileInsert(memberFileVO);
		 
		}

		return memberMapper.setMemberJoin(memberVO);
	}


}

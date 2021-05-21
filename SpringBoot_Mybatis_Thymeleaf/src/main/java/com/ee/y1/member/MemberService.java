package com.ee.y1.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import com.ee.y1.util.FileManager;

@Service
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;

	@Autowired
	private FileManager fileManager;
	
	@Value("${member.filePath}")
	private String filePath;
	
	
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
			
			//admin, adminstrator, root
			
			
			return result;
		}

	// Login
	public MemberVO memberLogin(MemberVO memberVO) throws Exception {
		memberVO = memberMapper.memberLogin(memberVO);

		return memberVO;
	}

	// Join
	public int setMemberJoin(MemberVO memberVO, MultipartFile avatar) throws Exception {

		//1. Member Table 저장
		int result = memberMapper.setMemberJoin(memberVO);
		
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

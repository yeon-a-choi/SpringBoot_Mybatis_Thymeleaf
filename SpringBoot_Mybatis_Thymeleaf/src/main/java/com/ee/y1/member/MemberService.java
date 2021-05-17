package com.ee.y1.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ee.y1.util.FileManager;

@Service
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;

	@Autowired
	private FileManager fileManager;

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
		String filePath = "upload/member/";
		
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

	// Update
	public int setMemberUpdate(MemberVO memberVO) throws Exception {
		return memberMapper.setMemberUpdate(memberVO);
	}

	// Delete
	public int setMemberDelete(MemberVO memberVO) throws Exception {

		MemberFileVO memberFileVO = memberMapper.getMemberFile(memberVO);

		String filePath = "upload/member/";
		String fileName; // 어디서 불러와야하지?

		// fileName 들어가야함
		boolean check = fileManager.delete(filePath, null);

		return memberMapper.setMemberDelete(memberVO);
	}

	// Id Check(id 중복체크)
	public MemberVO memberIdCheck(MemberVO memberVO) throws Exception {
		return memberMapper.memberIdCheck(memberVO);
	}

}

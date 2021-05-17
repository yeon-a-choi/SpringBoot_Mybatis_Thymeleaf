package com.ee.y1.member;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MemberMapper {
	
	/* member */
	
	//member Login
	public MemberVO memberLogin(MemberVO memberVO) throws Exception;
	
	//member Join
	public int setMemberJoin(MemberVO memberVO) throws Exception;
	
	//member Update
	public int setMemberUpdate(MemberVO memberVO) throws Exception;
	
	//member Delete
	public int setMemberDelete(MemberVO memberVO) throws Exception;
	
	//member ID Check(id 중복체크)
	public MemberVO memberIdCheck(MemberVO memberVO) throws Exception;
	
	
	/* fileUpload */
	
	//File Insert
	public int setFileInsert(MemberFileVO memberFileVO) throws Exception;
	
	//Login File Select
	public MemberFileVO memberLoginFile(MemberVO memberVO) throws Exception;
	
	//Login FileName
	public MemberFileVO getMemberFile(MemberVO memberVO) throws Exception;
	

}

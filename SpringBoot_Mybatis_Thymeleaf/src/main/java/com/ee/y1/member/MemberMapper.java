package com.ee.y1.member;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MemberMapper {
	
	/* member */
	
	//member Login
	public MemberVO memberLogin(MemberVO memberVO) throws Exception;
	
	//member Join
	public int setMemberJoin(MemberVO memberVO) throws Exception;
	
	//member username
	public MemberVO getUsername(MemberVO memberVO)throws Exception;
	
	
	/* fileUpload */
	
	//File Insert
	public int setFileInsert(MemberFileVO memberFileVO) throws Exception;


}

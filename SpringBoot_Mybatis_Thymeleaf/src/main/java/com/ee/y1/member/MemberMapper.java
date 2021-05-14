package com.ee.y1.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	
	//member Join
	public int setMemberJoin() throws Exception;
	
	//member Update
	public int setMemberUpdate() throws Exception;

}

package com.ee.y1.board.qna;

import org.apache.ibatis.annotations.Mapper;

import com.ee.y1.board.BoardMapper;
import com.ee.y1.board.BoardVO;

@Mapper
public interface QnaMapper extends BoardMapper{
	
	//reply
	
	public int setReplyInsert(BoardVO boardVO) throws Exception;
	
	public int setReplyUpdate(BoardVO boardVO) throws Exception;
	
	public int setRefUpdate(BoardVO boardVO) throws Exception;

}

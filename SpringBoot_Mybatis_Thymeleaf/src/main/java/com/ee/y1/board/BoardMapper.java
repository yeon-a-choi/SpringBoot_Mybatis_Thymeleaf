package com.ee.y1.board;

import java.util.List;

import com.ee.y1.util.Pager;

public interface BoardMapper{
	
	//List
	public List<BoardVO> getList(Pager pager) throws Exception;
	
	//Count
	public Long getTotalCount(Pager pager) throws Exception;
	
	//Select
	public BoardVO getSelect(BoardVO boardVO) throws Exception;
	
	//Insert
	public int setInsert(BoardVO boardVO) throws Exception;
	
	//Update
	public int setUpdate(BoardVO boardVO) throws Exception;
	
	//HitUpdate
	public int setHitUpdate(BoardVO boardVO) throws Exception;
	
	//Delete
	public int setDelete(BoardVO boardVO) throws Exception;

}

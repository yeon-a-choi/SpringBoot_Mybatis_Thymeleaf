package com.ee.y1.board.notice;

import java.util.List;

import com.ee.y1.board.BoardFileVO;
import com.ee.y1.board.BoardVO;

import lombok.Data;

@Data
//Value Object의 약자
public class NoticeVO extends BoardVO{
	
	private List<BoardFileVO> files;

}

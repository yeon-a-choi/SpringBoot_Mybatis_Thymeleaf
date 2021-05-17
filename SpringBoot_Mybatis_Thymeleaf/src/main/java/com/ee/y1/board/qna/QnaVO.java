package com.ee.y1.board.qna;

import java.util.List;

import com.ee.y1.board.BoardFileVO;
import com.ee.y1.board.BoardVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaVO extends BoardVO{
	
	private Long ref;
	private Long step;
	private Long depth;
	
	private List<BoardFileVO> files;

}

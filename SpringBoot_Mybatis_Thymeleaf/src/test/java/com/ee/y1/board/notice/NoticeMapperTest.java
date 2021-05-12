package com.ee.y1.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ee.y1.board.BoardVO;
import com.ee.y1.util.Pager;

@SpringBootTest
class NoticeMapperTest {
	
	@Autowired
	private NoticeMapper noticeMapper;
	
	@Test
	void setInsertTest()throws Exception{
		
		for(int i=0;i<100;i++) {
			BoardVO boardVO = new BoardVO();
			boardVO.setTitle("title"+i);
			boardVO.setContents("contents"+i);
			boardVO.setWriter("writer"+i);
			noticeMapper.setInsert(boardVO);
		}
		System.out.println("Finish");
	}

	//@Test
	void getListtest() throws Exception{
		Pager pager = new Pager();
		List<BoardVO> ar = noticeMapper.getList(pager);
		
		for(BoardVO boardVO : ar) {
			System.out.println(boardVO.toString());
		}
		
		assertNotEquals(0, ar.size());
	}

}

package com.ee.y1.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ee.y1.board.BoardVO;

@SpringBootTest
class NoticeMapperTest {
	
	@Autowired
	private NoticeMapper noticeMapper;

	@Test
	void getListtest() throws Exception{
		List<BoardVO> ar = noticeMapper.getList();
		
		for(BoardVO boardVO : ar) {
			System.out.println(boardVO.toString());
		}
		
		assertNotEquals(0, ar.size());
	}

}

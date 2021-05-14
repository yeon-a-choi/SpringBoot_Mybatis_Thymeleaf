package com.ee.y1.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ee.y1.board.BoardService;
import com.ee.y1.board.BoardVO;
import com.ee.y1.util.FileManager;
import com.ee.y1.util.Pager;

@Service
public class QnaService implements BoardService{
	
	@Autowired
	private QnaMapper qnaMapper;
	
	@Autowired
	private FileManager fileManager; 
	
	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		
		pager.makeRow();
		
		Long totalCount = qnaMapper.getTotalCount(pager);
		
		pager.makeNum(totalCount);
		
		return qnaMapper.getList(pager);
	}

	@Override
	public BoardVO getSelect(BoardVO boardVO) throws Exception {
		qnaMapper.setHitUpdate(boardVO);
		return qnaMapper.getSelect(boardVO);
	}

	@Override
	public int setInsert(BoardVO boardVO, MultipartFile[] files) throws Exception {
		
		int result = qnaMapper.setInsert(boardVO);
		
		String filePath = "upload/qna/";
		
		for(MultipartFile multipartFile : files) {
			
			if(multipartFile.getSize() == 0) {
				continue;
			}
			
			String fileName = fileManager.save(multipartFile, filePath);
			
			boardVO = new BoardVO(); 
			
		}
		
		return 0;
	}

	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setDelete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}

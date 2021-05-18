package com.ee.y1.board.notice;

import java.util.List;

import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ee.y1.board.BoardFileVO;
import com.ee.y1.board.BoardService;
import com.ee.y1.board.BoardVO;
import com.ee.y1.util.FileManager;
import com.ee.y1.util.Pager;

@Service
public class NoticeService implements BoardService{
	
	@Autowired
	private NoticeMapper noticeMapper;
	
	@Autowired
	private FileManager fileManager;

	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		
		//System.out.println(10/0);
//		if(pager.getCurPage() %2 == 0) {
//			//강제 Exception발생
//			throw new SqlSessionException();
//		}
		
		pager.makeRow();
		Long totalCount = noticeMapper.getTotalCount(pager);
		pager.makeNum(totalCount);
		
		System.out.println("startNum : "+pager.getStartNum());
		System.out.println("lastNum : "+pager.getLastNum());
		
		return noticeMapper.getList(pager);
	}

	@Override
	public BoardVO getSelect(BoardVO boardVO) throws Exception {
		noticeMapper.setHitUpdate(boardVO);
		return noticeMapper.getSelect(boardVO);
	}

	@Override
	public int setInsert(BoardVO boardVO, MultipartFile [] files) throws Exception {
		
		int result = noticeMapper.setInsert(boardVO);
		
		String filePath = "upload/notice/";
		
		for(MultipartFile multipartFile : files) {
			
			if(multipartFile.getSize() == 0) {
				continue;
			}
			
			String fileName = fileManager.save(filePath, multipartFile);
			System.out.println("fileName : "+fileName);
			
			BoardFileVO boardFileVO = new BoardFileVO();
			boardFileVO.setFileName(fileName);
			boardFileVO.setOriName(multipartFile.getOriginalFilename());
			boardFileVO.setNum(boardVO.getNum());
			
			noticeMapper.setFileInsert(boardFileVO);
			
		}
		
		return 0;//noticeMapper.setInsert(boardVO);
	}

	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		return noticeMapper.setUpdate(boardVO);
	}

	@Override
	public int setDelete(BoardVO boardVO) throws Exception {
		return noticeMapper.setDelete(boardVO);
	}

}

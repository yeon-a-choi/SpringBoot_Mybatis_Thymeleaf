package com.ee.y1.board.notice;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ee.y1.board.BoardVO;
import com.ee.y1.member.MemberVO;
import com.ee.y1.util.Pager;

@Controller
@RequestMapping("/notice/**")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService; 
	
	@ModelAttribute("board")
	public String getBoard() throws Exception {
		return "notice";
	}
	
	//fileDown
	@GetMapping("fileDown")
	public ModelAndView fileDown(String fileName, String oriName)throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.addObject("fileName", fileName);
		mv.addObject("oriName", oriName);
		mv.addObject("filePath", "/upload/notice/");
		
		// view의 이름은 Bean의 이름과 일치
		mv.setViewName("down");
		//fileDown.html
		return mv;
	}
	
	//List
	@GetMapping("list")
	public String getList(Model model, Pager pager)throws Exception{
		List<BoardVO> ar = noticeService.getList(pager);
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		return "board/list";
	}
	
	//Select
	@GetMapping("select")
	public ModelAndView getSelect(BoardVO boardVO) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		boardVO = noticeService.getSelect(boardVO);
		
		mv.addObject("vo", boardVO);
		mv.setViewName("board/select");
		
		return mv;
	}
	
	//Insert
	@GetMapping("insert")
	public String setInsert(Model model, HttpSession session)throws Exception{
		
		model.addAttribute("vo", new BoardVO());
		model.addAttribute("action", "insert");
		
		Object obj = session.getAttribute("member");
		MemberVO memberVO = null;
		String path="common/result";
		model.addAttribute("msg", "관리자가 아닙니다");
		model.addAttribute("path", "./list");
		//if(obj != null) {}
		if(obj instanceof MemberVO) {
			memberVO = (MemberVO)obj;
			
			if(memberVO.getUsername().equals("admin")) {
				path="board/form";
			}
		}	
		
		return path;
	}
	
	@PostMapping("insert")
	public String setInsert(BoardVO boardVO, MultipartFile [] files)throws Exception{
		
//		System.out.println(files.length);
		
//		for(MultipartFile f : files) {
//			System.out.println(f.getOriginalFilename());
//		}
		
		int result = noticeService.setInsert(boardVO, files);
		
		return "redirect:./list";
	}
	
	
	//Update
	@GetMapping("update")
	public ModelAndView setUpdate(BoardVO boardVO) throws Exception{
		
		boardVO = noticeService.getSelect(boardVO);
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("vo", boardVO);
		mv.addObject("action", "update");
		
		mv.setViewName("board/form");
		
		return mv;
	}
	
	@PostMapping("update")
	public String setUpdate(BoardVO boardVO, Model model) throws Exception{
		
		int result = noticeService.setUpdate(boardVO);
		
		return "redirect:./list";
	}
	
	
	//Delete
	@GetMapping("delete")
	public String setDelete(BoardVO boardVO) throws Exception{
		
		int result = noticeService.setDelete(boardVO);
		
		return "redirect:./list";
	}

}
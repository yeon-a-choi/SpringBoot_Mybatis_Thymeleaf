package com.ee.y1.board.qna;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ee.y1.board.BoardVO;
import com.ee.y1.util.Pager;

@Controller
@RequestMapping("/qna/**")
public class QnaController {
	
	@ModelAttribute("board")
	public String getBoard() {
		return "qna";
	}
	
	//List
	@GetMapping("list")
	public String getList(Model model, Pager pager)throws Exception{
		
		List<BoardVO> ar = qnaService.getList(pager);
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		return "board/list";
		
	}
	
	//Select
	@GetMapping("select")
	public ModelAndView getSelect(BoardVO boardVO) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		boardVO = qnaService.getSelect(boardVO);
		
		mv.addObject("vo", boardVO);
		mv.setViewName("board/select");
		
		return mv;
	}
	
}
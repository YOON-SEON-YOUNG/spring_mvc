package com.kh.sample01.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.sample01.domain.BoardVo;
import com.kh.sample01.domain.MemberVo;
import com.kh.sample01.domain.PagingDto;
import com.kh.sample01.service.BoardService;
import com.kh.sample01.util.FileUploadUtil;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Resource
	private String uploadPath; // servlet-context.xml (id="uploadPath")
	
	@Inject
	private BoardService boardService;
	
	// 글 등록 폼 -> /board/register : GET 방식 요청 처리
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(PagingDto pagingDto) throws Exception {
		
		System.out.println("registerGET() 실행됨");
	}
	
	
	// 글 등록 처리 -> /board/register : POST 방식 요청 처리
	@RequestMapping(value = "/register",  method = RequestMethod.POST)
	public String registerPOST(	HttpSession session,
								BoardVo boardVo,
								@ModelAttribute PagingDto pagingDto) throws Exception {
		
		System.out.println("registerPOST() 실행됨");
		System.out.println("boardVo: " + boardVo);
		
		MemberVo memberVo = (MemberVo)session.getAttribute("memberVo");
		
		boardVo.setWriter(memberVo.getUserid());
		
		boardService.regist(boardVo);
		
//		return "board/success";
		return "redirect:/board/listAll?page=1&perPage=" + pagingDto.getPerPage();
	}
	
	
	// 글 목록
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public String listAll(Model model, 
							PagingDto pagingDto) throws Exception {
		
		System.out.println("pagingDto: " + pagingDto);
		
		List<BoardVo> list = boardService.listAll(pagingDto);
		
		int totalCount = boardService.listCount(pagingDto);
		
		pagingDto.setTotalCount(totalCount);
		
		model.addAttribute("list", list);
		model.addAttribute("pagingDto", pagingDto);
		
		return "board/listAll";
	}
	
	
	// 글 읽기
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read( @RequestParam("bno") int bno, 
						@ModelAttribute PagingDto pagingDto,
						Model model ) throws Exception {
		
		// -> int bno = Inteer.parseInt(request.parameter("bno"));
		BoardVo boardVo = boardService.read(bno);
		
		model.addAttribute("boardVo", boardVo);
		
		return "board/read";
	}
	
	
	// 글 수정
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify( BoardVo boardVo,
							@ModelAttribute PagingDto pagingDto) throws Exception {
		
		boardService.modify(boardVo);
		System.out.println("boardVo: " + boardVo);
		
		return "redirect:/board/listAll?page=" + pagingDto.getPage() + "&perPage=" + pagingDto.getPerPage();
	}
	
	
	// 글 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("bno") int bno, 
						@ModelAttribute PagingDto pagingDto,
						RedirectAttributes rttr) throws Exception {
		// RedirectAttributes rttr 
		// 리다이렉트시 한번만 사용하고 버림
		
		// 파일 삭제
		List<String> fileNameList = boardService.getAttach(bno);
		FileUploadUtil.delete(fileNameList, uploadPath);
		boardService.delete(bno);	// 게시글 데이터 삭제
		rttr.addFlashAttribute("msg", "delete_success");
		
		return "redirect:/board/listAll?page=" + pagingDto.getPage() + "&perPage=" + pagingDto.getPerPage();
		
	}
	
	
	// 파일명 목록
	@RequestMapping(value = "/getAttach/{bno}", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getAttach(@PathVariable("bno") int bno) throws Exception {
		
		return boardService.getAttach(bno);
	}
	
	
	
}

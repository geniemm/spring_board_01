package com.care.root.board.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.service.BoardFileService;
import com.care.root.board.service.BoardService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("board")
public class BoardController {
	@Autowired BoardService bs;
	
	@GetMapping("boardAllList")
	public String boardAllList(Model model, @RequestParam(required=false, defaultValue="1") int num) {
		Map<String, Object> map = bs.boardAllList(num);
		model.addAttribute("list",map.get("list"));
		model.addAttribute("repeat",map.get("repeat"));
		System.out.println();
		return "board/boardAllList";
		
	}
	
	@GetMapping("writeForm")
	public String writeForm() {
		return "board/write_form";
	}
	@PostMapping("writeSave")
	public void writeSave(BoardDTO dto,
			@RequestParam(required = false) MultipartFile image_file_name
			,HttpServletResponse res) throws IOException {
		
		System.out.println(dto.getId());
		System.out.println(dto.getTitle());
		System.out.println(dto.getContent());
		System.out.println(image_file_name.getOriginalFilename());
		
		String msg = bs.writeSave(dto, image_file_name);
		
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out =res.getWriter();
		out.print(msg);
	}
	
	@GetMapping("content_view")
	public String contentView(@RequestParam int writeNo, Model model) {
		model.addAttribute("dto", bs.contentView(writeNo));
		return "board/contentView";
	}
	
	@GetMapping("download")
	public void download(@RequestParam String name, HttpServletResponse res) throws Exception{
		res.addHeader("Content-disposition","attachment; fileName="+ URLEncoder.encode( name, "utf-8"));
		File file = new File(BoardFileService.IMAGE_REPO+"/"+name);
		FileInputStream in = new FileInputStream(file);
		res.setContentType("text/html; charset=utf-8");
		FileCopyUtils.copy(in, res.getOutputStream());
		in.close();
		
	}
	
	@GetMapping("modify_form")
	public String modifyForm(@RequestParam int writeNo,Model model) {
		model.addAttribute("content",bs.getContent(writeNo));
		return "board/modify_form";
	}
	
	@PostMapping("modify")
	public void modify(BoardDTO dto, 
						@RequestParam MultipartFile file,
						HttpServletResponse res) throws Exception{
		String msg = bs.modify(dto, file);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out =res.getWriter();
		out.print(msg);
	}
	
	@GetMapping("delete")
	public void delete(@RequestParam int writeNo,
						@RequestParam String fileName, HttpServletResponse res) throws Exception{
		String msg = bs.delete(writeNo, fileName);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print( msg );
	}
}











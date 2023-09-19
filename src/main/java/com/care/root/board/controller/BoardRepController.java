package com.care.root.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.root.board.dto.BoardRepDTO;
import com.care.root.board.service.BoardService;
import com.care.root.common.LoginSession;

@RestController
@RequestMapping("board")
public class BoardRepController {
	
	@Autowired BoardService bs;
	
	@PostMapping(value="addReply", produces="application/json; charset=utf-8")
	public void addReply(@RequestBody BoardRepDTO dto, HttpSession session) {
		System.out.println(dto.getTitle());
		System.out.println(dto.getContent());
		System.out.println(dto.getWrite_group());
		System.out.println(session.getAttribute(LoginSession.LOGIN));
		dto.setId((String)session.getAttribute(LoginSession.LOGIN));
		
		bs.addReply(dto);
		
	}
	
	@GetMapping(value="replyData/{write_group}",produces="application/json; charset=utf-8")
	public List<BoardRepDTO> replyData(@PathVariable int write_group) {
		return bs.getRepList(write_group);
	}
	
}

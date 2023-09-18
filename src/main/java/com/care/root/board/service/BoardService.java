package com.care.root.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.care.root.board.dto.BoardDTO;

public interface BoardService {
	public List<BoardDTO> boardAllList();
	public String writeSave(BoardDTO dto, MultipartFile image_file_name);
	public BoardDTO contentView(int writeNo);
	
}

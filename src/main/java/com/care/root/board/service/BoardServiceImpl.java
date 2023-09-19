package com.care.root.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;
import com.care.root.mybatis.board.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardMapper mapper;
	@Autowired
	BoardFileService bfs;

	@Override
	public Map<String, Object> boardAllList(int num) {
		int pageLetter = 3; // 한페이지당 몇개의 글
		int allCount = mapper.selectBoardCount();// 글 총 개수
		int repeat = allCount/pageLetter; // 총페이지 수 
		if(allCount%pageLetter !=0) {
			repeat++;
		}
		int end = num * pageLetter;
		int start = end + 1 - pageLetter;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("repeat", repeat);
		map.put("list", mapper.boardAllList(start, end));

		return map;
	}

	@Override
	public String writeSave(BoardDTO dto, MultipartFile image_file_name) {
		if (image_file_name.isEmpty()) {
			// 파일이 없는경우
			dto.setImageFileName("nan");
		} else {
			// 파일이 존재하는 경우
			dto.setImageFileName(bfs.saveFile(image_file_name));

		}
		int result = mapper.writeSave(dto);
		String msg = "", url = "";
		if (result == 1) {
			// 디비에 성공적으로 저장
			msg = "등록 완료";
			url = "/root/board/boardAllList";
			// root 대신request.getContextPath()이용해서 절대경로로 사용가능

		} else {
			// 디비 저장 실패
			msg = "문제가 발생했습니다.";
			url = "/root/board/writeForm";
		}

		return bfs.getMessage(msg, url);
	}

	@Override
	public BoardDTO contentView(int writeNo) {
		upHit(writeNo);//조회수
		return mapper.getContent(writeNo);
		
	}
	public void upHit(int writeNo) {
		mapper.upHit(writeNo);
	}
	
	@Override
	public BoardDTO getContent(int writeNo) {
		return mapper.getContent(writeNo);
	}
	
	@Override
	public String modify(BoardDTO dto, MultipartFile file) {
		String originName = null;
		if(!file.isEmpty()) {//수정됨
			originName = dto.getImageFileName();
			dto.setImageFileName(bfs.saveFile(file)); // 이렇게 추가해버리면 
			
		}
		int result = mapper.modify(dto);
		String msg="",url="";
		if(result==1) {
			// 기존이미지 삭제 originName
			bfs.deleteImage(originName);
			msg="수정되었습니다!";
			url="/root/board/content_view?writeNo="+dto.getWriteNo();
		}else {
			// 수정된 이미지 삭제 dto.getImageFileName
			bfs.deleteImage(dto.getImageFileName());
			msg="문제 발생!";
			url="/root/board/modify_form?writeNo="+dto.getWriteNo();
		}
		return bfs.getMessage(msg, url);
	}
	
	@Override
	public String delete(int writeNo, String fileName) {
		int result = mapper.delete(writeNo);
		
		String msg="",url="";
		if(result==1) {
			bfs.deleteImage(fileName);
			msg="삭제되었습니다!";
			url="/root/board/boardAllList";
		}else {
			msg="문제 발생!";
			url="/root/board/content_view?writeNo="+writeNo;
		}
		return bfs.getMessage(msg, url);
	}
	
	@Override
	public void addReply(BoardRepDTO dto) {
		mapper.addReply(dto);
			
	}

	public List<BoardRepDTO> getRepList(int write_group) {

		return mapper.getRepList(write_group);
	}

}

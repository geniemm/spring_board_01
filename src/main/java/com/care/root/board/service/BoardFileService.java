package com.care.root.board.service;

import org.springframework.web.multipart.MultipartFile;

public interface BoardFileService {
	public String IMAGE_REPO = "c:/spring/image_repo2";
	public String getMessage(String msg, String url);
	public String saveFile(MultipartFile image_file_name);
	public void deleteImage( String fileName );
	
	

}

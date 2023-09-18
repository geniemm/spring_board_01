package com.care.root.board.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BoardFileServiceImpl implements BoardFileService{
	
	@Override
	public String getMessage(String msg, String url) {
		String message = "<script>alert('"+msg+"');";
		message += "location.href='"+url+"';</script>";
		return message;
	}
	
	@Override
	public String saveFile(MultipartFile image_file_name) {
		SimpleDateFormat fo = new SimpleDateFormat("yyyyMMddHHmmss-");
		String sysFileName = fo.format(new Date());
		sysFileName = sysFileName + image_file_name.getOriginalFilename();
		File saveFile = new File(IMAGE_REPO +"/"+sysFileName);
		try {
			image_file_name.transferTo(saveFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysFileName;
	}
}
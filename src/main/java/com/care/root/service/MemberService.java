package com.care.root.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.care.root.dto.MemberDTO;

public interface MemberService {
	public int logChk(String id, String pw);
	public List<MemberDTO> getList();
	public void register(MemberDTO dto, String[] addr);
	public Map<String, Object> getMember(String id);
	public void keepLogin(String sessionId, String id);
	public MemberDTO getUserSessionId( String sessionId );
}















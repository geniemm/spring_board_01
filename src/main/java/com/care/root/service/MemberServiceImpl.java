package com.care.root.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.care.root.dto.MemberDTO;
import com.care.root.mybatis.MemberMapper;
@Service
public class MemberServiceImpl 
					implements MemberService{
	@Autowired MemberMapper mapper;

	BCryptPasswordEncoder encoder;
	public MemberServiceImpl() {
		encoder = new BCryptPasswordEncoder();
	}
	
	public int logChk(String id, String pw) {
		MemberDTO dto = mapper.getMember( id );
		int result = 1;
		if(dto != null) {
			System.out.println( dto.getId() );
			System.out.println( dto.getPw() );
			System.out.println( dto.getAddr() );
			if( encoder.matches(pw, dto.getPw() ) 
						|| pw.equals( dto.getPw() ) ) {
				
				result = 0;
			}
		}
		return result;
	}
	public List<MemberDTO> getList(){
		List<MemberDTO> list = null;
		try {
			list = mapper.getList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public void register(MemberDTO dto, String[] addr) {
		String ad = "";
		for(String a : addr) {
			ad += a +"/";
		}
		dto.setAddr( ad );
		System.out.println("평문 : "+ dto.getPw() );
		System.out.println("암호화 : "+ encoder.encode(dto.getPw() ) );
		
		dto.setPw( encoder.encode(dto.getPw()) );
		try {
			mapper.register( dto );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Map<String, Object> getMember(String id){
		MemberDTO dto = mapper.getMember(id);
		System.out.println( dto.getId() );
		System.out.println( dto.getPw() );
		System.out.println( dto.getAddr() );
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("dto", dto);
		String[] addr = dto.getAddr().split("/");
		if( addr.length > 1) {
			map.put("addr1", addr[0]);
			map.put("addr2", addr[1]);
			map.put("addr3", addr[2]);
		}
		return map;
	}
	public void keepLogin(String sessionId, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionId", sessionId);
		map.put("id", id);
		mapper.keepLogin( map );
	}
	public MemberDTO getUserSessionId( String sessionId ) {
		return mapper.getUserSessionId( sessionId );
	}
}













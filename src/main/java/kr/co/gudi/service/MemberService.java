package kr.co.gudi.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.gudi.dao.MemberDAO;
import kr.co.gudi.dto.MemberDTO;

// Service 로 활용 된다는 뜻으로 어노테이션을 붙여 준다.
@Service
public class MemberService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired MemberDAO dao;
	
	public String join(HashMap<String, String> params) {
		String msg="회원가입에 실패 했습니다.";
		int row = dao.join(params);
		if(row>0) {
			msg = "회원가입에 성공 했습니다.";
		}		
		return msg;
	}

	public String login(String id, String pw) {	
		return dao.login(id,pw);
	}

	public ArrayList<MemberDTO> list() {
		return dao.list();
	}

	public void del(String id) {
		dao.del(id);		
	}

	public MemberDTO detail(String id) {
		return dao.detail(id);
	}

}

package kr.co.gudi.dao;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.gudi.dto.MemberDTO;

public interface MemberDAO {

	int join(HashMap<String, String> params);

	String login(String id, String pw);

	ArrayList<MemberDTO> list();

	void del(String id);

	MemberDTO detail(String id);

}

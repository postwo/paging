package kr.co.gudi.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.gudi.dto.MemberDTO;
import kr.co.gudi.service.MemberService;

@Controller
public class MemberController {
	
	//@Service 어노테이션이 붙으면 객체화 를 하나하나 시키지 않고 사용 할 수 있다. 
	@Autowired MemberService service;	
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String index() {
		logger.info("login page 이동");
		return "index";
	}
	
	@ RequestMapping(value="/joinForm", method = RequestMethod.GET)
	public String joinForm() {
		return "joinForm";
	}
	
	@RequestMapping(value="/join", method = RequestMethod.POST)
	public String join(Model model, @RequestParam HashMap<String, String> params) {
		logger.info("params : "+params);
		String msg = service.join(params);
		model.addAttribute("msg", msg);		
		return "index";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(Model model, HttpSession session,
			@RequestParam String id, @RequestParam String pw) {
		
		logger.info(id+" / "+pw);
		boolean success = service.login(id,pw) != null ? true:false;
		logger.info("login success : "+success);
		String page = "index";
		
		if(success) { // 로그인 성공시...
			// session 에 로그인이 성공했다는 표시로 id 를 저장한다.
			session.setAttribute("loginId", id);
			// list.jsp 로 이동한다.(list.jsp 에는 회원 정보 리스트가 나타나야 한다.)
			//response.sendRedirect("/gudi/list");
			page = "redirect:/list"; // 성공하면 ->"/list" 요청을 보내도록 한다.
			
		}else { // 로그인 실패시...
			// msg 에 "아이디 또는 비밀번호를 확인하세요" 라는 메시지를 담는다.
			model.addAttribute("msg", "아이디 또는 비밀번호를 확인하세요!");
		}	
				
		return page;
	}
	
	@RequestMapping(value="/list")
	public String list(Model model, HttpSession session) {		
		logger.info("list 요청 받음");
		String page = "index";		
		String msg = "로그인이 필요한 서비스 입니다.";
		
		if(session.getAttribute("loginId") != null) { // 로그인 했을 경우만 내용을 실행
			page = "list";
			msg = "";	
			model.addAttribute("list", service.list());
		}
		
		model.addAttribute("msg", msg);
		
		return page;
	}
	
	@RequestMapping(value="del",method = RequestMethod.GET)
	public String del(@RequestParam String id, HttpSession session, Model model) {
		logger.info("id : "+id);
		String page = "index";
		if(session.getAttribute("loginId")!=null) {
			page = "redirect:/list";
			service.del(id);
		}else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
		}
				
		return page;
	}
	
	@RequestMapping(value="/detail", method = RequestMethod.GET)
	public String detail(Model model, HttpSession session, @RequestParam String id) {
		
		logger.info("상세보기 요청 : "+id);		
		String page = "redirect:/list";
		
		if(session.getAttribute("loginId") != null) {		
			MemberDTO dto = service.detail(id);
			if(dto != null) {
				page = "detail";
				model.addAttribute("member", dto);
			}
			
		}else {
			page = "index";
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
		}
		
		return page;
	}
	
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginId");
		return "redirect:/";
	}

}















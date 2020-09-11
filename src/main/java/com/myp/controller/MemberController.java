package com.myp.controller;

import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myp.domain.MemberVO;
import com.myp.service.MemberService;
import com.mysql.fabric.xmlrpc.base.Member;

@Controller
public class MemberController {
	
	
	
	@Inject
	MemberService service;
	
	@Inject
	BCryptPasswordEncoder pwdEncoder;
	
	@RequestMapping(value = "/register")
	public String getRegister(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		String url;
		System.out.println(member);
		
		if(member != null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 중입니다. 로그아웃 후 입력해주세요');</script>");
			out.flush();
			url = "redirect:/";
			
		} else {
			url = "/register";
		}
		return url;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String postRegister(MemberVO vo) throws Exception {
		int result = service.idChk(vo);
		try {
			if(result == 1) {
				return "/register";
			}else if(result == 0) {
				service.register(vo);
			}
			// 요기에서~ 입력된 아이디가 존재한다면 -> 다시 회원가입 페이지로 돌아가기 
			// 존재하지 않는다면 -> register
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return "redirect:/";
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception{
		
		HttpSession session = req.getSession();
		MemberVO login = service.login(vo);
		
		if(login == null) {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
		}else {
			session.setAttribute("member", login);
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/memberUpdateView", method = RequestMethod.GET)
	public String registerUpdateView() throws Exception{
		
		return "/memberUpdateView";
	}

	@RequestMapping(value="/memberUpdate", method = RequestMethod.POST)
	public String registerUpdate(MemberVO vo, HttpSession session) throws Exception{
		
		service.memberUpdate(vo);
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/memberDeleteView", method = RequestMethod.GET)
	public String delteView() throws Exception{
		
		return "/memberDeleteView";
	}
	
	@RequestMapping(value="/memberDelete", method = RequestMethod.POST)
	public String memberDelete(MemberVO vo, HttpSession session, RedirectAttributes rttr) throws Exception {
		
		MemberVO member = (MemberVO) session.getAttribute("member");
		
		String sessionPass = member.getUserpass();
		String voPass = vo.getUserpass();
		
		if(!(sessionPass.contentEquals(voPass))) {
			rttr.addFlashAttribute("msg", false);
			return "redirect:/memberDeleteView";
		}
		service.memberDelete(vo);
		session.invalidate();
		
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping(value="/passChk", method = RequestMethod.POST)
	public int passChk(MemberVO vo) throws Exception {
		int result = service.passChk(vo);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/idChk", method = RequestMethod.POST)
	public int idChk(MemberVO vo) throws Exception {
		int result = service.idChk(vo);
		return result;
	}
	
}

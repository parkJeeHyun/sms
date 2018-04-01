package com.cnt.sms.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cnt.sms.dao.UserDAO;
import com.cnt.sms.model.User;
import com.cnt.sms.service.TeamService;

@Controller
public class TeamController {
	
	//@Autowired
	private TeamService service;
	private UserDAO userDao = new UserDAO();
	
	@RequestMapping(value = "/teamController/myPage.do", method = RequestMethod.GET)
	public String getList(HttpServletRequest request)
			throws UnsupportedEncodingException {
		User user=new User();
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(60*60);
		request.getSession().setAttribute("userSession", user);
		request.getSession().setAttribute("teamName", user.getTeamName());
		request.getSession().setAttribute("id", user.getUserID());
			
		return "mypage";
		
	}
}

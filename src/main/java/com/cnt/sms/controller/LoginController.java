package com.cnt.sms.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.cnt.sms.dao.UserDAO;
import com.cnt.sms.model.User;
import com.cnt.sms.service.LoginService;

@Controller
public class LoginController implements ServletContextAware{

	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	
	@Autowired	
	private LoginService service;
	private UserDAO userDAO = new UserDAO();

	private Object servletContext;
	
	@RequestMapping(value = "/loginController/login.do", method = RequestMethod.POST)
	public String login(HttpServletRequest request)
			throws UnsupportedEncodingException {
		
		request.setCharacterEncoding("utf-8");
		String userID = request.getParameter("userID");
		String userPassword = request.getParameter("userPassword");
		
		User user = null;
		try {
			user = userDAO.login(userID, userPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("유저아이디 : " + userID);
		if (user == null) {
				return "alert";
		} else {
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(60*60);
			request.getSession().setAttribute("userSession", user);
			request.getSession().setAttribute("teamName", user.getTeamName());
			request.getSession().setAttribute("id", user.getUserID());
			return "NewFile";
		}
	}
	
	@RequestMapping(value = "/loginController/signupPage.do")
	public String signupPage(HttpServletRequest request)
			throws UnsupportedEncodingException {
			return "signup";
	}
	
//	@RequestMapping(value = "/loginController/signup.do", method = RequestMethod.POST)
//	public String signup(HttpServletRequest request)
//			throws UnsupportedEncodingException {
//		request.setCharacterEncoding("utf-8");
//		String userID = request.getParameter("userID");
//		String teamName = request.getParameter("teamName");
//		String userPassword = request.getParameter("userPassword");
//		String uploadFile = "123";         //임시 데이터
//		try {
//		service.signup(userID, teamName, userPassword,uploadFile);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//			return "redirect:/";
//	}
	
//	@RequestMapping(value = "/loginController/signup.do", method = RequestMethod.POST)
//	public String signup(HttpServletRequest request)
//			throws IOException {	
//		String savePath = "C:/Users/anhc/Documents/workspace-sts-3.7.3.RELEASE/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SMS/image";  	
//		int sizeLimit = 1024*1024*5;
//		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
//		// 웹서버 컨테이너 경로
//		String userID = multi.getParameter("userID");
//		String teamName = multi.getParameter("teamName");
//		String userPassword = multi.getParameter("userPassword");
//		// 업로드 파일명s
//	    String file = multi.getFilesystemName("uploadFile");
//	    String uploadFile = savePath + "/" + file;
//		try {
//		service.signup(userID, teamName, userPassword,uploadFile);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//			return "redirect:/";
//
//	}
	
	@RequestMapping(value = "/loginController/signup.do", method = RequestMethod.POST)
	public String signup(
		@RequestParam("userID") String userID,
		// MultipartFile이 제공하는 메서드를 이용해서 업로드 데이터 접근	
		@RequestParam("teamName") String teamName,
		@RequestParam("userPassword") String userPassword,
		@RequestParam("uploadFile") MultipartFile file) throws IllegalStateException, IOException {
		String fileOrgName = file.getOriginalFilename();
		String fileNewName = System.currentTimeMillis() + file.getSize() + fileOrgName;
		String path = "C:/Users/anhc/Documents/workspace-sts-3.7.3.RELEASE/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/sms/image";
		File f = new File(path + File.separator + fileNewName);
		file.transferTo(f);
		try {
			service.signup(userID, teamName, userPassword, fileOrgName, fileNewName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return "redirect:/";
	}
	
	@RequestMapping(value = "/loginController/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("userSession");
		return "redirect:/";
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		this.servletContext = servletContext;
		
	}
}
package com.cnt.sms.service;

import java.sql.SQLException;
import org.springframework.stereotype.Service;

import com.cnt.sms.dao.UserDAO;
import com.cnt.sms.model.User;

@Service
public class LoginService {
	UserDAO userDAO = new UserDAO();
	User user = new User();

	public void signup(String userID, String teamName, String userPassword, String fileOrgName, String fileNewName) throws SQLException {
		user.setUserID(userID);
		user.setTeamName(teamName);
		user.setPassword(userPassword);
		user.setFileOrgName(fileOrgName);
		user.setFileNewName(fileNewName);		
		userDAO.signup(user);
	}
}
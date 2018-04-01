package com.cnt.sms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cnt.sms.model.User;

public class UserDAO {
	private final static String URL = "jdbc:mysql://localhost:3306/sms";
	private final static String ID = "root";
	private final static String PASSWORD = "tiger";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, ID, PASSWORD);
	}
	
	private void closeConnection(Connection connection, Statement statement, ResultSet resultset) throws SQLException {
		if(connection == null) {
			return;
		}
		connection.close();
		statement.close();
		resultset.close();
	}
	
	
	@SuppressWarnings("resource")
	public User login(String userID, String userPassword) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from user";
		User user = null;
		
		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			String id = rs.getString("userId");
			String password = rs.getString("password");
			
			if(id.equals(userID) && password.equals(userPassword)) {
				sql = "select * from user where userId = " + id;
				pstmt = conn.prepareStatement(sql);
				user = new User();
				user.setUserID(rs.getString("userId"));
				user.setTeamName(rs.getString("teamName"));
				user.setPassword(rs.getString("password"));
				user.setFileOrgName(rs.getString("fileOrgName"));
				user.setFileNewName(rs.getString("fileNewName"));
				closeConnection(conn, pstmt, rs);
				return user;
			}
		}
		closeConnection(conn, pstmt, rs);
		return null;
	}

	public void signup(User user) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO user " + "(userId, teamName, password, fileOrgName, fileNewName) values(?,?,?,?,?)";
		
		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getUserID());
		pstmt.setString(2, user.getTeamName());
		pstmt.setString(3, user.getPassword());
		pstmt.setString(4, user.getFileOrgName());
		pstmt.setString(5, user.getFileNewName());
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}
}
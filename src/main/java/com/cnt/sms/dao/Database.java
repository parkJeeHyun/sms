package com.cnt.sms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/sms";
	private static final String ID = "root";
	private static final String PASSWORD = "tiger";
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private static Database database = null;
	public static Database getInstance(){
		if(database == null){
			database = new Database();
		}
		return database;
	}
	public void connection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		this.conn = DriverManager.getConnection(URL,ID,PASSWORD);
	}
	public ResultSet select(String tableName) throws SQLException{
		String sql = "select * from " + tableName;
		this.pstmt =  this.conn.prepareStatement(sql);
		this.rs = this.pstmt.executeQuery();
		return rs;
	}
	public PreparedStatement insert(String tableName,int parameter) throws SQLException{
		String sql = "";
		if(parameter==2){
			sql = "insert into " + tableName + " values(?,?)";
		}else if(parameter==3){
			sql = "insert into " + tableName + " values(?,?,?)";
		}else if(parameter==4){
			sql = "insert into " + tableName + " values(?,?,?,?)";
		}
		this.pstmt = this.conn.prepareStatement(sql);
		return pstmt;
	}
}

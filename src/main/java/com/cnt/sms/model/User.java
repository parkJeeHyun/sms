package com.cnt.sms.model;

public class User {
//	private static User instance;
//	public static User getInstance(){
//		if(instance == null){
//			instance = new User();
//		}
//		return instance;
//	}
	private String userID;
	private String teamName;
	private String password;
	private String fileOrgName;
	private String fileNewName;
	public User(){
	
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFileOrgName() {
		return fileOrgName;
	}
	public void setFileOrgName(String fileOrgName) {
		this.fileOrgName = fileOrgName;
	}
	public String getFileNewName() {
		return fileNewName;
	}
	public void setFileNewName(String fileNewName) {
		this.fileNewName = fileNewName;
	}
	
}


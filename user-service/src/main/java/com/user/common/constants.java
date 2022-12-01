package com.user.common;

public class constants {

	//SQL Queries
	public static final String getAllUserRole = "SELECT * FROM USER_ROLE";
	public static final String getUserRoleById = "SELECT * FROM USER_ROLE WHERE ID=?";
	public static final String saveUserRole = "INSERT INTO USER_ROLE VALUES(?,?,?,?)";
	public static final String deactivateUserRole = "UPDATE USER_ROLE SET IS_ACTIVE =? WHERE ID=?";
	public static final String updateUserRole = "UPDATE USER_ROLE SET ROLE_NAME=?, ROLE_CODE=?, IS_ACTIVE=? WHERE ID = ?";

	
	//User Messages
	
}

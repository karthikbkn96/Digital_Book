package com.user.service;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.user.modal.userRole;

@Component
public interface userRoleService {
	 
	JSONObject getAllUserRole();
	
	JSONObject getUserRoleById(@PathVariable int id) throws Exception;
	
	JSONObject saveUserRole(@RequestBody userRole userRole) throws Exception;
	
	JSONObject deactivateUserRole(@PathVariable int id) throws Exception;
	
	JSONObject updateUserRole(@RequestBody userRole m, @PathVariable int id) throws Exception;
	
}

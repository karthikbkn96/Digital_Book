package com.user.serviceimpl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.user.exception.customCheckedException;
import com.user.modal.userRole;
import com.user.service.userRoleService;
import com.user.userDAO.userRoleDAO;

@Service
public class userRoleServiceImpl implements userRoleService {

	@Autowired
	userRoleDAO roleDAO;

	@Override
	public JSONObject getAllUserRole() {
		JSONObject obj = new JSONObject();
		obj.append("result", new JSONArray(roleDAO.getAllUserRole()));
		obj.append("status", "1");
		return obj;
	}

	@Override
	public JSONObject getUserRoleById(int id) throws customCheckedException {
		JSONObject obj = new JSONObject();
		obj.append("result", new JSONObject(roleDAO.getUserRoleById(id)));
		obj.append("status", "1");

		return obj;
	}

	@Override
	public JSONObject saveUserRole(userRole userRole) throws Exception {
		JSONObject obj = new JSONObject();
		if (roleDAO.saveUserRole(userRole) > 0) {
			obj.append("result", new JSONObject(userRole));
			obj.append("status", "1");
			return obj;
		}
		obj.append("status", "0");
		return obj;
	}

	@Override
	public JSONObject deactivateUserRole(int id) throws Exception {
		JSONObject obj = new JSONObject();
		if (roleDAO.deactivateUserRole(id) > 0) {
			obj.append("status", "1");
			return obj;
		}
		obj.append("status", "0");
		return obj;
	}

	@Override
	public JSONObject updateUserRole(userRole userRole, int id) throws Exception {
		JSONObject obj = new JSONObject();
		if (roleDAO.updateUserRole(userRole, id) > 0) {
			obj.append("result", new JSONObject(userRole));
			obj.append("status", "1");
			return obj;
		}
		obj.append("status", "0");
		return obj;
	}

}

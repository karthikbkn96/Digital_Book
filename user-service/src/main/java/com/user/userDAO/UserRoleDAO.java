package com.user.userDAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.user.common.constants;
import com.user.modal.userRole;

@Repository
public class userRoleDAO {

	@Autowired
	JdbcTemplate jdbctemplate;

	public List<userRole> getAllUserRole() {

		List<userRole> userRoleList = jdbctemplate.query(constants.getAllUserRole,
				(role, rowNum) -> new userRole(role.getInt("id"), role.getString("role_name"),
						role.getString("role_code"), role.getInt("is_active")));

		return userRoleList;
	}

	public userRole getUserRoleById(int id) {
		userRole userRole = jdbctemplate.queryForObject(constants.getUserRoleById, new Object[] { id },
				new BeanPropertyRowMapper<>(userRole.class));
		return userRole;
	}

	public int saveUserRole(userRole userRole) {
		return jdbctemplate.update(constants.saveUserRole, "0", userRole.getRoleName(), userRole.getRoleCode(), userRole.getIsActive());
	}

	public int deactivateUserRole(int id) {
		return jdbctemplate.update(constants.deactivateUserRole, 0, id);
	}

	public int updateUserRole(userRole userRole, int id) {
		return jdbctemplate.update(constants.updateUserRole, userRole.getRoleName(), userRole.getRoleCode(), userRole.getIsActive(), id);
	}

}

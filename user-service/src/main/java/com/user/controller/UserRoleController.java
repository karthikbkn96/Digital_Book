package com.user.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.common.constantRoutes;
import com.user.exception.customCheckedException;
import com.user.modal.userRole;
import com.user.service.userRoleService;

@RestController
@RequestMapping("/userRole")
public class userRoleController {

	private static Logger log = LogManager.getLogger(userRoleController.class.getName());
		
	@Autowired
	userRoleService service;

	@GetMapping(constantRoutes.getAllUserRole)
	public String getAllUserRole() {
		return service.getAllUserRole().toString();
	}

	@GetMapping(constantRoutes.getUserRoleById)
	public String getUserRoleById(@PathVariable int id) throws Exception {
		if (log.isDebugEnabled()) {
			log.info("Get User By ID is " + id);
		}
		try {
			return service.getUserRoleById(id).toString();
		}catch(Exception e) {
			throw new customCheckedException("Requested UserRole is not present", 500);
		}
	}

	@PostMapping(constantRoutes.saveUserRole)
	public String saveUserRole(@RequestBody userRole userRole) throws Exception {
		if (log.isDebugEnabled()) {
			log.info("Save UserRole Object " + userRole);
		}
		return service.saveUserRole(userRole).toString();
	}

	@DeleteMapping(constantRoutes.deactivateUserRole)
	public String deactivateUserRole(@PathVariable int id) throws Exception {
		if (log.isDebugEnabled()) {
			log.info("Deactivate By ID is " + id);
		}
		return service.deactivateUserRole(id).toString();
	}

	@PutMapping(constantRoutes.updateUserRole)
	public String updateUserRole(@RequestBody userRole m, @PathVariable int id) throws Exception {
		if (log.isDebugEnabled()) {
			log.info("Update userRole Object is" + m + " By ID is " + id);
		}
		return service.updateUserRole(m, id).toString();
	}

}

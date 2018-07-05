package com.java.hibernate.core.validations;

import com.java.hibernate.core.entity.unused.Employee;
import com.java.hibernate.core.entity.unused.User;

public class UserDAO {
	public static boolean validate(String username, String password, User usr) {
		if (usr.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean validateid(Integer id, Employee emp) {

		if (id.equals(emp.getId())) {
			return true;
		} else {
			return false;
		}
	}
}

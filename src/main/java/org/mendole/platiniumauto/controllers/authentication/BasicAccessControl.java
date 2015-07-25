package org.mendole.platiniumauto.controllers.authentication;

import org.mendole.platiniumauto.caching.ConnectedUsers;
import org.mendole.platiniumauto.controllers.UserController;
import org.mendole.platiniumauto.persistence.model.User;
import org.mendole.platiniumauto.security.SecurityUtil;
import org.mendole.platiniumauto.ui.authentication.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BasicAccessControl implements AccessControl {

	@Autowired
	UserController userController;

	@Override
	public boolean signIn(String email, String password) {
		if (email == null || email.isEmpty())
			return false;
		User user = userController.getByEmail(email);
		if (user != null
				&& SecurityUtil.hashPassword(password).equals(
						user.getPassword())) {
			CurrentUser.set(user);
			ConnectedUsers.INSTANCE.addUser(user);
			return true;
		}
		return false;
	}

	@Override
	public boolean isUserSignedIn() {
		return !(CurrentUser.get() == null);
	}

	@Override
	public boolean isUserInRole(String role) {
		if ("admin".equals(role)) {
			// Only the "admin" user is in the "admin" role
			return getPrincipalName().equals("admin");
		}

		// All users are in all non-admin roles
		return true;
	}

	@Override
	public String getPrincipalName() {
		return CurrentUser.get().getName();
	}

}
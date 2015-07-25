package org.mendole.platiniumauto.controllers.registration;

import org.mendole.platiniumauto.controllers.UserController;
import org.mendole.platiniumauto.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationControlImpl implements RegistrationControl {

	
	@Autowired
	UserController userController;

	@Override
	public boolean isValidUser(User guest) throws NullPointerException {

		if (userController.getByName(guest.getName()) != null
				|| userController.getByEmail(guest.getEmail()) != null)
			return false;
		return true;

	}

	@Override
	public User registerUser(User guest) {
		return userController.addUser(guest);
	}

}

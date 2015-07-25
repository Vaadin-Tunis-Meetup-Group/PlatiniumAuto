package org.mendole.platiniumauto.controllers.registration;

import org.mendole.platiniumauto.persistence.model.User;

public interface RegistrationControl {
	public boolean isValidUser(User guest) throws NullPointerException;

	public User registerUser(User guest);

}

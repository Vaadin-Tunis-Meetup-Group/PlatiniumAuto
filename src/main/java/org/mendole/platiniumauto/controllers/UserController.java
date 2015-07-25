package org.mendole.platiniumauto.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.mendole.platiniumauto.persistence.model.User;
import org.mendole.platiniumauto.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

	// ------------------------
	// PUBLIC METHODS
	// ------------------------

	/**
	 * /create --> Create a new user and save it in the database.
	 * 
	 * @param email
	 *            User's email
	 * @param name
	 *            User's name
	 * @return A string describing if the user is succesfully created or not.
	 */
	@RequestMapping("/addUser")
	@ResponseBody
	public User addUser(User user) {
		try {
			userRepository.save(user);
		} catch (Exception ex) {
			return null;
		}
		return user;
	}

	/**
	 * /delete --> Delete the user having the passed id.
	 * 
	 * @param id
	 *            The id of the user to delete
	 * @return A string describing if the user is succesfully deleted or not.
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(User user) {
		try {
			userRepository.delete(user);
		} catch (Exception ex) {
			return "Error deleting the user:" + ex.toString();
		}
		return "User succesfully deleted!";
	}
	
	@RequestMapping("/get-all-users")
	@ResponseBody
	public List<User> getAllUsers() {
		Iterator<User> usersIter = userRepository.findAll().iterator();
		List<User> users = new ArrayList<User>();
		while (usersIter.hasNext()) {
			User user = (User) usersIter.next();
			users.add(user);
		}
		return users;
	}

	/**
	 * /get-by-email --> Return the id for the user having the passed email.
	 * 
	 * @param email
	 *            The email to search in the database.
	 * @return The user id or a message error if the user is not found.
	 */
	@RequestMapping("/get-by-email")
	@ResponseBody
	public User getByEmail(String email) {
		String userId;
		User user;
		try {
			user = userRepository.findByEmail(email);
			userId = String.valueOf(user.getId());
		} catch (Exception ex) {
			return null;
		}
		return user;
	}

	@RequestMapping("/get-by-name")
	@ResponseBody
	public User getByName(String name) {
		String userId;
		User user;
		try {
			user = userRepository.findByName(name);
			userId = String.valueOf(user.getId());
		} catch (Exception ex) {
			return null;
		}
		return user;
	}

	/**
	 * /update --> Update the email and the name for the user in the database
	 * having the passed id.
	 * 
	 * @param id
	 *            The id for the user to update.
	 * @param email
	 *            The new email.
	 * @param name
	 *            The new name.
	 * @return A string describing if the user is succesfully updated or not.
	 */
	@RequestMapping("/update")
	@ResponseBody
	public String updateUser(long id, String email, String name) {
		try {
			User user = userRepository.findOne(id);
			user.setEmail(email);
			user.setName(name);
			userRepository.save(user);
		} catch (Exception ex) {
			return "Error updating the user: " + ex.toString();
		}
		return "User succesfully updated!";
	}

	// ------------------------
	// PRIVATE FIELDS
	// ------------------------

	@Autowired
	private UserRepository userRepository;

} // class UserController

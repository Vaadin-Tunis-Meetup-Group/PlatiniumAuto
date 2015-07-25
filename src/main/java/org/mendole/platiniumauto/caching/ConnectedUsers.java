package org.mendole.platiniumauto.caching;

import java.util.HashSet;
import java.util.Set;

import org.mendole.platiniumauto.persistence.model.User;

public enum ConnectedUsers {
	INSTANCE;

	private static Set<User> connectedUsers = new HashSet<User>();

	public static void addUser(User user) {
		getConnectedUsers().add(user);
	}

	public static void removeUser(User user) {
		getConnectedUsers().remove(user);
	}

	public static Set<User> getConnectedUsers() {
		return connectedUsers;
	}
}

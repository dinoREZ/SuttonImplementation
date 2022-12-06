package de.fhws.fiw.fds.sutton.client.auth;

public enum ClientUser {
	ANONYMOUS("", "");

	private final String userName;

	private final String password;

	ClientUser(final String userName, final String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
}
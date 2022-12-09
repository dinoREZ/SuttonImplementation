package de.fhws.fiw.fds.sutton.client.utils;

public class BearerAuthentication implements Authentication {
	private final String token;

	public BearerAuthentication(String token) {
		this.token = token;
	}

	@Override
	public String authenticationHeader() {
		return "Bearer " + token;
	}
}

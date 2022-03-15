package main.beans;

import lombok.Data;
import main.entity.User;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 5/1/2019
 * Github: https://github.com/loda-kun
 */
@Data
public class LoginResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LoginResponse(String accessToken,User user) {
		this.setUser(user);
        this.setAccessToken(accessToken);
    }

	public LoginResponse(String accessToken) {
		this.setAccessToken(accessToken);
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	@Override
	public String toString() {
		return "LoginResponse [accessToken=" + accessToken + ", tokenType=" + tokenType + "]";
	}
	
	
    
}
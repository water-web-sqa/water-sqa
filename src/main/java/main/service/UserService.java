package main.service;

import main.beans.LoginResponse;
import main.beans.UserBeans;
import main.entity.User;

import java.util.List;

public interface UserService {
	/**
	 * 
	 * @param username
	 * @return
	 */
	User getUserInfoByName(String username);
}

package com.fsl.ecart.service;

import java.util.List;

import com.fsl.ecart.user.User;

public interface UserService {

	User findById(int id);

	User findByUserName(String userName);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserByUserName(String sso);

	List<User> findAllUsers();

	boolean isUserSSOUnique(Integer id, String userName);

}

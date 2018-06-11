package com.fsl.ecart.dao;

import java.util.List;

import com.fsl.ecart.user.User;

public interface UserDAO {

	User findById(int id);

	User findByUserName(String userName);

	void save(User user);

	void deleteByUserName(String userName);

	List<User> findAllUsers();

}

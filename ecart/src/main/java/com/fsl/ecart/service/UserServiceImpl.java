package com.fsl.ecart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsl.ecart.dao.UserDAO;
import com.fsl.ecart.user.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO dao;

	// @Autowired
	// private PasswordEncoder passwordEncoder;

	public User findById(int id) {
		return dao.findById(id);
	}

	public User findByUserName(String userName) {
		return dao.findByUserName(userName);
	}

	public void saveUser(User user) {
		// user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setPassword(user.getPassword());
		dao.save(user);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate
	 * update explicitly. Just fetch the entity from db and update it with proper
	 * values within transaction. It will be updated in db once transaction ends.
	 */
	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
		if (entity != null) {
			entity.setUserName(user.getUserName());
			if (!user.getPassword().equals(entity.getPassword())) {
				// entity.setPassword(passwordEncoder.encode(user.getPassword()));
				entity.setPassword(user.getPassword());
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
		}
	}

	public void deleteUserByUserName(String userName) {
		dao.deleteByUserName(userName);
	}

	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	public boolean isUserSSOUnique(Integer id, String userName) {
		User user = findByUserName(userName);
		return (user == null || ((id != null) && (user.getId() == id)));
	}

}

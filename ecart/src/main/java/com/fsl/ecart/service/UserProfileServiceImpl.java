package com.fsl.ecart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsl.ecart.dao.UserProfileDAO;
import com.fsl.ecart.user.UserProfile;

@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	UserProfileDAO dao;

	@Override
	public UserProfile findById(int id) {
		return dao.findById(id);
	}

	@Override
	public UserProfile findByType(String type) {
		return dao.findByType(type);
	}

	@Override
	public List<UserProfile> findAll() {
		return dao.findAll();
	}

}

package com.fsl.ecart.dao;

import java.util.List;

import com.fsl.ecart.user.UserProfile;

public interface UserProfileDAO {
	List<UserProfile> findAll();

	UserProfile findByType(String type);

	UserProfile findById(int id);
}

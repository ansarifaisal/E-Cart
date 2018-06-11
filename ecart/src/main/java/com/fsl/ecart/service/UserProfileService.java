package com.fsl.ecart.service;

import java.util.List;

import com.fsl.ecart.user.UserProfile;

public interface UserProfileService {
	
	UserProfile findById(int id);

	UserProfile findByType(String type);

	List<UserProfile> findAll();
}

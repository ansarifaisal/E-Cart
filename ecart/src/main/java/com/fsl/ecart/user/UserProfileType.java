package com.fsl.ecart.user;

import java.io.Serializable;

public enum UserProfileType implements Serializable{

	USER("USER"), DBA("DBA"), ADMIN("ADMIN");

	String profileType;

	private UserProfileType(String userProfileType) {
		this.profileType = userProfileType;
	}

	public String getUserProfileType() {
		return profileType;
	}
	
}

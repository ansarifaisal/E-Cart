package com.fsl.ecart.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "USER_PROFILES")
@Component
public class UserProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7047173850733478951L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_PROFILES_SEQUENCE")
	@SequenceGenerator(name = "USER_PROFILES_SEQUENCE", sequenceName = "USER_PROFILES_SEQ")
	@Column(name = "USER_PROFILE_ID")
	private long id;

	@Column(name = "TYPE", length = 15, unique = true, nullable = false)
	private String type = UserProfileType.USER.getUserProfileType();

	public UserProfile() {
	}

	public UserProfile(long id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserProfile other = (UserProfile) obj;
		if (id != other.id)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", type=" + type + "]";
	}

}

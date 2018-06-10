package com.fsl.ecart.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userDAO")
public class UserDAOImpl {

	@Autowired
	SessionFactory sessionFactory;

}

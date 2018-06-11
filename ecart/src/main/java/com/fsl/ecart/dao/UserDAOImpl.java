package com.fsl.ecart.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fsl.ecart.user.User;

@Repository("userDAO")
public class UserDAOImpl extends AbstractDAO<Integer, User> implements UserDAO {

	static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findById(int id) {
		User user = getByKey(id);
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	@Override
	public User findByUserName(String userName) {
		logger.info("Username : {}", userName);
		CriteriaBuilder criteriaBuilder = createEntityCriteria();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> criteriaRoot = criteriaQuery.from(User.class);
		criteriaQuery.select(criteriaRoot).where(criteriaBuilder.equal(criteriaRoot.get("userName"), userName))
				.distinct(true);
		TypedQuery<User> userQueryResult = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
		User user = userQueryResult.getSingleResult();
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	@Override
	public void save(User user) {
		persist(user);

	}

	@Override
	public void deleteByUserName(String userName) {
		CriteriaBuilder criteriaBuilder = createEntityCriteria();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> criteriaRoot = criteriaQuery.from(User.class);
		criteriaQuery.select(criteriaRoot).where(criteriaBuilder.equal(criteriaRoot.get("userName"), userName))
				.distinct(true);
		TypedQuery<User> userQueryResult = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
		User user = userQueryResult.getSingleResult();
		delete(user);

	}

	@Override
	public List<User> findAllUsers() {
		CriteriaBuilder criteriaBuilder = createEntityCriteria();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> criteriaRoot = criteriaQuery.from(User.class);
		criteriaQuery.select(criteriaRoot);
		TypedQuery<User> userQueryResult = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
		return userQueryResult.getResultList();
	}

}

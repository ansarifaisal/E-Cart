package com.fsl.ecart.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fsl.ecart.user.UserProfile;

@Repository("userProfileDAO")
public class UserProfileDAOImpl extends AbstractDAO<Integer, UserProfile> implements UserProfileDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<UserProfile> findAll() {
		CriteriaBuilder criteriaBuilder = createEntityCriteria();
		CriteriaQuery<UserProfile> criteriaQuery = criteriaBuilder.createQuery(UserProfile.class);
		Root<UserProfile> criteriaRoot = criteriaQuery.from(UserProfile.class);
		criteriaQuery.select(criteriaRoot).orderBy(criteriaBuilder.asc(criteriaRoot.get("type")));
		TypedQuery<UserProfile> userQueryResult = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
		return userQueryResult.getResultList();
	}

	@Override
	public UserProfile findByType(String type) {
		CriteriaBuilder criteriaBuilder = createEntityCriteria();
		CriteriaQuery<UserProfile> criteriaQuery = criteriaBuilder.createQuery(UserProfile.class);
		Root<UserProfile> criteriaRoot = criteriaQuery.from(UserProfile.class);
		criteriaQuery.select(criteriaRoot).where(criteriaBuilder.equal(criteriaRoot.get("type"), type)).distinct(true);
		TypedQuery<UserProfile> persistentLoginQueryResult = sessionFactory.getCurrentSession()
				.createQuery(criteriaQuery);
		return persistentLoginQueryResult.getSingleResult();
	}

	@Override
	public UserProfile findById(int id) {
		return getByKey(id);
	}

}

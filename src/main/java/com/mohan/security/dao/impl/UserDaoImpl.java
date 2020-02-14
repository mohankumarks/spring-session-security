package com.mohan.security.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mohan.security.dao.AbstractDao;
import com.mohan.security.dao.UserDao;
import com.mohan.security.model.User;

@Repository
public class UserDaoImpl extends AbstractDao implements UserDao {

	@Override
	public User findByUserName(String userName) {

		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("userName", userName));

		return (User) criteria.uniqueResult();
	}
}

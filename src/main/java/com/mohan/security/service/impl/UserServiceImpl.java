package com.mohan.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mohan.security.dao.UserDao;
import com.mohan.security.model.User; 
import com.mohan.security.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDaoImpl;

	@Override
	@Transactional
	public User findByUserName(String userName) {
		return userDaoImpl.findByUserName(userName);
	}
}

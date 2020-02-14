package com.mohan.security.dao;

import com.mohan.security.model.User;

public interface UserDao {

	User findByUserName(String userName);
}

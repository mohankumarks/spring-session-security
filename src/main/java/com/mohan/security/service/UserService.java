package com.mohan.security.service;

import com.mohan.security.model.User;

public interface UserService {

	User findByUserName(String userName);
}

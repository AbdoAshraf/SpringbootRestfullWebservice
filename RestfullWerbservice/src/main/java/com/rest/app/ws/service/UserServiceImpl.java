package com.rest.app.ws.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.app.ws.UserRepository;
import com.rest.app.ws.io.entity.UserEntity;
import com.rest.app.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	@Override
	public UserDto creartUser(UserDto userDto) {
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDto, userEntity);
		userEntity.setEncryptedPassword("test");
		userEntity.setEmailVerificationStatus(false);
		userEntity.setUserId("tesId");
		UserEntity savedUser = userRepo.save(userEntity);
		UserDto result = new UserDto();
		BeanUtils.copyProperties(savedUser, result);
		
		return result ;
	}

}

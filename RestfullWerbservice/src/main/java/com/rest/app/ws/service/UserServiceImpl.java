package com.rest.app.ws.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rest.app.ws.shared.Utils;

import com.rest.app.ws.UserRepository;
import com.rest.app.ws.io.entity.UserEntity;
import com.rest.app.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	Utils utils ;
	@Override
	public UserDto creartUser(UserDto userDto) {
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDto, userEntity);
		userEntity.setEncryptedPassword("test");
		userEntity.setEmailVerificationStatus(false);
		String publicUserId = utils.generateUserId(30);
		userEntity.setUserId(publicUserId);
		UserEntity savedUser = userRepo.save(userEntity);
		UserDto result = new UserDto();
		BeanUtils.copyProperties(savedUser, result);
		
		return result ;
	}

}

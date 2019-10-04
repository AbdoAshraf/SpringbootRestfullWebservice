package com.rest.app.ws.service;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rest.app.ws.io.entity.UserEntity;
import com.rest.app.ws.io.repositories.UserRepository;
import com.rest.app.ws.shared.Utils;
import com.rest.app.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	Utils utils;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto creartUser(UserDto userDto) {
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDto, userEntity);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		userEntity.setEmailVerificationStatus(false);
		String publicUserId = utils.generateUserId(30);
		userEntity.setUserId(publicUserId);
		UserEntity savedUser = userRepo.save(userEntity);
		UserDto result = new UserDto();
		BeanUtils.copyProperties(savedUser, result);

		return result;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepo.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepo.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);

		return returnValue;
	}

	@Override
	public UserDto getUserById(String id) {
		UserEntity userEntity = userRepo.findByuserId(id);

		if (userEntity == null)
			throw new UsernameNotFoundException(id);

		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);

		return returnValue;
	}

	@Override
	public UserDto updateUser(UserDto userDto, String id) {

		UserEntity userEntity = userRepo.findByuserId(id);

		if (userEntity == null)
			throw new UsernameNotFoundException(id);
		UserDto result = new UserDto();
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		UserEntity updatedUserDetails = userRepo.save(userEntity);
		BeanUtils.copyProperties(updatedUserDetails, result);
		return result;
	}
}

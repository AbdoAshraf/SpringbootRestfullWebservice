package com.rest.app.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.rest.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService {

	public UserDto creartUser(UserDto userDto);

	public UserDto getUser(String email);

	public UserDto getUserById(String id);

	public UserDto updateUser(UserDto userDto, String id);

	public void deleteUser(String id);
}

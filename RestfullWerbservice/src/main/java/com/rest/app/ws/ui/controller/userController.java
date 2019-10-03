package com.rest.app.ws.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.app.ws.service.UserService;
import com.rest.app.ws.shared.dto.UserDto;
import com.rest.app.ws.ui.model.request.userDetailsRequestModel;
import com.rest.app.ws.ui.model.response.UserRest;

/******************************************************************
 * @author abdulrahman This is the use controller class in which we should
 *         interface with the external world: her is an implementation of rest
 *         api-s for ( getting updating deleting users)
 ******************************************************************/

@RestController
// the api should be handled by /users
@RequestMapping("/users")
public class userController {
	/*
	 * first we inject the service layer in order to communicate with our database.
	 * Hint: the ui layer should not interact directly with the database
	 */
	@Autowired
	UserService userService;

	/*
	 * 
	 */
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest getuser(@PathVariable String id) {
		UserRest result = new UserRest();
		UserDto userDto = userService.getUserById(id);
		// mapping between two layers
		BeanUtils.copyProperties(userDto, result);

		return result;
	}

	// if you want to creat a new user you should use http post method
	@PostMapping(
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })

	public UserRest creatuser(@RequestBody userDetailsRequestModel requestUserDetails) {
		/*
		 * the result should be a result rest class to hide internal implementations
		 * from user for both security and business concerns
		 */
		UserRest result = new UserRest();
		/*
		 * user dto ( data transfer object) to enable transferring data between layers
		 */
		UserDto userDto = new UserDto();
		// mapping between two layers
		BeanUtils.copyProperties(requestUserDetails, userDto);
		UserDto createdUser = new UserDto();
		// calling the service layer for creating a new user
		createdUser = userService.creartUser(userDto);
		// mapping again
		BeanUtils.copyProperties(createdUser, result);
		// finally we did it :)
		return result;
	}

	@PutMapping
	public String updateUser() {
		return "hello put";
	}

	@DeleteMapping
	public String deleteUser() {
		return "hello delete";
	}

}

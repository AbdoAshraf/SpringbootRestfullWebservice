package com.rest.app.ws.io.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rest.app.ws.io.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {
	UserEntity findByEmail(String email);
	UserEntity findByuserId(String id);

}

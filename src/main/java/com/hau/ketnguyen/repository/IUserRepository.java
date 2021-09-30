package com.hau.ketnguyen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hau.ketnguyen.entity.UserEntity;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long>{
	UserEntity findByEmail(String email);
}

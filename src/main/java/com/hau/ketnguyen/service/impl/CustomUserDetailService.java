package com.hau.ketnguyen.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hau.ketnguyen.dto.CustomUser;
import com.hau.ketnguyen.entity.RoleEntity;
import com.hau.ketnguyen.entity.UserEntity;
import com.hau.ketnguyen.repository.IUserRepository;

@Configuration
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
	private IUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity entity = userRepository.findByEmail(email);
		if (null == entity) {
			throw new UsernameNotFoundException(email);
		}

		CustomUser users = new CustomUser(entity.getEmail(), entity.getPassword(), getAuthorities(entity));
		users.setFirstName(entity.getFirstName());
		users.setLastName(entity.getLastName());

		return users;
	}

	private Collection<GrantedAuthority> getAuthorities(UserEntity userEntity) {
		Collection<RoleEntity> userRoles = userEntity.getRoles();
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(userRoles.size());
		for (RoleEntity item : userRoles) {
			authorities.add(new SimpleGrantedAuthority(item.getName().toUpperCase()));
		}
		return authorities;
	}

}

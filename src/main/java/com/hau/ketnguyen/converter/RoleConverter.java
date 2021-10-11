package com.hau.ketnguyen.converter;

import org.springframework.stereotype.Component;

import com.hau.ketnguyen.dto.RoleDTO;
import com.hau.ketnguyen.entity.RoleEntity;

@Component
public class RoleConverter {
	public RoleEntity toEntity(RoleDTO roleDTO , RoleEntity roleEntity) {
		roleEntity.setName(roleDTO.getName());
		roleEntity.setCode(roleDTO.getCode());
		return roleEntity;
	}
	
	public RoleEntity toEntity(RoleDTO roleDTO) {
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setName(roleDTO.getName());
		roleEntity.setCode(roleDTO.getCode());
		return roleEntity;
	}
	
	public RoleDTO toDTO(RoleEntity roleEntity) {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId(roleEntity.getId());
		roleDTO.setName(roleEntity.getName());
		roleDTO.setCode(roleEntity.getCode());
		return roleDTO;
	}
}

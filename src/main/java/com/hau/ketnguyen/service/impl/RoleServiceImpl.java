package com.hau.ketnguyen.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hau.ketnguyen.entity.RoleEntity;
import com.hau.ketnguyen.repository.IRoleRepository;
import com.hau.ketnguyen.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private IRoleRepository roleRepository;

	@Override
	public Map<String, String> listAll() {
		Map<String, String> map = new HashMap<String, String>();
		List<RoleEntity> listEntity = roleRepository.findAll();
		for (RoleEntity item : listEntity) {
			map.put(item.getCode(), item.getName());
		}
		return map;
	}

}

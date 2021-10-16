package com.hau.ketnguyen.dto;

import java.util.Set;

public class RoleDTO extends BaseDTO<RoleDTO>{
	private String name;
	private String code;
	private Set<UserDTO> users;

	public Set<UserDTO> getUsers() {
		return users;
	}
	public void setUsers(Set<UserDTO> users) {
		this.users = users;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}

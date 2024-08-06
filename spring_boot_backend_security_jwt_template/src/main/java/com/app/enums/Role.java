package com.app.enums;

public enum Role {
	 ROLE_ADMIN,ROLE_REGULARUSER,ROLE_WEIGHTLOSSUSER,ROLE_WEIGHTGAINUSER;

	public Enum<Role> getRoleName(Enum<Role> Role) {
		
		return Role;
	}

	
}

package vn.cnf166.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserType {
	@JsonProperty("owner")
	OWNER,
	@JsonProperty("admin")
	ADMIN,
	@JsonProperty("member")
	MEMBER;
}

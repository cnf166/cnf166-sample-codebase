package vn.cnf166.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import vn.cnf166.util.PhoneNumber;

import java.io.Serializable;

@Getter
@Builder
public class UserDetailResponse implements Serializable {

	private String firstName;
	private String lastName;
	private String email;
	private String phone;
}

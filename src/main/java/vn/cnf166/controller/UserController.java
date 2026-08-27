package vn.cnf166.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.*;
import vn.cnf166.dto.request.UserRequestDTO;
import vn.cnf166.util.Gender;
import vn.cnf166.util.UserStatus;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")

public class UserController {

	@PostMapping("/add")
	//@RequestMapping(method = RequestMethod.POST, path = "/", headers = "apiKey=v1.0")
	public String addUser(@Valid @RequestBody UserRequestDTO userDTO) {
		return "Added user successfully!";
	}

	@PutMapping("/{userId}")
	public String updateUser(@PathVariable int userId, @RequestBody UserRequestDTO userDTO) {
		System.out.println("Update user with userid = " + userId);
		return "Updated user successfully!";
	}

	@PatchMapping("/{userId}")
	public String changeStatusUser(@Min(1) @PathVariable int userId, @Min(1) @RequestParam(required = false) boolean status) //required = false --> non-mandatory
	{
		System.out.println("Change status user with userId =" + userId);
		return "Change status user successfully!";
	}

	@DeleteMapping("/{userId}")
	public String deleteUser(@Min(1) @PathVariable int userId) {
		System.out.println("Delete user with userId = " + userId);
		return "Delete user successfully";
	}

	@GetMapping("/{userId}")
	public UserRequestDTO getUser(@PathVariable int userId) {
		System.out.println("Request get user by userId: " + userId);
		return new UserRequestDTO("Viet Anh", "Nguyen Viet Anh", "nguyenvietanh166.fw@gmail.com", "0912xxxxxx", UserStatus.NONE, Gender.FEMALE, "Nam Tu Liem", new Date(), List.of("ABC", "XYZ"));
	}

	@GetMapping("/users_list")
	public List<UserRequestDTO> getAllUserList(
			@RequestParam(required = false) String phone,
			@RequestParam(defaultValue = "1") int pageNumber,
			@RequestParam(defaultValue = "20") int pageSize) {
		System.out.println("Request get all users: ");
		return List.of(new UserRequestDTO("Viet Anh", "Nguyen Viet Anh", "nguyenvietanh166.fw@gmail.com", "0912xxxxxx", UserStatus.ACTIVE, Gender.MALE, "Nam Tu Liem", new Date(), List.of("ABC", "XYZ")),
				new UserRequestDTO("Viet Anh", "Nguyen Viet Anh", "nguyenvietanh166.fw@gmail.com", "0912xxxxxx", UserStatus.NONE, Gender.OTHER, "Nam Tu Liem", new Date(), List.of("ABC", "XYZ")));
	}

}

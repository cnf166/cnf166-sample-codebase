package vn.cnf166.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.cnf166.configuration.Translator;
import vn.cnf166.dto.request.UserRequestDTO;
import vn.cnf166.dto.response.ResponseData;
import vn.cnf166.dto.response.ResponseError;
import vn.cnf166.exception.ResourceNotFoundException;
import vn.cnf166.service.UserService;
import vn.cnf166.util.Gender;
import vn.cnf166.util.UserStatus;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

	// Response based on ResponseEntity
//	@PostMapping("/add")
//	//@RequestMapping(method = RequestMethod.POST, path = "/", headers = "apiKey=v1.0")
//	public ResponseSuccess addUser(@Valid @RequestBody UserRequestDTO userDTO) {
//		return new ResponseSuccess(HttpStatus.CREATED, "Added user successfully!", 1);
//	}
	@Autowired
	private UserService userService;

	@PostMapping("/add")
	//@RequestMapping(method = RequestMethod.POST, path = "/", headers = "apiKey=v1.0")
	public ResponseData<Integer> addUser(@Valid @RequestBody UserRequestDTO userDTO) {
		try {
			userService.addUser(userDTO);
			return new ResponseData<>(HttpStatus.CREATED.value(), Translator.toLocale("user.add.success"), 1);
		} catch (ResourceNotFoundException e) {
			return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Saved fail!");
		}
	}

	@PutMapping("/{userId}")
	public ResponseData<?> updateUser(@Min(1) @PathVariable int userId, @Valid @RequestBody UserRequestDTO userDTO) {
		System.out.println("Update user with userid = " + userId);
		return new ResponseData<>(HttpStatus.ACCEPTED.value(), Translator.toLocale("user.upd.success"));
	}

	@PatchMapping("/{userId}")
	public ResponseData<?> changeStatusUser(@Min(1) @PathVariable int userId, @Min(1) @RequestParam(required = false) boolean status) //required = false --> non-mandatory
	{
		System.out.println("Change status user with userId =" + userId);
		return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Change status user successfully!");
	}

	@DeleteMapping("/{userId}")
	public ResponseData<?> deleteUser(@Min(1) @PathVariable int userId) {
		System.out.println("Delete user with userId = " + userId);
		return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Delete user successfully");
	}

	@GetMapping("/{userId}")
	public ResponseData<?> getUser(@PathVariable int userId) {
		System.out.println("Request get user by userId: " + userId);
		return new ResponseData<>(HttpStatus.OK.value(), "Get user by id: ", new UserRequestDTO("Viet Anh", "Nguyen Viet Anh", "nguyenvietanh166.fw@gmail.com", "0912xxxxxx", UserStatus.NONE, Gender.FEMALE, "",  "Nam Tu Liem", new Date(), List.of("ABC", "XYZ")));
	}

	@GetMapping("/users_list")
	public ResponseData<?> getAllUserList(
			@RequestParam(required = false) String phone,
			@Min(1) @RequestParam(defaultValue = "1") int pageNumber,
			@Min(20) @RequestParam(defaultValue = "20") int pageSize) {
		System.out.println("Request get all users: ");
		return new ResponseData<>(HttpStatus.OK.value(), "Get users: ", List.of(new UserRequestDTO("Viet Anh", "Nguyen Viet Anh", "nguyenvietanh166.fw@gmail.com", "0912xxxxxx", UserStatus.ACTIVE, Gender.MALE, "UserType.MEMBER", "Nam Tu Liem", new Date(), List.of("ABC", "XYZ")),
				new UserRequestDTO("Viet Anh", "Nguyen Viet Anh", "nguyenvietanh166.fw@gmail.com", "0912xxxxxx", UserStatus.NONE, Gender.OTHER, "UserType.OWNER", "Nam Tu Liem", new Date(), List.of("ABC", "XYZ"))));
	}

}

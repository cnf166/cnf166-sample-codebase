package vn.cnf166.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.cnf166.configuration.Translator;
import vn.cnf166.dto.request.UserRequestDTO;
import vn.cnf166.dto.response.ResponseData;
import vn.cnf166.dto.response.ResponseError;
import vn.cnf166.dto.response.UserDetailResponse;
import vn.cnf166.exception.ResourceNotFoundException;
import vn.cnf166.service.UserService;
import vn.cnf166.util.Gender;
import vn.cnf166.util.UserStatus;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
@Slf4j
@Tag(name = "User Controller")
@RequiredArgsConstructor
public class UserController {

	// Response based on ResponseEntity
//	@PostMapping("/add")
//	//@RequestMapping(method = RequestMethod.POST, path = "/", headers = "apiKey=v1.0")
//	public ResponseSuccess addUser(@Valid @RequestBody UserRequestDTO userDTO) {
//		return new ResponseSuccess(HttpStatus.CREATED, "Added user successfully!", 1);
//	}

	private final UserService userService;

	@Operation(summary = "Add user by id", description = "API will add or create a new user")
	@PostMapping("/add")
	//@RequestMapping(method = RequestMethod.POST, path = "/", headers = "apiKey=v1.0")
	public ResponseData<Long> addUser(@Valid @RequestBody UserRequestDTO user) {
		log.info("Request add user, {} {}", user.getFirstName(), user.getLastName());
		try {
			long userId = userService.saveUser(user);
			return new ResponseData<>(HttpStatus.CREATED.value(), Translator.toLocale("user.add.success"), userId);
		} catch (Exception e) {
			log.error("error message={}", e.getMessage(), e.getCause());
			return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Saved fail!");
		}
	}

	@Operation(summary = "Update user by id", description = "API will update an user")
	@PutMapping("/{userId}")
	public ResponseData<?> updateUser(@Min(1) @PathVariable long userId, @Valid @RequestBody UserRequestDTO userDTO) {
		System.out.println("Update user with userid = " + userId);
		try {
			userService.updateUser(userId, userDTO);
			return new ResponseData<>(HttpStatus.ACCEPTED.value(), Translator.toLocale("user.upd.success"));
		} catch (Exception e) {
			log.error("errorMessage={}", e.getMessage(), e.getCause());
			return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Update user failed!");
		}
	}

	@Operation(summary = "Change detail in user by id", description = "API will change the detail of an user")
	@PatchMapping("/{userId}")
	public ResponseData<?> changeStatusUser(@Min(1) @PathVariable long userId, @Min(1) @RequestParam(required = false) UserStatus status) //required = false --> non-mandatory
	{
		System.out.println("Change status user with userId =" + userId);
		try {
			userService.changeStatus(userId, status);
			return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Change status user successfully!");
		} catch (Exception e) {
			log.error("errorMessage={}", e.getMessage(), e.getCause());
			return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Change user failed!");
		}
	}

	@Operation(summary = "Delete user by id", description = "API will delete an user")
	@DeleteMapping("/{userId}")
	public ResponseData<?> deleteUser(@Min(1) @PathVariable long userId) {
		System.out.println("Delete user with userId = " + userId);
		try {
			userService.deleteUser(userId);
			return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Delete user successfully");
		} catch (Exception e) {
			log.error("errorMessage={}", e.getMessage(), e.getCause());
			return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Delete user failed!");
		}

	}

	@Operation(summary = "Get user by id", description = "API will return an user by id")
	@GetMapping("/{userId}")
	public ResponseData<UserDetailResponse> getUser(@PathVariable long userId) {
		System.out.println("Request get user by userId: " + userId);
		try {
			return new ResponseData<>(HttpStatus.OK.value(), "Get user by id: ", userService.getUser(userId));
		} catch (ResourceNotFoundException e) {
			log.error("errorMessage={}", e.getMessage(), e.getCause());
			return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
		}
	}

	@Operation(summary = "Get user list per page", description = "API will return a list of users based on page number & page size")
	@GetMapping("/users_list")
	public ResponseData<List<UserDetailResponse>> getAllUserList(
			@Min(1) @RequestParam(defaultValue = "1") int pageNumber,
			@Min(20) @RequestParam(defaultValue = "20") int pageSize) {
		System.out.println("Request get all users: ");
		return new ResponseData<>(HttpStatus.OK.value(), "Get users: ", userService.getAllUsers(pageNumber, pageSize));
	}

}

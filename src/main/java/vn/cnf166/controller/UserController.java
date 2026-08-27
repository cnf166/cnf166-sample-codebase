package vn.cnf166.controller;

import org.springframework.web.bind.annotation.*;
import vn.cnf166.dto.request.UserRequestDTO;

@RestController
@RequestMapping("/users")

public class UserController {

	@PostMapping("/")
	public String addUser(@RequestBody UserRequestDTO userDTO) {
		return "Added user successfully!";
	}

	@PutMapping("/{userId}")
	public String updateUser(@PathVariable int userId, @RequestBody UserRequestDTO userDTO) {
		System.out.println("Update user with userid = " + userId);
		return "Updated user successfully!";
	}

	@PatchMapping("/{userId}")
	public String changeStatusUser(@PathVariable int userId, @RequestParam(required = false) boolean status) //required = false --> non-mandatory
	{
		System.out.println("Change status user with userId =" + userId);
		return "Change status user successfully!";
	}

	@DeleteMapping("/{userId}")
	public String deleteUser(@PathVariable int userId) {
		System.out.println("Delete user with userId = " + userId);
		return "Delete user successfully";
	}


}

package vn.cnf166.controller;

import org.springframework.web.bind.annotation.*;
import vn.cnf166.dto.request.UserRequestDTO;

@RestController
@RequestMapping("/users")

public class UserController {

	@PostMapping("/")
	public String addUser(@RequestBody UserRequestDTO userDTO) {
		return "hello" + userDTO;
	}
}

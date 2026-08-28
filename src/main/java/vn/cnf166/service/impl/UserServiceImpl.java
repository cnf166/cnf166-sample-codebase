package vn.cnf166.service.impl;

import org.springframework.stereotype.Service;
import vn.cnf166.dto.request.UserRequestDTO;
import vn.cnf166.exception.ResourceNotFoundException;
import vn.cnf166.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public int addUser(UserRequestDTO userRequestDTO) {
		System.out.println("Save user to db");
		if (!userRequestDTO.getFirstName().equals("ok")) {
			throw new ResourceNotFoundException("Resource not found!");
		}
		return 0;
	}
}

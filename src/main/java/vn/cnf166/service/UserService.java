package vn.cnf166.service;

import vn.cnf166.dto.request.UserRequestDTO;
import vn.cnf166.dto.response.UserDetailResponse;
import vn.cnf166.util.UserStatus;

import java.util.List;

public interface UserService {

	int addUser(UserRequestDTO request);

	long saveUser(UserRequestDTO request);

	void updateUser(long userId, UserRequestDTO request);

	void changeStatus(long userId, UserStatus status);

	void deleteUser(long userId);

	UserDetailResponse getUser(long userId);

	List<UserDetailResponse> getAllUsers(int pageNumber, int pageSize);

}

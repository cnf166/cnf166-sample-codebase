package vn.cnf166.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.cnf166.dto.request.AddressDTO;
import vn.cnf166.dto.request.UserRequestDTO;
import vn.cnf166.dto.response.UserDetailResponse;
import vn.cnf166.exception.ResourceNotFoundException;
import vn.cnf166.model.Address;
import vn.cnf166.model.User;
import vn.cnf166.repository.UserRepository;
import vn.cnf166.service.UserService;
import vn.cnf166.util.UserStatus;
import vn.cnf166.util.UserType;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public int addUser(UserRequestDTO request) {
		return 0;
	}

	@Override
	@Transactional
	public long saveUser(UserRequestDTO request) {
		User user = User.builder()
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.dateOfBirth(request.getDateOfBirth())
				.gender(request.getGender())
				.phone(request.getPhone())
				.email(request.getEmail())
				.username(request.getUsername())
				.password(request.getPassword())
				.status(request.getStatus())
				.type(UserType.valueOf(request.getUserType().toUpperCase(Locale.ROOT)))
				.build();

		// Use your helper method to link the bidirectional relationship
		if (request.getAddresses() != null) {
			request.getAddresses().forEach(a -> {
				Address address = Address.builder()
						.apartmentNumber(a.getApartmentNumber())
						.floor(a.getFloor())
						.building(a.getBuilding())
						.streetNumber(a.getStreetNumber())
						.street(a.getStreet())
						.city(a.getCity())
						.country(a.getCountry())
						.addressType(a.getAddressType())
						.build();
				user.saveAddress(address);
			});
		}

		userRepository.save(user);
		log.info("Saved user successfully!");
		return user.getId();
	}
	@Override
	public void updateUser(long userId, UserRequestDTO request) {
		User user = getUserById(userId);
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setDateOfBirth(request.getDateOfBirth());
		user.setGender(request.getGender());
		user.setPhone(request.getPhone());
		if (!request.getEmail().equals(user.getEmail())) {
			// check if email from database if not exist then allow update email, otherwise will throw exception
			user.setEmail(request.getEmail());
		}
		if (StringUtils.hasLength(request.getUsername())) {
			// check if username from database if not exist then allow update
			user.setUsername(request.getUsername());
		}
		user.setPassword(request.getPassword());
		user.setStatus(request.getStatus());
		user.setType(UserType.valueOf(request.getUserType()));
		user.setAddresses(convertToAddress(request.getAddresses()));
		userRepository.save(user);
		log.info("User updated successfully!");
	}

	@Override
	public void changeStatus(long userId, UserStatus status) {
		User user = getUserById(userId);
		user.setStatus(status);
		userRepository.save(user);
		log.info("Changed status successfully!");
	}

	@Override
	public void deleteUser(long userId) {
		userRepository.deleteById(userId);
		log.info("Delete user successfully!");
	}

	@Override
	public UserDetailResponse getUser(long userId) {
		User user = getUserById(userId);
		return UserDetailResponse.builder()
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.phone(user.getPhone())
				.email(user.getPhone())
				.build();
	}

	@Override
	public List<UserDetailResponse> getAllUsers(int pageNumber, int pageSize) {
		// set up query pageNumber 1 still oke (= 0), Spring boot setting with = 0 by default in configuration
		int p = 0;
		if (pageNumber > 0) {
			p = pageNumber - 1;
		}

		// only want one particular portion of the users
		Pageable pageable = PageRequest.of(p, pageSize);
		Page<User> users = userRepository.findAll(pageable);

		// mapping DTO to list
		return users.stream().map(user -> UserDetailResponse.builder()
						.firstName(user.getFirstName())
						.lastName(user.getLastName())
						.phone(user.getPhone())
						.email(user.getEmail())
						.build()).toList();
	}

	private Set<Address> convertToAddress(Set<AddressDTO> addresses) {
		Set<Address> result = new HashSet<>();
		addresses.forEach(a ->
				result.add(Address.builder()
						.apartmentNumber(a.getApartmentNumber())
						.floor(a.getFloor())
						.building(a.getBuilding())
						.streetNumber(a.getStreetNumber())
						.street(a.getStreet())
						.city(a.getCity())
						.country(a.getCountry())
						.addressType(a.getAddressType())
						.build())
		);
		return result;
	}

	private User getUserById(long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}
}

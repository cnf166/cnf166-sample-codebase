package vn.cnf166.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.cnf166.dto.request.AddressDTO;
import vn.cnf166.dto.request.UserRequestDTO;
import vn.cnf166.dto.response.UserDetailResponse;
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

	}

	@Override
	public void changeStatus(long userId, UserStatus status) {

	}

	@Override
	public void deleteUser(long userId) {

	}

	@Override
	public UserDetailResponse getUser(long userId) {
		return null;
	}

	@Override
	public List<UserDetailResponse> getAllUsers(int pageNumber, int pageSize) {
		return List.of();
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

}

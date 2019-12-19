package hu.elte.NewReddit.controller;

import hu.elte.NewReddit.model.User;
import hu.elte.NewReddit.model.enums.UserRole;
import hu.elte.NewReddit.repository.UserRepository;
import hu.elte.NewReddit.security.AuthenticatedUser;
import hu.elte.NewReddit.model.ApiResponse;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticatedUser authenticatedUser;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("")
	public ResponseEntity<Iterable<User>> getAll() {
		Iterable<User> result = userRepository.findAll();
		result.forEach((User item) -> {
			item.setPassword(null);
		});
		return new ResponseEntity(result, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id) {
		Optional<User> result = userRepository.findById(id);
		if (result.isPresent()) {
			result.get().setPassword(null);
			return new ResponseEntity(result, HttpStatus.OK);
		}
		return new ResponseEntity(null, HttpStatus.NOT_FOUND);
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse> login(@RequestBody User reqUser) {
		Optional<User> user = userRepository.findByUsername(reqUser.getUsername());

		String date = new Date(System.currentTimeMillis()).toString();

		if (authenticatedUser.getUser() != null) {
			return new ResponseEntity<>(new ApiResponse(418, "User already loged in", date), HttpStatus.I_AM_A_TEAPOT);
		}
		if (user.isPresent()) {
			authenticatedUser.setUser(user.get());
			return new ResponseEntity(new ApiResponse(200, "Successfully logged in", date), HttpStatus.OK);
		}
		return new ResponseEntity(new ApiResponse(404, "Something went wrong", date), HttpStatus.NOT_FOUND);
	}

	@GetMapping("/logoff")
	public ResponseEntity<ApiResponse> logoff() {

		String date = new Date(System.currentTimeMillis()).toString();

		if (authenticatedUser.getUser() == null) {
			return new ResponseEntity(new ApiResponse(418, "User not logged in", date), HttpStatus.I_AM_A_TEAPOT);
		}

		authenticatedUser.setUser(null);
		return new ResponseEntity(new ApiResponse(200, "Successfully logged out", date), HttpStatus.OK);
	}

	@GetMapping("/me")
	public ResponseEntity<ApiResponse> getLoggedInUser() {
		String date = new Date(System.currentTimeMillis()).toString();

		if (authenticatedUser.getUser() == null) {
			return new ResponseEntity(new ApiResponse(404, "No logged in user", date), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(new ApiResponse(200, "It's a me " + authenticatedUser.getUser().getUsername(), date), HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<ApiResponse> register(@RequestBody User user) {

		String date = new Date(System.currentTimeMillis()).toString();

		Optional<User> oUser = userRepository.findByUsername(user.getUsername());
		if (oUser.isPresent()) {
			return new ResponseEntity(new ApiResponse(418, "Username is taken", date), HttpStatus.I_AM_A_TEAPOT);
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setUserRole(UserRole.NORMAL);
		user.setRegistrationDate(new Date());
		// return new ResponseEntity<>(user, HttpStatus.OK);
		userRepository.save(user);
		return new ResponseEntity(new ApiResponse(200, "Successfuly registered", date), HttpStatus.OK);
	}

}

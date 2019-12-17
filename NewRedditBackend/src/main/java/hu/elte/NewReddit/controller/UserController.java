package hu.elte.NewReddit.controller;

import hu.elte.NewReddit.model.RedditPost;
import hu.elte.NewReddit.model.User;
import hu.elte.NewReddit.model.enums.UserRole;
import hu.elte.NewReddit.repository.UserRepository;
import hu.elte.NewReddit.security.AuthenticatedUser;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
	public ResponseEntity login(@RequestBody User user) {
		return ResponseEntity.ok().build();
	}

	@GetMapping("/logoff")
	public ResponseEntity logoff() {
		authenticatedUser.setUser(null);
		return ResponseEntity.ok(0);
	}

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) {
		Optional<User> oUser = userRepository.findByUsername(user.getUsername());
		if (oUser.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setUserRole(UserRole.NORMAL);
		return ResponseEntity.ok(userRepository.save(user));
	}

}

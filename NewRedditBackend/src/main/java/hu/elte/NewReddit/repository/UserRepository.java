package hu.elte.NewReddit.repository;

import hu.elte.NewReddit.model.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByUsername(String username);

	Optional<User> findById(Long Id);

}

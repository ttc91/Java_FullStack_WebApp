package fa.training.mock.remotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.training.mock.remotes.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	public User findUserByUsernameAndPassword(String username, String password);
	
}

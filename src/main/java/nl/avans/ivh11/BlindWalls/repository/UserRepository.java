package nl.avans.ivh11.BlindWalls.repository;

import nl.avans.ivh11.BlindWalls.domain.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}

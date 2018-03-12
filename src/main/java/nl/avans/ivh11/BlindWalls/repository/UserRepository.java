package nl.avans.ivh11.BlindWalls.repository;

import nl.avans.ivh11.BlindWalls.domain.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by thomasdelhez on 12-03-18.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}

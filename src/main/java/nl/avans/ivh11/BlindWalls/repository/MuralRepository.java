package nl.avans.ivh11.BlindWalls.repository;

import nl.avans.ivh11.BlindWalls.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface MuralRepository extends CrudRepository<Product, Long> {

}

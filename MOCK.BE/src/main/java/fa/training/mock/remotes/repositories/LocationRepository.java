package fa.training.mock.remotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.training.mock.remotes.entities.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer>{

}

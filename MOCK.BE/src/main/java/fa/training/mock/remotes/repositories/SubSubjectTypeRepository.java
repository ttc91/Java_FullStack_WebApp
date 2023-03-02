package fa.training.mock.remotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.training.mock.remotes.entities.SubSubjectType;

@Repository
public interface SubSubjectTypeRepository extends JpaRepository<SubSubjectType, Integer>{

}

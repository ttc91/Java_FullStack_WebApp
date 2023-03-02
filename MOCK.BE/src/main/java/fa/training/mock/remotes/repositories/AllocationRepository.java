package fa.training.mock.remotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.training.mock.remotes.entities.Allocation;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Integer>{

}

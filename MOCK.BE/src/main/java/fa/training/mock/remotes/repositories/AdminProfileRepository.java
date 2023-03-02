package fa.training.mock.remotes.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fa.training.mock.remotes.entities.AdminProfile;

@Repository
public interface AdminProfileRepository extends JpaRepository<AdminProfile, Integer>{


	@Modifying
	@Transactional
	@Query(value = "DELETE FROM admin_profile WHERE admin_id = ?1", nativeQuery = true)
	public void deleteByClassAdminId(Integer adminId);
}

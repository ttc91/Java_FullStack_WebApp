package fa.training.mock.remotes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.training.mock.remotes.entities.TraineeCandidateProfile;

@Repository
public interface TraineeCandidateProfileRepository extends JpaRepository<TraineeCandidateProfile, Integer>{
	List<TraineeCandidateProfile> findByAccount(String account);
	TraineeCandidateProfile findByEmail(String email);
	TraineeCandidateProfile findByPhone(String phone);
}

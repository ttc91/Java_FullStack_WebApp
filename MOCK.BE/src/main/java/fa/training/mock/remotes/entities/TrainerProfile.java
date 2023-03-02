package fa.training.mock.remotes.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class TrainerProfile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trainer_profile_id")
	private Integer trainerProfileId;
	
	@Column(name = "full_name", length = 50, nullable = false)
	private String fullName;
	
	@Column(name = "date_of_birth", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@Column(name = "gender", nullable = false)
	private Boolean gender;
	
	@Column(name = "unit", nullable = false, length = 50)
	private String unit;
	
	@Column(name = "major", nullable = false, length = 50)
	private String major;
	
	@Column(name = "phone", unique = true, length = 10, nullable = false)
	private String phone;
	
	@Column(name = "email", unique = true, length = 100, nullable = false)
	private String email;
	
	@Column(name = "experience", length = 10, nullable = false)
	private String experience;
	
	@Column(name = "remarks", length = 200)
	private String remarks;
	
}

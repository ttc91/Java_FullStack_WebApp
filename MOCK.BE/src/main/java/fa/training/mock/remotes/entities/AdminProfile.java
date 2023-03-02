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

import org.springframework.format.annotation.DateTimeFormat;

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
public class AdminProfile implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_profile_id")
	private Integer adminProfileId;
	
	@Column(name = "full_name", length = 50, nullable = false)
	private String fullName;
	
	@Column(name = "date_of_birth", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	
	@Column(name = "gender", nullable = false)
	private Boolean gender;
	
	@Column(name = "phone", unique = true, length = 10, nullable = false)
	private String phone;
	
	@Column(name = "email", unique = true, length = 100, nullable = false)
	private String email;
	
	@Column(name = "remarks", length = 200)
	private String remarks;
	
	
}

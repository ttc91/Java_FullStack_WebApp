package fa.training.mock.remotes.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fa.training.mock.models.dto.base.BaseDto;
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
public class TraineeCandidateProfile extends BaseDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer profileId;
	
	@Column(unique = true)
	private String account;
	
	@Column
	private String fullName;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@Column
	private String gender;
	
	@ManyToOne
	@JoinColumn(name = "university_id")
	private University university;
	
	@ManyToOne
	@JoinColumn(name = "faculty_id")
	private Faculty faculty;
	
	@Column(unique = true)
	private String phone;
	
	@Column(unique = true)
	private String email;
	
	@Column
	private String cv;
	
	@Column
	private String skill;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date graduationYear;
	
	@Column
	private String foreignLanguage;
	
	@Column
	private Integer level;
	
	@Column
	private String type;
	
	@Column
	private Boolean allowcationStatus;
	
	@Column
	private String remarks;
}

package fa.training.mock.remotes.entities;


import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
public class Trainee implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer traineeId;
	
	@Column
 	private Double commitedValue;

	@Column
 	private Integer committedWorkingDuration;
 	
 	@Column
	private String traineeGroup;
 	
 	@Column
 	private Boolean salaryPaid;
	
	@Column
	private String remarks;
	
	@Column
 	@Temporal(TemporalType.DATE)
 	private Date committedWorkingStartDate;
	
	@Column
	private String tpBankAccount;
	
	@OneToOne
	@JoinColumn(name = "profile_id", unique = true)
	private TraineeCandidateProfile traineeCandidateProfile;
	
	@ManyToOne
	@JoinColumn(name = "status_id")
	private Status status;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "class_batch_id")
	private ClassBatch classBatch;
	
	@OneToMany(mappedBy = "trainee")
	@JsonBackReference
	@ToString.Exclude
	private Collection<AttendantStatus> attendantStatuses;
	
	@OneToMany(mappedBy = "trainee")
	@JsonBackReference
	@ToString.Exclude
	private Collection<TopicMark> topicMarks;
	
	@OneToMany(mappedBy = "trainee")
	@JsonBackReference
	@ToString.Exclude
	private Collection<RewardPenalty> rewardPenalties;
	
	@OneToMany(mappedBy = "trainee")
	@JsonBackReference
	@ToString.Exclude
	private Collection<Allocation> allocations;
	
	@OneToMany(mappedBy = "trainee")
	@JsonBackReference
	@ToString.Exclude
	private Collection<Allowance> allowances;
	
	@OneToMany(mappedBy = "trainee")
	@JsonBackReference
	@ToString.Exclude
	private Collection<GPA> gpas;
}

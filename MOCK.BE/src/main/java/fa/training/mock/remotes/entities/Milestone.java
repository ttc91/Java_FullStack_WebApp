package fa.training.mock.remotes.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fa.training.mock.models.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Milestone implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer milestoneId;
	
	@Column
	private String milestoneName;

	@Column
	private Double totalMaxScore; 
	
	@Column
	private Double totalWeightedNumber;
	
	@Column
	private Double totalPassingScore; 
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@Column
	private Boolean salaryPaid;
	
	@ManyToOne
	@JoinColumn(name = "class_batch_id")
	@JsonBackReference
	private ClassBatch classBatch;
	
	@OneToMany(mappedBy = "milestone")
	@JsonBackReference
	@ToString.Exclude
	private Collection<AttendantStatus> attendantStatuses;
	
	@OneToMany(mappedBy = "milestone")
	@JsonBackReference
	@ToString.Exclude
	private Collection<Topic> topics;
	
	@OneToMany(mappedBy = "milestone")
	@JsonBackReference
	@ToString.Exclude
	private Collection<RewardPenalty> rewardPenalties;
	
	@OneToMany(mappedBy = "milestone")
	@JsonBackReference
	@ToString.Exclude
	private Collection<GPA> gpas;
	
}

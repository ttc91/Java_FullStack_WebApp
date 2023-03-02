package fa.training.mock.remotes.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import fa.training.mock.config.enums.StatusClassEnum;
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
public class ClassBatch implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "class_batch_id")
	private Integer classBatchId;
	
	@Column(name = "class_name", length = 50, nullable = false, unique = true)
	private String className;

	@Column(name = "class_code", length = 50, nullable = false, unique = true)
	@Pattern(regexp="^Site_(FR|CP)_[a-zA-Z\\\\\\\\s,]+_[0-9]{2}_[0-9]{2}$", message="Class code must be format Site_FR_Skill_YY_XX or Site_CP_Skill_YY_XX!")
	private String classCode;
	
	@Column(name = "expected_start_date", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date expectedStartDate;
	
	@Column(name = "expected_end_date", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date expectedEndDate;
	
	@Column(name = "detail_location", nullable = false, length = 50)
	private String detailLocation;
	
	@Column(name = "planned_trainee_number", nullable = false)
	private Long plannedTraineeNumber;
	
	@Column(name = "estimated_budget", nullable = false)
	private Double estimatedBudget;
	
	@Column(name = "actual_start_date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date actualStartDate;
	
	@Column(name = "actual_end_date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date actualEndDate;
	
	@Column(name = "accepted_trainee_number", nullable = false)
	private Long acceptedTraineeNumber;
	
	@Column(name = "actual_trainee_number", nullable = false)
	private Long actualTraineeNumber;

	@OneToMany(mappedBy = "classBatch")
	@ToString.Exclude
	private Collection<Milestone> milestones;
	
	@Column(name = "curriculumn", nullable = false, length = 200)
	private String curriculumn;
	
	@Column(name = "remarks", length = 200)
	private String remarks;
	
	@Column(name = "status", nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	private StatusClassEnum status;
	
	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;
	
	@ManyToOne
	@JoinColumn(name = "admin_id")
	private ClassAdmin admin;
	
	@ManyToOne
	@JoinColumn(name = "budget_id", nullable = false)
	private Budget budget;
	
	@Column(name = "created_date", updatable = false)
	@CreationTimestamp
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date createdDate;
	
	@ManyToOne
	@JoinColumn(name = "master_trainer_id")
	private Trainer masterTrainer;
	
	@OneToMany(mappedBy = "classBatch", cascade = CascadeType.ALL)
	private Collection<Audit> audit;
	
	@ManyToOne
	@JoinColumn(name = "subject_type_id")
	private SubjectType subjectType;
	
	@ManyToOne
	@JoinColumn(name = "sub_subject_type_id")
	private SubSubjectType subSubjectType;
	
	@ManyToOne
	@JoinColumn(name = "delivery_type_id")
	private DeliveryType deliveryType;
	
	@ManyToOne
	@JoinColumn(name = "format_type_id")
	private FormatType formatType;
	
	@ManyToOne
	@JoinColumn(name = "scope_id")
	private Scope scope;
	
	@ManyToOne
	@JoinColumn(name = "supplier_partner_id")
	private SupplierPartner supplierPartner;
	
	@OneToMany(mappedBy = "classBatch")
	@ToString.Exclude
	private Collection<TrainerClass> trainerClasses;
	
	@OneToMany(mappedBy = "classBatch")
	@ToString.Exclude
	private Collection<Trainee> trainees;
	
	@OneToMany(mappedBy = "classBatch", cascade = CascadeType.ALL)
	@ToString.Exclude
	private Collection<BudgetItem> budgetItems;
	
}

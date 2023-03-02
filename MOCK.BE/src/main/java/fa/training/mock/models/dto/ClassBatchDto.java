package fa.training.mock.models.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import fa.training.mock.config.enums.StatusClassEnum;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.res.trainingResult.MilestoneDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClassBatchDto extends BaseDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer classBatchId;
	
	private String className;
	
	private String classCode;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date expectedStartDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date expectedEndDate;
	
	private String detailLocation;
	
	private Long plannedTraineeNumber;
	
	private Double estimatedBudget;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date actualStartDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date actualEndDate;
	
	private Long acceptedTraineeNumber;
	
	private Long actualTraineeNumber;
	
	private Collection<MilestoneDto> milestones;
	
	private String curriculumn;
	
	private String remarks;
	
	private StatusClassEnum status;
	
	private LocationDto location;

	private ClassAdminDto classAdmin;
	
	private BudgetDto budget;
	
	private TrainerDto masterTrainer;
	
	private List<AuditDto> audit;
	
	private SubjectTypeDto subjectType;
	
	private SubSubjectTypeDto subSubjectType;
	
	private DeliveryTypeDto deliveryType;
	
	private FormatTypeDto formatType;
	
	private ScopeDto scope;
	
	private SupplierPartnerDto supplierPartner;
	
	private Collection<TraineeDto> trainees;
	
	private List<BudgetItemDto> budgetItems;
	
}

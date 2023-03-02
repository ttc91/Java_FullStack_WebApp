package com.example.demo.services.dtos;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.services.dtos.base.BaseDto;
import com.example.demo.services.dtos.enums.StatusClassEnum;

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
public class ClassBatchDto extends BaseDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer classBatchId;
	
	private String className;
	
	private String classCode;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String expectedStartDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String expectedEndDate;
	
	private String detailLocation;
	
	private Long plannedTraineeNumber;
	
	private Double estimatedBudget;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String actualStartDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String actualEndDate;
	
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

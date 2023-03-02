package fa.training.mock.models.dto.res.trainingResult;

import java.io.Serializable;

import lombok.Data;

@Data
public class AllowanceDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer allowanceStandardId;
	private String milestoneName;
	private String allowanceResult;
	private boolean salaryPaid;
	private Double standardSalary;
	private String remarks;
}

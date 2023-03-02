package fa.training.mock.models.dto.res.trainingResult;

import java.io.Serializable;

import lombok.Data;

@Data
public class GPADto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String milestoneName;
	private Double academicMark;
	private Double disciplinaryPoint;
	private Double bonus;
	private Double penalty;
	private Double gpa;
}

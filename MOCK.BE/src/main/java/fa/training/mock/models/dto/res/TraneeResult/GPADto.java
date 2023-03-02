package fa.training.mock.models.dto.res.TraneeResult;

import java.io.Serializable;

import lombok.Data;

@Data
public class GPADto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private float academicMark;
	private float disciplinaryPoint;
	private float bonus;
	private float penalty;
	private float gpa;
}

package fa.training.mock.models.dto.req.candidate;

import java.io.Serializable;

import lombok.Data;

@Data
public class TransferCandidateDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer profileId;
	private Integer candidateId;
	private String remarks;
}

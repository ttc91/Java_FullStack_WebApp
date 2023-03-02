package fa.training.mock.models.dto.res.trainingResult;

import java.io.Serializable;
import java.util.Collection;

import lombok.Data;

@Data
public class MilestoneMarkDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String milestoneName;
	private float milestoneMark;
	private Collection<TopicMarkDto> topicMarkDtos; 
}

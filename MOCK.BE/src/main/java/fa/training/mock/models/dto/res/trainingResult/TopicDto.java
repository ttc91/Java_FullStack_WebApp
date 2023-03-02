package fa.training.mock.models.dto.res.trainingResult;


import lombok.Data;

@Data
public class TopicDto {
	private Integer topicId;
	private String topicName;
	private Double maxScore;
	private Double passingScore;
	private Double weightedNumber;
	private String remarks;
	private String willDelete;
}

package fa.training.mock.models.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import fa.training.mock.config.enums.EventCategoryEnum;
import fa.training.mock.models.dto.base.BaseDto;
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
public class AuditDto extends BaseDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer auditId;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	private EventCategoryEnum eventCategory;
	
	@NotEmpty(message = "relatedPeople must not empty !!!")
	private String relatedPeople;
	
	@NotEmpty(message = "action must not empty !!!")
	private String action;
	
	@NotEmpty(message = "PIC must not empty !!!")
	private String pic;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dealine;
	
	@NotEmpty(message = "note must not empty !!!")
	private String note;
	
	@JsonIgnore
	private ClassBatchDto classBatch;
}

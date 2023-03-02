package fa.training.mock.models.dto;

import java.io.Serializable;

import fa.training.mock.models.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChannelDto extends BaseDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String channelName;
	
	private String remarks;

}

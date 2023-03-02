package fa.training.mock.models.dto.req.channel;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InsertChannelDto  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String channelName;

	private String remarks;
}

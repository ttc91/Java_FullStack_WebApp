package fa.training.mock.models.dto.req.candidate;

import java.io.Serializable;
import java.util.Date;

import fa.training.mock.remotes.entities.Channel;
import fa.training.mock.remotes.entities.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class InsertCandidateDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date applicationDate;
	
	private String note;
	
	private Channel channel;
	
	private Location location;
	
}

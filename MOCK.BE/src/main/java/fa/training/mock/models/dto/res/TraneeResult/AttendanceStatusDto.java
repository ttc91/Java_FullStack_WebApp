package fa.training.mock.models.dto.res.TraneeResult;

import lombok.Data;

@Data
public class AttendanceStatusDto {
	private int absentTimes;
	private int lateAndEarlyLeave;
	private float noPermissionRate;
	private float discipinaryPoint;
}

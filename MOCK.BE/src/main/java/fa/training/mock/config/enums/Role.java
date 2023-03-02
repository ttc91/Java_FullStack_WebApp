package fa.training.mock.config.enums;

public enum Role {

	TRAINER("ROLE_TRAINER"), 
	ADMIN("ROLE_ADMIN"),
	TRAINEE("ROLE_TRAINEE"),
	FA_MANAGEMENT("ROLE_FA_MANAGEMENT"),
	DELIVERY_MANAGEMENT("ROLE_DELIVERY_MANAGEMENT"),
	SYSTEM_ADMIN("SYSTEM_ADMIN"),
	FA_REC("FA_REC");
	
	public String value;
	
	private Role(String value) {
		this.value = value;
	}
	
}

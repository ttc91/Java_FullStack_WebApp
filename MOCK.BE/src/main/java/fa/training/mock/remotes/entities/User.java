package fa.training.mock.remotes.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import fa.training.mock.config.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "username",length = 50)
	private String username; 
	
	@Column(name = "password", nullable = false, length = 255)
	private String password;

	@Column(name = "user_role", nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private Role role; 
	
}

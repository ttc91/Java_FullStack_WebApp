package fa.training.mock.remotes.entities;


import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class ClassAdmin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_id")
	private Integer id;
	
	@Column(name = "user_name", length = 50, nullable = false, unique = true)
	private String username;
	
	@Column(name = "remarks", length = 200)
	private String remarks;
	
	@OneToMany(mappedBy = "admin")
	@JsonIgnore
	@ToString.Exclude
	private Collection<ClassBatch> classBatchs;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "admin_profile_id", unique = true)
	private AdminProfile adminProfile;
	
}

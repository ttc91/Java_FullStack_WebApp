package fa.training.mock.remotes.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
public class Allowance implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String allowanceResult;
	
	@Column
	private Boolean salaryPaid;
	
	@Column(name = "remarks")
	private String remarks;
	
	@ManyToOne
	@JoinColumn(name = "trainee_id")
	private Trainee trainee;
	
	@ManyToOne
	@JoinColumn(name = "allowance_standard_id")
	private AllowanceStandard allowanceStandard;
	
}

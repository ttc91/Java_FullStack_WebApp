package fa.training.mock.remotes.entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class Allocation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String allocatedFSU;
	
	@Column
	private Double salary;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column
	private String allocationStatus; 
	
	@Column
	private String remarks;
	
	@ManyToOne
	@JoinColumn(name = "trainee_id", referencedColumnName = "traineeId")
	private Trainee trainee;

}

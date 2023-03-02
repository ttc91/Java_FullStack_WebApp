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

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class RewardPenalty implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column
	private Double bonusPoint;
	
	@Column
	private Double penaltyPoint;
	
	@Column
	private String reason;
	
	@ManyToOne
	@JoinColumn(name = "trainee_id", referencedColumnName = "traineeId")
	@JsonBackReference
	private Trainee trainee;
	
	@ManyToOne
	@JoinColumn(name = "milestone_id")
	@JsonBackReference
	private Milestone milestone;
	
}

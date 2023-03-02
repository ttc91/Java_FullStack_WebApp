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

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Getter
@Setter
public class GPA implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private Double academicMark;
	
	@Column
	private Double disciplinaryPoint;
	
	@Column
	private Double bonus;
	
	@Column
	private Double penalty;
	
	@Column
	private Double gpa;
	
	@ManyToOne
	@JoinColumn(name = "trainee_id")
	@JsonBackReference
	private Trainee trainee;
	
	@ManyToOne
	@JoinColumn(name = "milestone_id")
	@JsonBackReference
	private Milestone milestone;

}

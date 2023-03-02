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
public class Trainer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer trainerId;
	
	@Column(name = "type", length = 100, nullable = false)
	private String type;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "trainer_profile_id", unique = true)
	private TrainerProfile trainerProfile;
	
	@OneToMany(mappedBy = "masterTrainer")
	@JsonIgnore
	@ToString.Exclude
	private Collection<ClassBatch> classBatchs;
	
	@OneToMany(mappedBy = "trainer")
	@JsonIgnore
	@ToString.Exclude
	private Collection<TrainerClass> trainerClasses;
	
}

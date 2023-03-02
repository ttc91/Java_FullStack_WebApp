package fa.training.mock.remotes.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
public class Topic implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer topicId;
	
	@Column(unique = true)
	private String topicName;
	
	@Column
	private Double maxScore;
	
	@Column
	private Double passingScore;
	
	@Column
	private Double weightedNumber;
	
	@Column
	private String remarks;
	
	@ManyToOne
	@JoinColumn(name = "milestone_id")
	@JsonBackReference
	private Milestone milestone;
	
	@OneToMany(mappedBy = "topic")
	@JsonBackReference
	private Collection<TopicMark> gpas;

	
}

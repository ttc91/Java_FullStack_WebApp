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
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Location implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "id")
	private Integer locationId;
	
	@Column(name = "location_name", length = 50, nullable = false)
	private String locationName;
	
	@Column(name = "location_remarks", length = 100)
	private String remarks;
	
	@OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
	@JsonIgnore
	@ToString.Exclude
	private Collection<ClassBatch> classBatchs;
	
	@OneToMany(mappedBy = "location")
	@JsonIgnore
	@ToString.Exclude
	private Collection<Candidate> candidate;
	
}

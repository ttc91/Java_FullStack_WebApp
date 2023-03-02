package fa.training.mock.remotes.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
public class Candidate implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "profile_id", unique = true)
	private TraineeCandidateProfile traineeCandidateProfile;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date applicationDate;
	
	@Column
	private String status;
	
	@Column
	private String note;
	
	@Column
	private String remarks;
	
	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;
	
	@ManyToOne
	@JoinColumn(name = "channel_id")
	private Channel channel;
	
	@OneToMany(mappedBy = "candidate")
	@JsonBackReference
	private Collection<EntryTest> entryTests;
	
	@OneToMany(mappedBy = "candidate")
	@JsonBackReference(value="candidate-inteviews")
	private Collection<Interview> inteviews;
	
	//Chưa xét
	
//	@OneToMany
//	private List<Offer> offers;
	
}

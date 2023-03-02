package fa.training.mock.remotes.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import fa.training.mock.config.enums.EventCategoryEnum;
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
public class Audit implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "audit_id")
	private Integer auditId;
	
	@Column(name = "date", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name = "event_category", length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	private EventCategoryEnum eventCategory;
	
	@Column(name = "related_people", length = 50, nullable = false)
	private String relatedPeople;
	
	@Column(name = "action", length = 50, nullable = false)
	private String action;
	
	@Column(name = "pic", length = 50, nullable = false)
	private String PIC;
	
	@Column(name = "dealine", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dealine;
	
	@Column(name = "note", length = 200, nullable = false)
	private String note;
	
	@ManyToOne()
	@JsonBackReference
	@JsonIgnore
	@JoinColumn(name = "class_batch_id")
	private ClassBatch classBatch;
	
}

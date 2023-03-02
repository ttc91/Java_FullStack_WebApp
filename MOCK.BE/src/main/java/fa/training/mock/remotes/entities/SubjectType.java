package fa.training.mock.remotes.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fa.training.mock.config.enums.SubjectTypeEnum;
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
public class SubjectType implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subject_type_id")
	private Integer subjectTypeid;
	
	@Column(name = "subject_type_name", length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	private SubjectTypeEnum subjectTypeName;
	
	@Column(name = "remarks", length = 200)
	private String remarks;
	
	@OneToMany(mappedBy = "subjectType")
	@JsonIgnore
	@ToString.Exclude
	private Collection<ClassBatch> classBatchs;
}

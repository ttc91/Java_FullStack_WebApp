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

import fa.training.mock.config.enums.SubSubjectTypeEnum;
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
public class SubSubjectType implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sub_subject_type_id")
	private Integer subSubjectTypeId;
	
	@Column(name = "sub_subject_type_name", length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	private SubSubjectTypeEnum subSubjectTypeName;
	
	@Column(name = "remarks", length = 200)
	private String remarks;
	
	@OneToMany(mappedBy = "subSubjectType")
	@JsonIgnore
	@ToString.Exclude
	private Collection<ClassBatch> classBatchs;
}

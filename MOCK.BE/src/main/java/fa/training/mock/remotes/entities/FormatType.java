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

import fa.training.mock.config.enums.FormatTypeEnum;
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
public class FormatType implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "format_type_id")
	private Integer id;
	
	@Column(name = "format_type_name", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private FormatTypeEnum formatTypeName;
	
	@Column(name = "remarks")
	private String remarks;
	
	@OneToMany(mappedBy = "formatType")
	@JsonIgnore
	@ToString.Exclude
	private Collection<ClassBatch> classBatchs;
	
}

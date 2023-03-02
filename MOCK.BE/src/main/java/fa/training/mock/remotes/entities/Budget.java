package fa.training.mock.remotes.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

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

import fa.training.mock.config.enums.BudgetEnum;
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
public class Budget implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "budget_id")
	private Integer id;
	
	@Column(name = "budget_code", length = 50, nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private BudgetEnum budgetCode;
	
	@Column(name = "budget_total", nullable = false)
	private BigDecimal total;
	
	@Column(name = "budget_over", columnDefinition = "BOOLEAN NOT NULL DEFAULT FALSE")
	private Boolean overBudget;
	
	@OneToMany(mappedBy = "budget")
	@JsonIgnore
	@ToString.Exclude
	private Collection<ClassBatch> classBatchs;
	
	@OneToMany(mappedBy = "budget")
	@JsonIgnore
	@ToString.Exclude
	private List<BudgetItem> budgetItems;
	
}

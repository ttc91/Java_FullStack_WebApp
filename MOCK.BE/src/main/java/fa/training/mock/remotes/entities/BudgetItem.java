package fa.training.mock.remotes.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class BudgetItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "budget_item_name", length = 50)
	private String itemName;
	
	@Column(name = "unit", length = 10, nullable = false)
	private String unit;
	
	@Column(name = "unit_expense", nullable = false)
	private BigDecimal unitExpense;
	
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	@Column(name = "tax", columnDefinition = "INT NOT NULL DEFAULT 0")
	private Integer tax;
	
	@Column(name = "note", length = 50)
	private String note;
	
	@ManyToOne
	@JoinColumn(name = "budget_id", nullable = false)
	private Budget budget;
	
	@ManyToOne
	@JsonBackReference
	@JsonIgnore
	@JoinColumn(name = "class_path")
	private ClassBatch classBatch;

}

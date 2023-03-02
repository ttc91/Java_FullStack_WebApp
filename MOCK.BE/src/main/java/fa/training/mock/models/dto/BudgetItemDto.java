package fa.training.mock.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fa.training.mock.models.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BudgetItemDto extends BaseDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String itemName;
	
	private String unit;
	
	private BigDecimal unitExpense;
	
	private Integer quantity;
	
	private Integer tax;
	
	private String note;
	
	private BudgetDto budget;
	
	@JsonIgnore
	private ClassBatchDto classBatch;
	
}

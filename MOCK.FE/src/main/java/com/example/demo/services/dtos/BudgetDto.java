package com.example.demo.services.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import com.example.demo.services.dtos.base.BaseDto;
import com.example.demo.services.dtos.enums.BudgetEnum;

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
public class BudgetDto extends BaseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private BudgetEnum budgetCode;
	
	private BigDecimal total;
	
	private Boolean overBudget;
	
}

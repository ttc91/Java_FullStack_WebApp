package com.example.demo.services.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.services.dtos.base.BaseDto;
import com.example.demo.services.dtos.enums.EventCategoryEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class AuditDto extends BaseDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer auditId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String date;
	
	private EventCategoryEnum eventCategory;
	
	@NotEmpty(message = "relatedPeople must not empty !!!")
	private String relatedPeople;
	
	@NotEmpty(message = "action must not empty !!!")
	private String action;
	
	@NotEmpty(message = "PIC must not empty !!!")
	private String pic;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String dealine;
	
	@NotEmpty(message = "note must not empty !!!")
	private String note;
	
	@JsonIgnore
	private ClassBatchDto classBatch;
}

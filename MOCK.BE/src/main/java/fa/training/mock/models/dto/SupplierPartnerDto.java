package fa.training.mock.models.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
public class SupplierPartnerDto extends BaseDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Supplier partner name must not be empty !!!")
	@Size(min = 5, max = 50,  message = "Supplier partner name must be greater than 5 and lesser than 50")
	private String supplierPartnerName;
	
	@Size(max = 200)
	private String remarks;
	
}

package fa.training.mock.remotes.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
public class SupplierPartner implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supplier_partner_id")
	private Integer id;
	
	@Column(name = "supplier_partner_name", length = 50, nullable = false)
	private String supplierPartnerName;
	
	@Column(name = "supplier_partner_remarks", length = 200)
	private String remarks;
	
	@OneToMany(mappedBy = "supplierPartner")
	@JsonIgnore
	@ToString.Exclude
	private Collection<ClassBatch> classBatchs;
	
}

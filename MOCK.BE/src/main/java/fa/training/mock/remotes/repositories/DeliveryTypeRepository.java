package fa.training.mock.remotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.training.mock.config.enums.DeliveryTypeEnum;
import fa.training.mock.remotes.entities.DeliveryType;

@Repository
public interface DeliveryTypeRepository extends JpaRepository<DeliveryType, Integer> {
	
	public DeliveryType findDeliveryTypeByDeliveryTypeName(DeliveryTypeEnum deliveryTypeName);

}

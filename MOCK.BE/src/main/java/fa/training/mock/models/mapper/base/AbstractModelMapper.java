package fa.training.mock.models.mapper.base;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractModelMapper <E, D>{
	
	@Autowired
	ModelMapper mapper;

	public D mapToDto(E e, Class<D> d) {
		return e != null ? mapper.map(e, d) : null;
	}
	
	public E mapToEntity(D d, Class<E> e) {
		return d != null ? mapper.map(d, e) : null;
	}
	
	public List<D> mapToDtoList(List<E> eList, Class<D> d){
		return eList != null ? eList.stream().map(e -> mapper.map(e, d)).collect(Collectors.toList()) : null;
	}
	
	public List<E> mapToEntityList(List<D> dList, Class<E> e){
		return dList != null ? dList.stream().map(d -> mapper.map(d, e)).collect(Collectors.toList()) : null;
	}
	
	public E mergeToEntity(D d, E e) {
		mapper.map(d, e);
		return d != null ? e : null;
	}
	
}

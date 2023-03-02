package fa.training.mock.remotes.services.base;

import fa.training.mock.models.dto.base.ResponseDto;

public abstract class BaseService <T> {

	public abstract ResponseDto<T> create(T obj);
	public abstract ResponseDto<T> update(T obj);
	public abstract ResponseDto<T> delete(T obj);
	public abstract ResponseDto<T> getOne(T obj);
	public abstract ResponseDto<T> getAll();
	
}

/**
 * 
 */
package br.com.aprendaViajando.service.servicesInterfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author MARCIO-PC
 *
 */
public interface ServiceInterface <O, S, L> {
	
	public O saveOrUpdate(O objeto);
	
	public void delete(L id);
	
	public O findById(L id);
	
	public List<O> findByNome(S nome);
	
	public Page<O> findByPagination(Pageable pageable);
}

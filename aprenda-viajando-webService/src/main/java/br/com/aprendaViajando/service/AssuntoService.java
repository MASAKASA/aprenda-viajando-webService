package br.com.aprendaViajando.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.aprendaViajando.domain.model.competencias.Assunto;
import br.com.aprendaViajando.domain.repository.competencias.AssuntoRepository;
import br.com.aprendaViajando.util.ReplaceString;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AssuntoService {

	@Autowired
	private AssuntoRepository repository;
	
	@Transactional(readOnly = false)
	public void saveOrUpdate(Assunto assunto) {
		
		String permalink = ReplaceString.formatarPermalink(assunto.getNome());
		
		if (assunto.getId() != null) {
			//Recupera um assunto na forma persistente para atualização
			Optional<Assunto> optional = repository.findById(assunto.getId());
			
			Assunto aPersistente = optional.get();
			//Sets...
			
			repository.save(aPersistente);
		} else {
			//Cadastra um novo assunto
			assunto.setPermalink(permalink);			
			repository.save(assunto);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		
		//Recupera um assunto na forma persistente para ser excluido
		Optional<Assunto> optional = repository.findById(id);
		
		Assunto aPersistente = optional.get();
		
		repository.delete(aPersistente);
	}
	
	public Assunto findById(Long id) {
		
		Optional<Assunto> optional = repository.findById(id);
		
		return optional.get();
	}
	
	public Assunto findByPermalink (String permalink) {
		
		return repository.findByPermalink(permalink);
	}
	
	public List<Assunto> findAll() {
		//TODO pesquisar como melhorar esse metodo 
		Sort sort = new Sort(new Order(Direction.ASC, "descricao"));
		
		return repository.findAll(sort);
	}
	
	public Page<Assunto> findByPagination(int page, int size) {
		//TODO pesquisar como melhorar esse metodo
		Pageable pageable = new PageRequest(page, size);
		
		return repository.findAllByOrderByCompetenciaNomeAsc(pageable);
	}
}

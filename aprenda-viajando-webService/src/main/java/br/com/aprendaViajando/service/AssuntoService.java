/*
 * 
 */
package br.com.aprendaViajando.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.aprendaViajando.domain.model.competencias.Assunto;
import br.com.aprendaViajando.domain.model.competencias.Competencia;
import br.com.aprendaViajando.domain.repository.competencias.AssuntoRepository;
import br.com.aprendaViajando.util.ReplaceString;

/**
 * @author MARCIO
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AssuntoService {
	
	@Autowired
	private AssuntoRepository repository;
	
	@Transactional(readOnly = false)
	public void saveOrUpdate(Assunto assunto) {
		assunto.setPermalink(ReplaceString.formatarPermalink(assunto.getNome()));
		
		if (assunto.getId() != null) {
			Optional<Assunto> optional = repository.findById(assunto.getId());
			
			Assunto aPersistente = optional.get();
			
			aPersistente.setCompetencia(assunto.getCompetencia());
			aPersistente.setDidatica(assunto.getDidatica());
			aPersistente.setNome(assunto.getNome());
			aPersistente.setPermalink(assunto.getPermalink());
			
			repository.save(aPersistente);
		} else {
			repository.save(assunto);
		}
	}
	
	@Transactional(readOnly = false)
	public
	 void delete(Long id) {
		Optional<Assunto> optional = repository.findById(id);
		
		Assunto aPersistente = optional.get();
		
		repository.delete(aPersistente);
	}
	
	public Assunto findById(Long id) {
		Optional<Assunto> optional = repository.findById(id);
		
		return optional.get();
	}
	
	public Assunto findByPermalink(String permalink) {
		return repository.findByPermalink(permalink);
	}
	
	public List<Assunto> findAll() {
		return repository.findAll(Sort.by("nome"));
	}
	
	public List<Assunto> findByNome(String nome) {
		return repository.findByNomeContainingOrderByNomeAsc(nome);
	}
	
	public List<Assunto> findByCompetencia(Competencia competencia) {
		return repository.findByCompetenciaIdContainingOrderByNomeAsc(competencia);
	}
	
	public Page<Assunto> findByPagination(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		
		return repository.findAll(pageable);
	}
}

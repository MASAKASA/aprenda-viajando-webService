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

import br.com.aprendaViajando.domain.model.competencias.Competencia;
import br.com.aprendaViajando.domain.repository.competencias.CompetenciaReposirtory;
import br.com.aprendaViajando.util.ReplaceString;

/**
 * @author MARCIO
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CompetenciaService {

	@Autowired
	private CompetenciaReposirtory repository;
	
	@Transactional(readOnly = false)
	public void saveOrUpdate(Competencia competencia) {
		competencia.setPermalink(ReplaceString.formatarPermalink(competencia.getNome()));
		
		if (competencia.getId() != null) {
			Optional<Competencia> optional = repository.findById(competencia.getId());
			
			Competencia cPersistente = optional.get();
			
			cPersistente.setListaAssuntos(competencia.getListaAssuntos());
			cPersistente.setListaPontoTuristico(competencia.getListaPontoTuristico());
			cPersistente.setNome(competencia.getNome());
			cPersistente.setPermalink(competencia.getPermalink());
			
			repository.save(cPersistente);
		} else {			
			repository.save(competencia);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		Optional<Competencia> optional = repository.findById(id);
		
		Competencia cPersistente = optional.get();
		
		repository.delete(cPersistente);
	}
	
	public Competencia findById(Long id) {
		Optional<Competencia> optional = repository.findById(id);
		
		return optional.get();
	}
	
	public Competencia findByPermalink (String permalink) {
		return repository.findByPermalink(permalink);
	}
	
	public List<Competencia> findAll() {
		return repository.findAll(Sort.by("nome").ascending());
	}
	
	public List<Competencia> findByNome(String nome) {
		return repository.findByNomeContainingOrderByNomeAsc(nome);
	}
	
	public Page<Competencia> findByPagination(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		
		return repository.findAllByOrderByNomeAsc(pageable);
	}
}

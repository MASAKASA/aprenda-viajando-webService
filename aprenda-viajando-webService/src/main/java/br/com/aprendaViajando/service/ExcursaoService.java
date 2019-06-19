/**
 * 
 */
package br.com.aprendaViajando.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.aprendaViajando.domain.model.pontoTuristico.Excursao;
import br.com.aprendaViajando.domain.model.pontoTuristico.enuns.StatusExcursao;
import br.com.aprendaViajando.domain.repository.pontoTuristico.ExcursaoRepository;
import br.com.aprendaViajando.util.ReplaceString;

/**
 * @author MARCIO
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ExcursaoService {

	@Autowired
	private ExcursaoRepository repository;
	
	@Transactional(readOnly = false)
	public void saveOrUpdate(Excursao excursao) {
		excursao.setPermalink(ReplaceString.formatarPermalink(excursao.getNome()));
		
		if (excursao.getId() != null) {
			Optional<Excursao> optional = repository.findById(excursao.getId());
			
			Excursao ePersistente = optional.get();
			
			ePersistente.setDataExcursao(excursao.getDataExcursao());
			ePersistente.setListaCoordenadores(excursao.getListaCoordenadores());
			ePersistente.setListaParticipantes(excursao.getListaParticipantes());
			ePersistente.setListPontoTuristico(excursao.getListPontoTuristico());
			ePersistente.setNome(excursao.getNome());
			ePersistente.setPermalink(excursao.getPermalink());
			ePersistente.setStatusExcursao(excursao.getStatusExcursao());
			
			repository.save(ePersistente);
		} else {
			repository.save(excursao);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		Optional<Excursao> optional = repository.findById(id);
		
		Excursao ePersistente = optional.get();
		
		repository.delete(ePersistente);
	}
	
	public Excursao findById(Long id) {
		return repository.getOne(id);
	}
	
	public Excursao findByPermalink(String permalink) {
		return repository.findByPermalink(permalink);
	}
	
	public Excursao findByStatusExcursaoAndPermalink(
			StatusExcursao statusExcursao, String permalink) {
		return repository.findByStatusExcursaoAndPermalink(statusExcursao, permalink);
	}
	
	public List<Excursao> findByDataExcursaoAsc(LocalDateTime dataInicial, LocalDateTime dataFinal) {
		return repository.findByDataExcursaoBetweenOrderByDataExcursaoAsc(dataInicial, dataFinal);
	}
	
	public List<Excursao> findByDataExcursaoDesc(LocalDateTime dataInicial, LocalDateTime dataFinal) {
		return repository.findByDataExcursaoBetweenOrderByDataExcursaoDesc(dataInicial, dataFinal);
	}
	
	public List<Excursao> findByStatusExcursaoAsc(
			StatusExcursao statusExcursao, LocalDateTime dataInicial, LocalDateTime dataFinal) {
		return repository.findByStatusExcursaoAndDataExcursaoBetweenOrderByDataExcursaoAsc(
				statusExcursao, dataInicial, dataFinal);
	}
	
	public List<Excursao> findByStatusExcursaoDesc(
			StatusExcursao statusExcursao, LocalDateTime dataInicial, LocalDateTime dataFinal) {
		return repository.findByStatusExcursaoAndDataExcursaoBetweenOrderByDataExcursaoDesc(
				statusExcursao, dataInicial, dataFinal);
	}
	
	public Page<Excursao> findPaginationExcursao(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		
		return repository.findAll(pageable);
	}
	
	public Page<Excursao> findByPaginationStatusExcursao(StatusExcursao statusExcursao, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		
		return repository.findByStatusExcursaoOrderByNomeAsc(statusExcursao, pageable);
	}
}

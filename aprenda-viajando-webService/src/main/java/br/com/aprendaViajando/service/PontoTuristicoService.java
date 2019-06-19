/**
 * 
 */
package br.com.aprendaViajando.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.aprendaViajando.domain.model.pontoTuristico.PontoTuristico;
import br.com.aprendaViajando.domain.repository.pontoTuristico.PontoTuristicoRepository;
import br.com.aprendaViajando.util.ReplaceString;

/**
 * @author MARCIO
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PontoTuristicoService {

	@Autowired
	private PontoTuristicoRepository repository;
	
	@Transactional(readOnly = false)
	public void saveOrUpdate(PontoTuristico pontoTuristico) {
		pontoTuristico.setPermalink(ReplaceString.formatarPermalink(pontoTuristico.getNome()));
		
		if (pontoTuristico.getId() != null) {
			Optional<PontoTuristico> optional = repository.findById(pontoTuristico.getId());
			
			PontoTuristico pPersistente = optional.get();
			
			pPersistente.setAvatarPrincipal(pontoTuristico.getAvatarPrincipal());
			pPersistente.setEmail(pontoTuristico.getEmail());
			pPersistente.setEndereco(pontoTuristico.getEndereco());
			pPersistente.setFaixaEtariaMinima(pontoTuristico.getFaixaEtariaMinima());
			pPersistente.setHistoria(pontoTuristico.getHistoria());
			pPersistente.setHorarioFuncionamento(pontoTuristico.getHorarioFuncionamento());
			pPersistente.setIngresso(pontoTuristico.getIngresso());
			pPersistente.setListaAvatar(pontoTuristico.getListaAvatar());
			pPersistente.setListaComentarios(pontoTuristico.getListaComentarios());
			pPersistente.setListaCompetencias(pontoTuristico.getListaCompetencias());
			pPersistente.setListaExcursoes(pontoTuristico.getListaExcursoes());
			pPersistente.setListaTelefone(pontoTuristico.getListaTelefone());
			pPersistente.setNome(pontoTuristico.getNome());
			pPersistente.setPermalink(pontoTuristico.getPermalink());
			
			repository.save(pPersistente);
		} else {
			repository.save(pontoTuristico);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		Optional<PontoTuristico> optional = repository.findById(id);
		
		PontoTuristico pPersistente = optional.get();
		
		repository.delete(pPersistente);
	}
	
	public PontoTuristico findById(Long id) {
		return repository.getOne(id);
	}
	
	public PontoTuristico findByPermalink(String permalink) {
		return repository.findByPermalink(permalink);
	}
	
	public List<PontoTuristico> findByAll() {
		return repository.findAll();
	}
	
	public Page<PontoTuristico> findByPaginationPontoTuristico(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		
		return repository.findAll(pageable);
	}
}

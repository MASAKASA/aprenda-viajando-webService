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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.aprendaViajando.domain.model.pontoTuristico.Avaliacao;
import br.com.aprendaViajando.domain.model.pontoTuristico.PontoTuristico;
import br.com.aprendaViajando.domain.repository.pontoTuristico.AvaliacaoRepository;
import br.com.aprendaViajando.util.MediaAvaliacao;

/**
 * @author MARCIO
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AvaliacaoService {

	@Autowired
	private AvaliacaoRepository retository;

	@Transactional(readOnly = false)
	public void saveOrUpdate(Avaliacao avaliacao) {
		avaliacao.setMediaAvaliacao(
				MediaAvaliacao.media(avaliacao.getTotalDasAvaliacoes(), avaliacao.getTotalDosAvaliadores()));
		
		if (avaliacao.getId() != null) {
			Optional<Avaliacao> optional = retository.findById(avaliacao.getId());

			Avaliacao aPersistente = optional.get();

			aPersistente.setAvaliacaoAtual(avaliacao.getAvaliacaoAtual());
			aPersistente.setAvaliacaoUsuario(avaliacao.getAvaliacaoUsuario());
			aPersistente.setMediaAvaliacao(avaliacao.getMediaAvaliacao());

			retository.save(aPersistente);
		} else {
			retository.save(avaliacao);
		}
	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		Optional<Avaliacao> optional = retository.findById(id);

		Avaliacao aPersistente = optional.get();

		retository.delete(aPersistente);
	}

	public Avaliacao findById(Long id) {
		return retository.getOne(id);
	}

	public Avaliacao findByPontoTuristico(PontoTuristico pontoTuristico) {
		return retository.findByPontoTuristicoId(pontoTuristico);
	}

	public List<Avaliacao> findAll() {
		return retository.findAll(Sort.by("pontoTuristicoNome"));
	}

	public Page<Avaliacao> findByPagination(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

		return retository.findAll(pageable);
	}
}

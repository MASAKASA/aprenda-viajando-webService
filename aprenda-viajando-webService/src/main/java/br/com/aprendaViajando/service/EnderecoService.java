/**
 * 
 */
package br.com.aprendaViajando.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.aprendaViajando.domain.model.util.Endereco;
import br.com.aprendaViajando.domain.repository.util.EnderecoRepository;

/**
 * @author MARCIO
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;
	
	@Transactional(readOnly = false)
	public void saveOrUpdate(Endereco endereco) {
		if (endereco.getId() != null) {
			Optional<Endereco> optional = repository.findById(endereco.getId());
			
			Endereco ePersistente = optional.get();
			
			ePersistente.setBairro(endereco.getBairro());
			ePersistente.setCep(endereco.getCep());
			ePersistente.setCidade(endereco.getCidade());
			ePersistente.setComplemento(endereco.getComplemento());
			ePersistente.setEstado(endereco.getEstado());
			ePersistente.setLatitude(endereco.getLatitude());
			ePersistente.setLogradouro(endereco.getLogradouro());
			ePersistente.setNumero(endereco.getNumero());
			ePersistente.setPontoReferencia(endereco.getPontoReferencia());
			
			repository.save(ePersistente);
		} else {
			repository.save(endereco);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		Optional<Endereco> optional =repository.findById(id);
		
		Endereco ePersistente = optional.get();
		
		repository.delete(ePersistente);
	}
	
	public Endereco findById(Long id) {
		return repository.getOne(id);
	}
	
	public List<Endereco> findAll() {
		return repository.findAll();
	}
}

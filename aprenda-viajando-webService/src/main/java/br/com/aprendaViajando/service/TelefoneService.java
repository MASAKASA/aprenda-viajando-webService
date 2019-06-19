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

import br.com.aprendaViajando.domain.model.pontoTuristico.PontoTuristico;
import br.com.aprendaViajando.domain.model.usuario.Usuario;
import br.com.aprendaViajando.domain.model.util.Telefone;
import br.com.aprendaViajando.domain.repository.util.TelefoneRepository;

/**
 * @author MARCIO
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class TelefoneService {

	@Autowired
	private TelefoneRepository repository;
	
	@Transactional(readOnly = false)
	public void saveOrUpdate(Telefone telefone) {
		if (telefone.getId() != null) {
			Optional<Telefone> optional = repository.findById(telefone.getId());
			
			Telefone tPersistente = optional.get();
			
			tPersistente.setDdd(telefone.getDdd());
			tPersistente.setNumero(telefone.getNumero());
			tPersistente.setTipoTelefone(telefone.getTipoTelefone());
			
			repository.save(tPersistente);
		} else {
			repository.save(telefone);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		Optional<Telefone> optional = repository.findById(id);
		
		Telefone tPersistente = optional.get();
		
		repository.delete(tPersistente);
	}
	
	public Telefone findById(Long id) {
		return repository.getOne(id);
	}
	
	public List<Telefone> findAll() {
		return repository.findAll();
	}
	
	public List<Telefone> findByTelefonePontoTuridtico(PontoTuristico pontoTuristico) {
		return repository.findByPontoTuristicoIdOrderByTipoTelefoneAsc(pontoTuristico);
	}
	
	public List<Telefone> findByTelefoneUsuario(Usuario usuario) {
		return repository.findByUsuarioIdOrderByTipoTelefoneAsc(usuario);
	}
}

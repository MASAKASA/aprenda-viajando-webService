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

import br.com.aprendaViajando.domain.model.usuario.Usuario;
import br.com.aprendaViajando.domain.repository.usuario.UsuarioRepository;

/**
 * @author MARCIO
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Transactional(readOnly = false)
	public void saveOrUpdate(Usuario usuario) {
		if (usuario.getId() != null) {
			Optional<Usuario> optional = repository.findById(usuario.getId());
			
			Usuario uPersistente = optional.get();
			
			uPersistente.setAvatar(usuario.getAvatar());
			uPersistente.setEmail(usuario.getEmail());
			uPersistente.setEndereco(usuario.getEndereco());
			uPersistente.setListaComenterio(usuario.getListaComenterio());
			uPersistente.setListaCoordenacoes(usuario.getListaCoordenacoes());
			uPersistente.setListaExcursoes(usuario.getListaExcursoes());
			uPersistente.setListaTelefone(usuario.getListaTelefone());
			uPersistente.setNome(usuario.getNome());
			
			repository.save(uPersistente);
		} else {
			repository.save(usuario);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		Optional<Usuario> optional = repository.findById(id);
		
		Usuario uPersistente = optional.get();
		
		repository.delete(uPersistente);
	}
	
	public Usuario findById(Long id) {
		return repository.getOne(id);
	}
	
	public List<Usuario> findByUsuario(String nome) {
		return repository.findByNomeContainingOrderByNomeAsc(nome);
	}
	
	public Page<Usuario> findByPaginationUsuario(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		
		return repository.findAll(pageable);
	}
}

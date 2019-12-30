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
import br.com.aprendaViajando.domain.model.usuario.exceptions.EmailJaCadastradoException;
import br.com.aprendaViajando.domain.model.usuario.exceptions.NomeJaCadastradoException;
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
	public void saveOrUpdate(Usuario usuario) 
			throws NomeJaCadastradoException, EmailJaCadastradoException {
		
		if (usuario.getId() != null) {
			Optional<Usuario> optional = repository.findById(usuario.getId());
			
			Usuario uPersistente = optional.get();
			
			uPersistente.setAvatar(usuario.getAvatar());
			uPersistente.setEndereco(usuario.getEndereco());
			uPersistente.setListaComenterio(usuario.getListaComenterio());
			uPersistente.setListaCoordenacoes(usuario.getListaCoordenacoes());
			uPersistente.setListaExcursoes(usuario.getListaExcursoes());
			uPersistente.setListaTelefone(usuario.getListaTelefone());
			
			repository.save(uPersistente);
		} else {
			Optional<Usuario> optionalUsuarioNome = repository.findByNome(usuario.getNome());
			
			if (optionalUsuarioNome.isPresent()) {
				throw new NomeJaCadastradoException("Nome já cadastrado no sistema!");
			}
			
			Optional<Usuario> optionalUsuarioEmail = repository.findByEmail(usuario.getEmail());
			
			if (optionalUsuarioEmail.isPresent()) {
				throw new EmailJaCadastradoException("Email já cadastrado no sistema!");
			}
			
			repository.save(usuario);
		}
	}
	
	@Transactional(readOnly = false)
	public void atualizarNome(String nomeAntigo, String nomeAtualizado) 
			throws NomeJaCadastradoException {
		
		Optional<Usuario> optionalUsuarioNome = repository.findByNome(nomeAtualizado);
		
		if (optionalUsuarioNome.isPresent()) {
			throw new NomeJaCadastradoException("Nome já cadastrado no sistema!");
		}
		
		Optional<Usuario> optional = repository.findByNome(nomeAntigo);
		
		Usuario uPersistente = optional.get();
		
		uPersistente.setNome(nomeAtualizado);
		
		repository.save(uPersistente);
	}
	
	@Transactional(readOnly = false)
	public void atualizarEmail(String emailAntigo, String emailAtualizado) 
			throws EmailJaCadastradoException {
		
		Optional<Usuario> optionalUsuarioEmail = repository.findByEmail(emailAtualizado);
		
		if (optionalUsuarioEmail.isPresent()) {
			throw new EmailJaCadastradoException("Email já cadastrado no sistema!");
		}
		
		Optional<Usuario> optional = repository.findByEmail(emailAntigo);
		
		Usuario uPersistente = optional.get();
		
		uPersistente.setEmail(emailAtualizado);
		
		repository.save(uPersistente);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		Optional<Usuario> optional = repository.findById(id);
		
		Usuario uPersistente = optional.get();
		
		repository.delete(uPersistente);
	}
	
	public Usuario findById(Long id) {
		Optional<Usuario> optional = repository.findById(id);
		
		Usuario uPersistente = optional.get();
		
		return uPersistente;
	}
	
	public List<Usuario> findByNome(String nome) {
		return repository.findByNomeContainingOrderByNomeAsc(nome);
	}
	
	public Page<Usuario> findByPaginationUsuario(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		
		return repository.findAll(pageable);
	}
}

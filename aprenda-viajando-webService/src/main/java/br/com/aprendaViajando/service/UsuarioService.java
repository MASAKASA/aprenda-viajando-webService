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

import br.com.aprendaViajando.domain.model.pontoTuristico.Comentario;
import br.com.aprendaViajando.domain.model.usuario.Usuario;
import br.com.aprendaViajando.domain.model.usuario.exceptions.EmailJaCadastradoException;
import br.com.aprendaViajando.domain.model.usuario.exceptions.NomeJaCadastradoException;
import br.com.aprendaViajando.domain.model.util.Telefone;
import br.com.aprendaViajando.domain.repository.usuario.UsuarioRepository;
import br.com.aprendaViajando.service.servicesInterfaces.usuario.UsuarioServiceInterface;

/**
 * @author MARCIO
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UsuarioService implements UsuarioServiceInterface<Usuario, String, Long> {

	@Autowired
	private UsuarioRepository repository;
	
	@Transactional(readOnly = false)
	public Usuario saveOrUpdate(Usuario usuario) 
			throws NomeJaCadastradoException, EmailJaCadastradoException {
		
		if (usuario.getId() != null) {
			for (Telefone telefone : usuario.getListaTelefone()) {
				telefone.setUsuario(usuario);
			}
			
			return repository.save(usuario);
		} else {
			Optional<Usuario> optionalUsuarioNome = repository.findByNome(usuario.getNome());
			
			if (optionalUsuarioNome.isPresent()) {
				throw new NomeJaCadastradoException("Nome já cadastrado no sistema!");
			}
			
			Optional<Usuario> optionalUsuarioEmail = repository.findByEmail(usuario.getEmail());
			
			if (optionalUsuarioEmail.isPresent()) {
				throw new EmailJaCadastradoException("Email já cadastrado no sistema!");
			}
			
			for (Telefone telefone : usuario.getListaTelefone()) {
				telefone.setUsuario(usuario);
			}
			
			return repository.save(usuario);
		}
	}
	
	@Transactional(readOnly = false)
	public Usuario atualizarNome(String nomeAntigo, String nomeAtualizado) 
			throws NomeJaCadastradoException {
		
		Optional<Usuario> optionalUsuarioNome = repository.findByNome(nomeAtualizado);
		
		if (optionalUsuarioNome.isPresent()) {
			throw new NomeJaCadastradoException("Nome já cadastrado no sistema!");
		}
		
		Optional<Usuario> optional = repository.findByNome(nomeAntigo);
		
		Usuario uPersistente = optional.get();
		
		uPersistente.setNome(nomeAtualizado);
		
		return repository.save(uPersistente);
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
	
	public Page<Usuario> findByPagination(Pageable pageable) {
		
		return repository.findAll(pageable);
	}
}

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

import br.com.aprendaViajando.domain.model.pontoTuristico.Comentario;
import br.com.aprendaViajando.domain.model.pontoTuristico.PontoTuristico;
import br.com.aprendaViajando.domain.model.usuario.Usuario;
import br.com.aprendaViajando.domain.repository.pontoTuristico.ComentarioRepository;

/**
 * @author MARCIO
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ComentarioService {

	@Autowired
	private ComentarioRepository repository;
	
	@Transactional(readOnly = false)
	public void saveOrUpdate(Comentario comentario) {
		comentario.setDataComentario(LocalDateTime.now());
		
		if (comentario.getId() != null) {
			Optional<Comentario> optional = repository.findById(comentario.getId());
			
			Comentario cPersistente = optional.get();
			
			cPersistente.setComentario(comentario.getComentario());
			cPersistente.setDataComentario(comentario.getDataComentario());
			
			repository.save(cPersistente);
		} else {
			repository.save(comentario);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		Optional<Comentario> optional = repository.findById(id);
		
		Comentario cPersistente = optional.get();
		
		repository.delete(cPersistente);
	}
	
	public Comentario findById(Long id) {
		return repository.getOne(id);
	}
	
	public List<Comentario> findByComentariosUsuario(Usuario usuario) {
		return repository.findByUsuarioIdOrderByDataComentarioDesc(usuario);
	}
	
	public List<Comentario> findByComentariosPontoTuristico(PontoTuristico pontoTuristico) {
		return repository.findByPontoTuristicoIdOrderByDataComentarioDesc(pontoTuristico);
	}
	
	public List<Comentario> findByComentariosPontoTuristicoUsuario(
			PontoTuristico pontoTuristico, Usuario usuario) {
		return repository.findByPontoTuristicoIdAndUsuarioIdOrderByDataComentarioDesc(
				pontoTuristico, usuario);
	}
	
	public Page<Comentario> findByPaginationComentariosPontoTuristico(
			PontoTuristico pontoTuristico, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		
		return repository.findByPontoTuristicoIdOrderByDataComentarioDesc(pontoTuristico, pageable);
	}
	
	public Page<Comentario> findByPaginationComentarioUsuario(
			Usuario usuario, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		
		return repository.findByUsuarioIdOrderByDataComentarioDesc(usuario, pageable);
	}
	
	public Page<Comentario> findByPaginationComentariosPontoTuristoUsuario(
			PontoTuristico pontoTuristico, Usuario usuario, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		
		return repository.findByPontoTuristicoIdAndUsuarioIdOrderByDataComentarioDesc(pontoTuristico, usuario, pageable);
	}
}

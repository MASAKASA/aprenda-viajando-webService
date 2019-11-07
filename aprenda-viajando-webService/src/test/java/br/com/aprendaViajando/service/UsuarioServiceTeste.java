/**
 * 
 */
package br.com.aprendaViajando.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.aprendaViajando.domain.model.usuario.Usuario;
import br.com.aprendaViajando.domain.repository.usuario.UsuarioRepository;

/**
 * 
 * @author MARCIO
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class UsuarioServiceTeste {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private UsuarioRepository repository;
	
	@Test
	@Transactional(readOnly = false)
	public void saveOrUpdateOfSaveTeste() {
		String id = "1";
		String nome = "marcio";
		String email ="marcioandredasilvaalmeida@gmail.com";
		
		Usuario usuario = new Usuario();
		
		usuario.setNome(nome);
		usuario.setEmail(email);
		
		service.saveOrUpdate(usuario);
		
		Optional<Usuario> optional = repository.findById(Long.parseLong(id));
		
		assertTrue(optional.isPresent());
	}
	
	@Test
	public void saveOrUpdateOfUpdateTeste() {
		Usuario usuario = service.findById(1L);
		
		usuario.setNome("m");
		
		service.saveOrUpdate(usuario);
		
		Usuario usuario2 = service.findById(1L);
		
		//assertTrue("1".equals(String.valueOf(usuario.getId())));
		//assertTrue("marcioandredasilvaalmeida@gmail.com".equals(usuario.getEmail()));
		assertTrue("m".equals(usuario2.getNome()));
	}
	
	@Test
	public void deleteTeste() {
		service.delete(1L);
		
		Optional<Usuario> optional = repository.findById(1L);
		
		assertFalse(optional.isPresent());
	}
	
	@Test
	public void findByIdCadastroInicialTeste() {
		Usuario usuario = service.findById(1L);
		
		assertTrue("1".equals(String.valueOf(usuario.getId())));
		assertTrue("marcioandredasilvaalmeida@gmail.com".equals(usuario.getEmail()));
		assertTrue("marcio".equals(usuario.getNome()));
	}
	
	@Test
	public void findByIdCadastroCompletoTeste() {
		
	
	}
	
	@Test
	public void findByUsuarioTeste() {
		
	}
	
	@Test
	public void findByPaginationUsuarioTeste() {
		
	}
}

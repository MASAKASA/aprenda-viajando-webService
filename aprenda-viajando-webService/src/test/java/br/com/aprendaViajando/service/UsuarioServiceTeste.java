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
		//Testa se o usuario foi cadastrado do banco de dados
		String nome = "marcio";
		String email ="marcioandredasilvaalmeida@gmail.com";
		
		Usuario usuario = new Usuario();
		
		usuario.setNome(nome);
		usuario.setEmail(email);
		
		System.out.println();
		System.out.println(usuario.getId());
		System.out.println();
		
		service.saveOrUpdate(usuario);
		
		System.out.println("Cadastro");
		System.out.println(usuario.getId());
		System.out.println();
		
		Optional<Usuario> optional = repository.findById(usuario.getId());
		
//		//Testa a duplicidade do nome e email no cadastro
//		Usuario usuario2 = new Usuario();
//		usuario2.setEmail(email);
//		usuario2.setNome(nome);
//		
//		service.saveOrUpdate(usuario2);
//		
//		assertFalse(usuario.getEmail().equals(usuario2.getEmail()));
//		assertFalse(usuario.getNome().equals(usuario2.getNome()));
		assertTrue(optional.isPresent());//Se true o usuario foi cadastrado com sucesso
	}
	
	@Test
	public void saveOrUpdateOfUpdateTeste() {
//		Usuario usuario = service.findById(1L);
//		
//		usuario.setNome("m");
//		
//		service.saveOrUpdate(usuario);
//		
//		Usuario usuario2 = service.findById(1L);
//		
//		//assertTrue("1".equals(String.valueOf(usuario.getId())));
//		//assertTrue("marcioandredasilvaalmeida@gmail.com".equals(usuario.getEmail()));
//		assertTrue("m".equals(usuario2.getNome()));
	}	
	
	@Test
	@Transactional(readOnly = true)
	public void findByIdCadastroInicialTeste() {
		Usuario usuario = service.findById(18L);
		
		assertTrue("18".equals(String.valueOf(usuario.getId())));
		assertTrue("marcioandredasilvaalmeida@gmail.com".equals(usuario.getEmail()));
		assertTrue("marcio".equals(usuario.getNome()));
	}
	
	@Test
	//@Transactional(readOnly = false)
	public void deleteTeste() {
		service.delete(18L);
		
		Optional<Usuario> optional = repository.findById(18L);
		
		assertFalse(optional.isPresent());
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

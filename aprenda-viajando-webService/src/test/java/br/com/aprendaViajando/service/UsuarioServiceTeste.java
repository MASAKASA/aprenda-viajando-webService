/**
 * 
 */
package br.com.aprendaViajando.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.aprendaViajando.domain.model.usuario.Usuario;
import br.com.aprendaViajando.domain.model.util.Endereco;
import br.com.aprendaViajando.domain.model.util.enuns.EstadoEnum;
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
	private EnderecoService serviceEndereco;
	
	@Autowired
	private UsuarioRepository repository;
	
	@Test
	@Commit
	public void saveOrUpdateOfSaveTeste() {
		//Testa se o usuario foi cadastrado do banco de dados
		String nome = "a";
		String email ="a@gmail.com";
		
		Usuario usuario = new Usuario();
		
		usuario.setNome(nome);
		usuario.setEmail(email);
		
		service.saveOrUpdate(usuario);
		
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
	@Commit
	public void saveOrUpdateOfUpdateTeste() {
		//Dados para o teste
		String nome = "z";
		String email ="z@gmail.com";
		
		//Cadastrar um usuario para depois atualizar
		Usuario usuario = new Usuario();
		
		usuario.setNome(nome);
		usuario.setEmail(email);
		
		service.saveOrUpdate(usuario);
		
		//Cadastrar um endereco para atualizar o usuario
		Endereco endereco = new Endereco();
		
		endereco.setLogradouro("rua Carneiro de Campos");
		endereco.setBairro("Petropolis");
		endereco.setCep("55032-370");
		endereco.setCidade("Caruaru");
		endereco.setComplemento("Casa");
		endereco.setEstado(EstadoEnum.PE);
		endereco.setNumero("408");
		endereco.setPontoReferencia("Perto do Village");
		
		serviceEndereco.saveOrUpdate(endereco);
		
		//TODO Cadastrar um avatar para atualizar o usuario
		
		
		//Atualizar o usuario
		usuario.setEndereco(endereco);
		
		service.saveOrUpdate(usuario);
		
		//Teste para confirmar a atualização
		Usuario uPersistente = service.findById(usuario.getId());
		
		assertTrue(uPersistente.getId() == usuario.getId());
		assertTrue(uPersistente.getEndereco().getId() == endereco.getId());
	}
	
	@Test
	@Commit
	public void deleteTeste() {
		//Dados para o teste
		String nome = "r";
		String email ="r@gmail.com";
		
		//Cadastrar um usuario para depois deletar
		Usuario usuario = new Usuario();
		
		usuario.setNome(nome);
		usuario.setEmail(email);
		
		service.saveOrUpdate(usuario);
		
		//Deletando o usuario
		service.delete(usuario.getId());
		                     
		//Testando a exclusão do usuario
		Optional<Usuario> optional = repository.findById(usuario.getId());
		
		assertFalse(optional.isPresent());
	}
	
	@Test
	@Commit
	public void findByIdCadastroInicialTeste() {
		//Dados para o teste
		String nome = "h";
		String email ="h@gmail.com";
		
		//Cadastrar um usuario para depois pesquisar
		Usuario usuario = new Usuario();
		
		usuario.setNome(nome);
		usuario.setEmail(email);
		
		service.saveOrUpdate(usuario);
		
		//Testando pesquisa de usuario
		Usuario uPersistente = service.findById(usuario.getId());

		assertTrue(nome.equals(uPersistente.getNome()));
		assertTrue(email.equals(uPersistente.getEmail()));
		assertTrue(uPersistente.getAvatar() == null);
		assertTrue(uPersistente.getEndereco() == null);
	}
	
	
	@Test
	public void findByIdCadastroCompletoTeste() {
		//Dados para o teste
		String nome = "h";
		String email ="h@gmail.com";
		
		//Cadastrar um usuario para depois pesquisar
		Usuario usuario = new Usuario();
		
		usuario.setNome(nome);
		usuario.setEmail(email);
		
		service.saveOrUpdate(usuario);
		
		//Cadastrar um endereco para atualizar o usuario
		Endereco endereco = new Endereco();
		
		endereco.setLogradouro("rua Carneiro de Campos");
		endereco.setBairro("Petropolis");
		endereco.setCep("55032-370");
		endereco.setCidade("Caruaru");
		endereco.setComplemento("Casa");
		endereco.setEstado(EstadoEnum.PE);
		endereco.setNumero("408");
		endereco.setPontoReferencia("Perto do Village");
		
		serviceEndereco.saveOrUpdate(endereco);
		
		//TODO Cadastrar um avatar para atualizar o usuario
		
		
		//Testando pesquisa de usuario
		Usuario uPersistente = service.findById(usuario.getId());

		assertTrue(uPersistente.getNome().equals(nome));
		assertTrue(uPersistente.getEmail().equals(email));
		assertTrue(uPersistente.getEndereco().getId() == endereco.getId());
		//TODO assertTrue(uPersistente.getAvatar() == null);
	}
	
	@Test
	public void findByNomeTeste() {
		//Dados para o teste
		String pesquisarPor = "m";
		String  totalPesquisa = "2";
		String nome = "marcio";
		String email ="marcioandredasilvaalmeida@gmail.com";
		String nome2 = "m";
		String email2 ="m@gmail.com";
		
		//Cadastrar um usuario para depois pesquisar
		Usuario usuario = new Usuario();
		Usuario usuario2 = new Usuario();
		
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario2.setNome(nome2);
		usuario2.setEmail(email2);
		
		service.saveOrUpdate(usuario);
		service.saveOrUpdate(usuario2);
		
		//Testando pesquisa de usuario
		List<Usuario> usuarios = service.findByNome(pesquisarPor);
		
		assertTrue(totalPesquisa.equals(String.valueOf(usuarios.size())));
	}
	
	@Test
	public void findByPaginationTeste() {
		String pagina = "1";
		String  totalPesquisa = "5	";
		
		Page page = service.findByPaginationUsuario(
				Integer.parseInt(pagina), Integer.parseInt(totalPesquisa));
		
		
		assertTrue(pagina.equals(String.valueOf(page.getNumber())));
		assertTrue(totalPesquisa.equals(String.valueOf(page.getSize())));
		
	}
}

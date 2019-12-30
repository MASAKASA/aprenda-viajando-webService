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
import br.com.aprendaViajando.domain.model.usuario.exceptions.EmailJaCadastradoException;
import br.com.aprendaViajando.domain.model.usuario.exceptions.NomeJaCadastradoException;
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
	public void saveOrUpdateOfSaveTeste() throws NomeJaCadastradoException, EmailJaCadastradoException {

		// Testa se o usuario foi cadastrado do banco de dados
		String nome = "a";
		String email = "a@gmail.com";

		Usuario usuario = new Usuario();

		usuario.setNome(nome);
		usuario.setEmail(email);

		service.saveOrUpdate(usuario);

		Optional<Usuario> optional = repository.findById(usuario.getId());

		assertTrue(optional.isPresent());
	}

	@Test
	@Commit
	public void saveOrUpdateOfUpdateTeste() throws NomeJaCadastradoException, EmailJaCadastradoException {

		// Dados para o teste
		String nome = "b";
		String email = "b@gmail.com";

		// Cadastrar um usuario para depois atualizar
		Usuario usuario = new Usuario();

		usuario.setNome(nome);
		usuario.setEmail(email);

		service.saveOrUpdate(usuario);

		// Cadastrar um endereco para atualizar o usuario
		Endereco endereco = new Endereco();

		endereco.setLogradouro("rua São Paulo");
		endereco.setBairro("Mauricio de Nassau");
		endereco.setCep("55012-000");
		endereco.setCidade("Caruaru");
		endereco.setComplemento("comercio");
		endereco.setEstado(EstadoEnum.PE);
		endereco.setNumero("25");
		endereco.setPontoReferencia("Por trás do campo do Central");

		serviceEndereco.saveOrUpdate(endereco);

		// TODO Cadastrar um avatar para atualizar o usuario

		// Atualizar o usuario
		usuario.setEndereco(endereco);

		service.saveOrUpdate(usuario);

		// Teste para confirmar a atualização
		Usuario uPersistente = service.findById(usuario.getId());

		assertTrue(uPersistente.getId() == usuario.getId());
		assertTrue(uPersistente.getEndereco().getId() == endereco.getId());
	}

	@Test
	@Commit
	public void atualizarNomeTeste() throws NomeJaCadastradoException {
		// Dados para o teste
		String nome = "3";
		String email = "c@gmail.com";
		String nomeAtualizado = "c";

		// Cadastrar um usuario para depois atualizar
		Usuario usuario = new Usuario();

		usuario.setNome(nome);
		usuario.setEmail(email);

		service.saveOrUpdate(usuario);

		// Atualização do nome do usuario
		service.atualizarNome(nome, nomeAtualizado);

		// Teste para confirmar a atualização
		Usuario uPersistente = service.findById(usuario.getId());

		assertTrue(uPersistente.getNome().equals(nomeAtualizado));
	}

	@Test
	@Commit
	public void atualizarEmailTeste() throws EmailJaCadastradoException {
		// Dados para o teste
		String nome = "d";
		String email = "4@gmail.com";
		String emailAtualizado = "d@gmail.com";

		// Cadastrar um usuario para depois atualizar
		Usuario usuario = new Usuario();

		usuario.setNome(nome);
		usuario.setEmail(email);

		service.saveOrUpdate(usuario);

		// Atualização do email do usuario
		service.atualizarEmail(email, emailAtualizado);

		// Teste para confirmar a atualização
		Usuario uPersistente = service.findById(usuario.getId());

		assertTrue(uPersistente.getEmail().equals(emailAtualizado));
	}

	@Test
	@Commit
	public void deleteTeste() throws NomeJaCadastradoException, EmailJaCadastradoException {

		// Dados para o teste
		String nome = "e";
		String email = "e@gmail.com";

		// Cadastrar um usuario para depois deletar
		Usuario usuario = new Usuario();

		usuario.setNome(nome);
		usuario.setEmail(email);

		service.saveOrUpdate(usuario);

		// Deletando o usuario
		service.delete(usuario.getId());

		// Testando a exclusão do usuario
		Optional<Usuario> optional = repository.findById(usuario.getId());

		assertFalse(optional.isPresent());
	}

	@Test
	@Commit
	public void findByIdCadastroInicialTeste() throws NomeJaCadastradoException, EmailJaCadastradoException {

		// Dados para o teste
		String nome = "f";
		String email = "f@gmail.com";

		// Cadastrar um usuario para depois pesquisar
		Usuario usuario = new Usuario();

		usuario.setNome(nome);
		usuario.setEmail(email);

		service.saveOrUpdate(usuario);

		// Testando pesquisa de usuario
		Usuario uPersistente = service.findById(usuario.getId());

		assertTrue(nome.equals(uPersistente.getNome()));
		assertTrue(email.equals(uPersistente.getEmail()));
		assertTrue(uPersistente.getAvatar() == null);
		assertTrue(uPersistente.getEndereco() == null);
	}

	@Test
	@Commit
	public void findByIdCadastroCompletoTeste() throws NomeJaCadastradoException, EmailJaCadastradoException {

		// Dados para o teste
		String nome = "g";
		String email = "g@gmail.com";

		// TODO Cadastrar um avatar para atualizar o usuario

		// Cadastrar um endereco para atualizar o usuario
		Endereco endereco = new Endereco();

		endereco.setLogradouro("rua Carneiro de Campos");
		endereco.setBairro("Petropolis");
		endereco.setCep("55032-370");
		endereco.setCidade("Caruaru");
		endereco.setComplemento("Casa");
		endereco.setEstado(EstadoEnum.PE);
		endereco.setNumero("408");
		endereco.setPontoReferencia("Perto do Village");

		// Cadastrar um usuario para depois pesquisar
		Usuario usuario = new Usuario();

		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setEndereco(endereco);

		service.saveOrUpdate(usuario);

		// Testando pesquisa de usuario
		Usuario uPersistente = service.findById(usuario.getId());

		assertTrue(uPersistente.getNome().equals(nome));
		assertTrue(uPersistente.getEmail().equals(email));
		assertTrue(uPersistente.getEndereco().getId() == endereco.getId());
		// TODO assertTrue(uPersistente.getAvatar() == null);
	}

	@Test
	@Commit
	public void findByNomeTeste() throws NomeJaCadastradoException, EmailJaCadastradoException {

		// Dados para o teste
		String pesquisarPor = "m";
		String totalPesquisa = "2";
		String nome = "marcio";
		String email = "marcioandredasilvaalmeida@gmail.com";
		String nome2 = "m";
		String email2 = "m@gmail.com";

		// Cadastrar um usuario para depois pesquisar
		Usuario usuario = new Usuario();
		Usuario usuario2 = new Usuario();

		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario2.setNome(nome2);
		usuario2.setEmail(email2);

		service.saveOrUpdate(usuario);
		service.saveOrUpdate(usuario2);

		// Testando pesquisa de usuario
		List<Usuario> usuarios = service.findByNome(pesquisarPor);

		assertTrue(totalPesquisa.equals(String.valueOf(usuarios.size())));
	}

	@Test
	@Commit
	public void findByPaginationTeste() {
		String pagina = "1";
		String totalPesquisa = "6";

		Page page = service.findByPaginationUsuario(Integer.parseInt(pagina), Integer.parseInt(totalPesquisa));

		assertTrue(pagina.equals(String.valueOf(page.getNumber())));
		assertTrue(totalPesquisa.equals(String.valueOf(page.getSize())));

	}

	@Test
	@Commit
	public void cadastrarNomeBrancoOuNullTeste() 
			throws NomeJaCadastradoException, EmailJaCadastradoException {
		// Testa se o nome não esta em branco ou null
		String nome = " ";
		String email = "g@gmail.com";
		String nome2 = " ";
		String email2 = "h@gmail.com";
		
		Usuario usuario = new Usuario();
		Usuario usuario2 = new Usuario();
		
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario2.setNome(nome2);
		usuario2.setEmail(email2);
		
		service.saveOrUpdate(usuario);
		//service.saveOrUpdate(usuario2);
	}
	
	@Test
	@Commit
	public void cadastrarEmailInvalido() 
			throws NomeJaCadastradoException, EmailJaCadastradoException {
		// Testa se o email é válido
		String nome = "h";
		String email = " ";
		
		Usuario usuario = new Usuario();

		usuario.setNome(nome);
		usuario.setEmail(email);

		service.saveOrUpdate(usuario);
	}

	@Test(expected = NomeJaCadastradoException.class)
	public void NomeJaCadastradoExceptionTeste() throws EmailJaCadastradoException {
		// Testa se o já tem nome cadastrado do banco de dados
		String nome = "a";
		String email = "a@gmail.com";
		String mensagemErro = "Nome já cadastrado no sistema!";

		Usuario usuario = new Usuario();

		usuario.setNome(nome);
		usuario.setEmail(email);

		try {
			service.saveOrUpdate(usuario);
		} catch (NomeJaCadastradoException e) {
			e.printStackTrace();
			assertTrue(mensagemErro.equalsIgnoreCase(e.getMessage()));
		}
	}

	@Test(expected = EmailJaCadastradoException.class)
	public void EmailJaCadastradoExceptionTeste() throws NomeJaCadastradoException {
		// Testa se o e-mail já foi cadastrado do banco de dados
		String nome = "b";
		String email = "b@gmail.com";
		String mensagemErro = "Email já cadastrado no sistema!";

		Usuario usuario = new Usuario();

		usuario.setNome(nome);
		usuario.setEmail(email);

		try {
			service.saveOrUpdate(usuario);
		} catch (EmailJaCadastradoException e) {
			e.printStackTrace();
			assertTrue(mensagemErro.equalsIgnoreCase(e.getMessage()));
		}
	}
}

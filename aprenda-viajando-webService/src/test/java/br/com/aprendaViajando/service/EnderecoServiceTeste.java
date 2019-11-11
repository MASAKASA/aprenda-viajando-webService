/**
 * 
 */
package br.com.aprendaViajando.service;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.aprendaViajando.domain.model.util.Endereco;
import br.com.aprendaViajando.domain.model.util.enuns.EstadoEnum;
import br.com.aprendaViajando.domain.repository.util.EnderecoRepository;

/**
 * @author MARCIO
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class EnderecoServiceTeste {

	@Autowired
	private EnderecoService service;
	
	@Autowired
	private EnderecoRepository repository;
	
	@Test
	@Commit
	public void saveOrUpdateTeste() {
		Endereco endereco = new Endereco();
		
		endereco.setLogradouro("rua Carneiro de Campos");
		endereco.setBairro("Petropolis");
		endereco.setCep("55032-370");
		endereco.setCidade("Caruaru");
		endereco.setComplemento("Casa");
		endereco.setEstado(EstadoEnum.PE);
		endereco.setNumero("408");
		endereco.setPontoReferencia("Perto do Village");
		
		Endereco endereco2 = new Endereco();
		
		endereco2.setLogradouro("rua Carneiro de Campos");
		endereco2.setBairro("Petropolis");
		endereco2.setCep("55032-370");
		endereco2.setCidade("Caruaru");
		endereco2.setComplemento("Casa");
		endereco2.setEstado(EstadoEnum.PE);
		endereco2.setNumero("408");
		endereco2.setPontoReferencia("Perto do Village");
		
		service.saveOrUpdate(endereco);
		service.saveOrUpdate(endereco2);
		
		Optional<Endereco> optional = repository.findById(endereco.getId());
		
		assertTrue(optional.isPresent());//Se true o usuario foi cadastrado com sucesso
	}
	
	@Test
	public void findByIdTeste() {
		Endereco endereco = service.findById(1L);
		
		assertTrue("1".equals(String.valueOf(endereco.getId())));
	}
}

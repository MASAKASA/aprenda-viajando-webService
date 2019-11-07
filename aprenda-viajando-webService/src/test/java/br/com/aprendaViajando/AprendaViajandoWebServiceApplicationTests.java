package br.com.aprendaViajando;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import br.com.aprendaViajando.domain.model.competencias.Assunto;
import br.com.aprendaViajando.domain.model.competencias.Competencia;
import br.com.aprendaViajando.domain.model.pontoTuristico.PontoTuristico;
import br.com.aprendaViajando.domain.model.util.Avatar;
import br.com.aprendaViajando.domain.model.util.Endereco;
import br.com.aprendaViajando.domain.model.util.Telefone;
import br.com.aprendaViajando.domain.model.util.enuns.EstadoEnum;
import br.com.aprendaViajando.domain.model.util.enuns.TipoTelefoneEnum;
import br.com.aprendaViajando.service.CompetenciaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AprendaViajandoWebServiceApplicationTests {

	private Competencia competencia = new Competencia();
	
	private Assunto assunto = new Assunto();
	
	private PontoTuristico pontoTuristico = new PontoTuristico();
	
	private Avatar avartar = new Avatar();
	
	private Endereco endereco = new Endereco();
	
	private Telefone telefone = new Telefone();
	
	private CompetenciaService service = new CompetenciaService();
		
	@Test
	public void contextLoads() {
	}


	@Test
	public void competenciaCRUDTeste() {
		endereco.setBairro("rua do Alto do Moura");
		endereco.setCep("55000000");
		endereco.setCidade("Caruaru");
		endereco.setEstado(EstadoEnum.PE);
		endereco.setNumero("SN");
		

		telefone.setDdd("081");
		telefone.setNumero("37310855");
		telefone.setTipoTelefone(TipoTelefoneEnum.COMERCIAL);
		
		pontoTuristico.setNome("Alto do Moura");
		pontoTuristico.setEmail("altodomoura@email.com");
		pontoTuristico.setFaixaEtariaMinima(5);
		pontoTuristico.setHistoria("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhvhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
		pontoTuristico.setHorarioFuncionamento("De 6:oo às 18:00");
		pontoTuristico.setIngresso(new BigDecimal(0));
		pontoTuristico.setEndereco(endereco);
		
		competencia.setNome("Matemática");
		
		assunto.setDidatica("ddddddddddddddddddddddddddddddddddddd  dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd  dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd  dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd  dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd  dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd  ddddddddddddddddddddddddddddddddddd");
		assunto.setNome("Pocentagem");
		
		List<Telefone> telefonePontoTuristico = new ArrayList<>();
		telefonePontoTuristico.add(telefone);
		
		List<PontoTuristico> listaPontoTuristico = new ArrayList<>();
		listaPontoTuristico.add(pontoTuristico);
		
		List<Competencia> listaCompetencias = new ArrayList<>();
		listaCompetencias.add(competencia);
		
		List<Assunto> listaAssuntos = new ArrayList<>();
		listaAssuntos.add(assunto);
		
		telefone.setPontoTuristico(pontoTuristico);
		pontoTuristico.setListaTelefone(telefonePontoTuristico);
		
		pontoTuristico.setListaCompetencias(listaCompetencias);
		competencia.setListaPontoTuristico(listaPontoTuristico);
		
		competencia.setListaAssuntos(listaAssuntos);
		assunto.setCompetencia(competencia);
		
		service.saveOrUpdate(competencia);
		//Resultado esperado
		Long id = (long) 1;
		//Teste
		Assert.notNull(competencia.getId());
	}
}

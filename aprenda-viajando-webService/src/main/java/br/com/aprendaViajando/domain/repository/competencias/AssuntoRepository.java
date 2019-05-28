package br.com.aprendaViajando.domain.repository.competencias;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aprendaViajando.domain.model.competencias.Assunto;
import br.com.aprendaViajando.domain.model.competencias.Competencia;

/**
 * @author MARCIO
 *
 */
public interface AssuntoRepository extends JpaRepository<Assunto, Long>{

	/**
	 * Buscar por Assuntos para paginação conforme os dados no Page e ordena pelo
	 * nome da competencia
	 *  
	 * @param Pageable pageable com as quantidades de linhas e a posição para pesquisa no banco de dados
	 *  
	 * @return Page com os Assuntos encontrados ou vazio se nenhum resultado for encontrado
	 */
	Page<Assunto> findAllByOrderByCompetenciaNomeAsc(Pageable pageable);
	
	/**
	 * Buscar por Assuntos para paginação conforme os dados no Page e o campo competencia_id e ordena pelo
	 * nome da competencia
	 *  
	 * @param Pageable pageable com as quantidades de linhas e a posição para pesquisa no banco de dados
	 * 
	 * @param Competencia competencia com código para busca no banco de dados
	 *  
	 * @return Page com os Assuntos encontrados ou vazio se nenhum resultado for encontrado
	 */
	Page<Assunto> findByCompetenciaOrderByCompetenciaNomeAsc(Competencia competencia, Pageable pageable);
	
	/**
	 * Buscar Assunto conforme o parametro coincida com permalink o campo permalink
	 * 
	 * @param String permalink
	 * 
	 * @return Assunto encontrado ou um null se não tiver nenhum dado for encontrado
	 */
	Assunto findByPermalink(String permalink);
	
	/**
	 * Buscar Assuntos conforme o parametro coincida com qualquer parte do campo nome
	 * e ordena de forma ascesdente pelo campo nome
	 * 
	 * @param String nome para busca no banco de dados
	 * 
	 * @return List com Assuntos encontrados ou um List vazio se não tiver nenhum dado
	 * 		for encontrado
	 */
	List<Assunto> findByNomeContainingOrderByNomeAsc(String nome);
	
	/**
	 * Buscar Assuntos conforme o parametro coincida com a Competencia no campo competencia_id
	 * e ordena de forma ascesdente pelo campo nome
	 * 
	 * @param Competencia competencia com código para busca no banco de dados
	 * 
	 * @return List com Assuntos encontrados ou um List vazio se não tiver nenhum dado
	 * 		for encontrado
	 */
	List<Assunto> findByCompetenciaIdContainingOrderByNomeAsc(Competencia competencia);
}

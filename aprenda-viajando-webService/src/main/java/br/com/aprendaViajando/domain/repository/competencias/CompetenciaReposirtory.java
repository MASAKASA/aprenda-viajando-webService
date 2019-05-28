/**
 * 
 */
package br.com.aprendaViajando.domain.repository.competencias;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aprendaViajando.domain.model.competencias.Competencia;

/**
 * @author MARCIO
 *
 */
public interface CompetenciaReposirtory extends JpaRepository<Competencia, Long>{

	/**
	 * Buscar por Competencias para paginação conforme os dados no Page e ordena pelo nome
	 * 
	 * @param Pageable pageable com as quantidades de linhas e a posição para pesquisa no banco de dados
	 *  
	 * @return Page com os Competencias encontrados ou vazio se nenhum resultado for encontrado
	 */
	Page<Competencia> findAllByOrderByNomeAsc(Pageable pageable);
	
	/**
	 * Buscar Competencia conforme o parametro coincida com permalink o campo permalink
	 * 
	 * @param String permalink para consulta no banco de dados
	 * 
	 * @return Competencia encontrado ou um null se não tiver nenhum dado for encontrado
	 */
	Competencia findByPermalink(String permalink);
	
	/**
	 * Buscar Competencias conforme o parametro coincida com qualquer parte do campo nome
	 * e ordena de forma ascesdente pelo campo nome
	 * 
	 * @param String nome para busca no banco de dados
	 * 
	 * @return List com Competencias encontrados ou um List vazio se não tiver nenhum dado
	 * 		for encontrado
	 */
	List<Competencia> findByNomeContainingOrderByNomeAsc(String nome);
}

package br.com.aprendaViajando.domain.repository.pontoTuristico;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aprendaViajando.domain.model.pontoTuristico.Excursao;
import br.com.aprendaViajando.domain.model.pontoTuristico.enuns.StatusExcursao;

public interface ExcursaoRepository extends JpaRepository<Excursao, Long>{
	
	/**
	 * Buscar por Excursões para paginação conforme os dados no Page e ordena pelo nome
	 * 
	 * @param Pageable pageable com as quantidades de linhas e a posição para pesquisa no banco de dados
	 *  
	 * @return Page com os Excursõs encontrados ou vazio se nenhum resultado for encontrado
	 */
	Page<Excursao> findAllByOrderByNomeAsc(Pageable pageable);
	
	/**
	 * Buscar por Excursões para paginação conforme os dados no Page e o campo statusExcusao e ordena pelo nome
	 * 
	 * @param StatusExcursao statusExcursao para busca no banco de dados
	 * 
	 * @param Pageable pageable com as quantidades de linhas e a posição para pesquisa no banco de dados
	 *  
	 * @return Page com os Excursõs encontrados ou vazio se nenhum resultado for encontrado
	 */
	Page<Excursao> findByStatusExcursaoOrderByNomeAsc(StatusExcursao statusExcursao, Pageable pageable);
	
	/**
	 * Buscar Excursao conforme o parametro coincida com permalink o campo permalink
	 * 
	 * @param String permalink para consulta no banco de dados
	 * 
	 * @return Excursao encontrado ou um null se não tiver nenhum dado for encontrado
	 */
	Excursao findByPermalink(String permalink);
	
	/**
	 * Buscar Excursao conforme o parametro coincida com permalink os campos statusExcusao e permalink
	 * 
	 * @param StatusExcursao statusExcursao para busca no banco de dados
	 * 
	 * @param String permalink para consulta no banco de dados
	 * 
	 * @return Excursao encontrado ou um null se não tiver nenhum dado for encontrado
	 */
	Excursao findByStatusExcursaoAndPermalink(StatusExcursao statusExcursao, String permalink);
	
	/**
	 * Busca por excursões entre as data infornadas no campo dataExcusao
	 * e orde de forma ascendente pela dataExcusao
	 * 
	 * @param LocalDate dataInicial para pesquisa como data inicial
	 * 
	 * @param LocalDate dataFinal para pesquisa como data final
	 * 
	 * @return List com Excursao encontradas ou um List vazio se não tiver nenhum dado
	 * 		for encontrado
	 */
	List<Excursao> findByDataExcursaoBetweenOrderByDataExcursaoAsc(
			LocalDateTime dataInicial, LocalDateTime dataFinal);
	
	/**
	 * Busca por excursões entre as data infornadas no campo dataExcusao
	 * e ordena de forma descendente pela dataExcusao
	 * 
	 * @param LocalDate dataInicial para pesquisa como data inicial
	 * 
	 * @param LocalDate dataFinal para pesquisa como data final
	 * 
	 * @return List com Excursao encontradas ou um List vazio se não tiver nenhum dado
	 * 		for encontrado
	 */
	List<Excursao> findByDataExcursaoBetweenOrderByDataExcursaoDesc(
			LocalDateTime dataInicial, LocalDateTime dataFinal);
	
	/**
	 * Busca por excursões entre as data infornadas no campo dataExcusao
	 * e ordena de forma ascendente pela dataExcusao
	 * 
	 * @param StatusExcursao statusExcursao para busca no banco de dados
	 * 
	 * @param LocalDate dataInicial para pesquisa como data inicial
	 * 
	 * @param LocalDate dataFinal para pesquisa como data final
	 * 
	 * @return List com Excursao encontradas ou um List vazio se não tiver nenhum dado
	 * 		for encontrado
	 */
	List<Excursao> findByStatusExcursaoAndDataExcursaoBetweenOrderByDataExcursaoAsc(
			StatusExcursao statusExcursao, LocalDateTime dataInicial, LocalDateTime dataFinal);
	
	/**
	 * Busca por excursões entre as data infornadas no campo dataExcusao
	 * e ordena de forma descendente pela dataExcusao
	 * 
	 * @param StatusExcursao statusExcursao para busca no banco de dados
	 * 
	 * @param LocalDate dataInicial para pesquisa como data inicial
	 * 
	 * @param LocalDate dataFinal para pesquisa como data final
	 * 
	 * @return List com Excursao encontradas ou um List vazio se não tiver nenhum dado
	 * 		for encontrado
	 */
	List<Excursao> findByStatusExcursaoAndDataExcursaoBetweenOrderByDataExcursaoDesc(
			StatusExcursao statusExcursao, LocalDateTime dataInicial, LocalDateTime dataFinal);
}

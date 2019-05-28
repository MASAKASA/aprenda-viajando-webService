package br.com.aprendaViajando.domain.repository.pontoTuristico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aprendaViajando.domain.model.pontoTuristico.PontoTuristico;

public interface PontoTuristicoRepository extends JpaRepository<PontoTuristico, Long>{

	/**
	 * Buscar por PontosTuristicos para paginação conforme os dados no Page e ordena pelo nome
	 * 
	 * @param Pageable pageable com as quantidades de linhas e a posição para pesquisa no banco de dados
	 *  
	 * @return Page com os PontosTuristicos encontrados ou vazio se nenhum resultado for encontrado
	 */
	Page<PontoTuristico> findAllByOrderByNomeAsc(Pageable pageable);
	
	/**
	 * Buscar PontoTuristico conforme o parametro coincida com permalink o campo permalink
	 * 
	 * @param String permalink para consulta no banco de dados
	 * 
	 * @return PontoTuristico encontrado ou um null se não tiver nenhum dado for encontrado
	 */
	PontoTuristico findByPermalink(String permalink);
}

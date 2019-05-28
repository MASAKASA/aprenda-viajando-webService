package br.com.aprendaViajando.domain.repository.pontoTuristico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aprendaViajando.domain.model.pontoTuristico.Avaliacao;
import br.com.aprendaViajando.domain.model.pontoTuristico.PontoTuristico;

public interface AvaliacaoRetositoy extends JpaRepository<Avaliacao, Long>{

	/**
	 * Buscar por Avaliacões para paginação conforme os dados no Page e ordena pelo nome do ponto
	 * turistico
	 * 
	 * @param Pageable pageable com as quantidades de linhas e a posição para pesquisa no banco de dados
	 *  
	 * @return Page com os Avaliacões encontrados ou vazio se nenhum resultado for encontrado
	 */
	Page<Avaliacao> findAllByOrderByPontoTuristicoNomeAsc(Pageable pageable);
	
	/**
	 * Busca por avaliação conforme o campo pontoTuristico_id
	 * 
	 * @param PontoTuristico pontoTuristico com id par busca no  banco de dados
	 * 
	 * @return Avaliação se for encontrado ou null se não tiver nenhum dado
	 * 		for encontrado
	 */
	Avaliacao findByPontoTuristicoId(PontoTuristico pontoTuristico);
}

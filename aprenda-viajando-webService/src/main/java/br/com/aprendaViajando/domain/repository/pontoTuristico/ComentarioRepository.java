package br.com.aprendaViajando.domain.repository.pontoTuristico;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aprendaViajando.domain.model.competencias.Assunto;
import br.com.aprendaViajando.domain.model.competencias.Competencia;
import br.com.aprendaViajando.domain.model.pontoTuristico.Comentario;
import br.com.aprendaViajando.domain.model.pontoTuristico.PontoTuristico;
import br.com.aprendaViajando.domain.model.usuario.Usuario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

	/**
	 * Buscar por Comentarios para paginação conforme os dados no Page e ponto_turistico_id 
	 * e ordena pela DataComentario deforma descendente
	 * 
	 * @param PontoTuristico pontoTuristico com código para busca no banco de dados
	 *  
	 * @param Pageable pageable com  as quantidades de linhas e a posição para pesquisa no banco de dados
	 *  
	 * @return Page com os Comentarios encontrados ou vazio se nenhum resultado for encontrado
	 */
	Page<Comentario> findByPontoTuristicoIdOrderByDataComentarioDesc(PontoTuristico pontoTuristico, Pageable pageable);
	
	/**
	 * Buscar por Comentarios para paginação conforme os dados no Page e usuario_id e ordena pela e ordena 
	 * pela DataComentario deforma descendente
	 * 
	 * @param Usuario usuario com código para busca no banco de dados
	 *  
	 * @param Pageable pageable com  as quantidades de linhas e a posição para pesquisa no banco de dados
	 *  
	 * @return Page com os Comentarios encontrados ou vazio se nenhum resultado for encontrado
	 */
	Page<Comentario> findByUsuarioIdOrderByDataComentarioDesc(Usuario usuario, Pageable pageable);
	
	/**
	 * Buscar por Comentarios para paginação conforme os dados no Page e ponto_turistico_id eusuario_id
	 * e ordena pela DataComentario deforma descendente
	 * 
	 * @param PontoTuristico pontoTuristico com código para busca no banco de dados
	 * @param Usuario usuario com código para busca no banco de dados
	 *  
	 * @param Pageable pageable com  as quantidades de linhas e a posição para pesquisa no banco de dados
	 *  
	 * @return Page com os Comentarios encontrados ou vazio se nenhum resultado for encontrado
	 */
	Page<Comentario> findByPontoTuristicoIdAndUsuarioIdOrderByDataComentarioDesc(PontoTuristico pontoTuristico, Usuario usuario, Pageable pageable);
	
	/**
	 * Buscar Comentarios conforme o parametro coincida com o usuario no campo usuario_id
	 * e ordena de forma descesdente pelo campo dataComentario
	 * 
	 * @param Usuario usuario com código para busca no banco de dados
	 * 
	 * @return List com Comentarios encontrados ou um List vazio se não tiver nenhum dado
	 * 		for encontrado
	 */
	List<Comentario> findByUsuarioIdOrderByDataComentarioDesc(Usuario usuario);
	
	/**
	 * Buscar Comentarios conforme o parametro coincida com o pontoTuristico no campo pontoTuristico_id
	 * e ordena de forma descesdente pelo campo dataComentario
	 * 
	 * @param PontoTuristico pontoTuristico com código para busca no banco de dados
	 * 
	 * @return List com Comentarios encontrados ou um List vazio se não tiver nenhum dado
	 * 		for encontrado
	 */
	List<Comentario> findByPontoTuristicoIdOrderByDataComentarioDesc(PontoTuristico pontoTuristico);
	
	/**
	 * Buscar Comentarios conforme o parametro coincida com o pontoTuristico no campo pontoTuristico_id,
	 * o usuario no campo usuario_id e ordena de forma descesdente pelo campo dataComentario
	 * 
	 * @param PontoTuristico pontoTuristico com código para busca no banco de dados
	 * 
	 * @param Usuario usuario com código para busca no banco de dados
	 * 
	 * @return List com Comentarios encontrados ou um List vazio se não tiver nenhum dado
	 * 		for encontrado
	 */
	List<Comentario> findByPontoTuristicoIdAndUsuarioIdOrderByDataComentarioDesc(
											PontoTuristico pontoTuristico, Usuario usuario);
}

package br.com.aprendaViajando.domain.repository.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aprendaViajando.domain.model.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	/**
	 * Buscar por Usuarios para paginação conforme os dados no Page e ordena pelo nome
	 * 
	 * @param Pageable pageable com as quantidades de linhas e a posição para pesquisa no banco de dados
	 *  
	 * @return Page com os Usuarios encontrados ou vazio se nenhum resultado for encontrado
	 */
	Page<Usuario> findAllByOrderByNomeAsc(Pageable pageable);
	
	/**
	 * Buscar Usuarios conforme o parametro coincida com qualquer parte do campo nome 
	 * e ordena de forma ascendente pelo nome
	 * 
	 * @param String nome para consulta no banco de dados
	 * 
	 * @return Usuarios encontrado ou um null se não tiver nenhum dado for encontrado
	 */
	List<Usuario> findByNomeContainingOrderByNomeAsc(String nome);
	
	
	/**
	 * Buscar Usuario conforme o parametro coincida com qualquer com campo nome 
	 * 
	 * @param String nome para consulta no banco de dados
	 * 
	 * @return Usuario encontrado ou um null se não tiver nenhum dado for encontrado
	 */
	Optional<Usuario> findByNome(String nome);
	
	/**
	 * Buscar Usuario conforme o parametro coincida com qualquer com campo email 
	 * 
	 * @param String nome para consulta no banco de dados
	 * 
	 * @return Usuario encontrado ou um null se não tiver nenhum dado for encontrado
	 */
	Optional<Usuario> findByEmail(String email);
}

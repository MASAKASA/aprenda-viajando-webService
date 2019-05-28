package br.com.aprendaViajando.domain.repository.util;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aprendaViajando.domain.model.usuario.Usuario;
import br.com.aprendaViajando.domain.model.util.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Long>{

	/**
	 * Busca por Avatar do usuario conforme o id de campo usuario_id
	 * 
	 * @param Usuario usuario com id para busca o banco de dados
	 * 
	 * @return Avatar do usuario ou null se não nenhum dado encontrado  
	 */
	Avatar findByUsuarioId(Usuario usuario);
	
	/**
	 * Busca por Avatar do Ponto Turistico conforme o id de campo ponto_turistico_id
	 * 
	 * @param Usuario usuario com id para busca o banco de dados
	 * 
	 * @return Avatar do Ponto Turistico ou null se não nenhum dado encontrado  
	 */
	Avatar findByPontoTuristicoId(Usuario usuario);
}

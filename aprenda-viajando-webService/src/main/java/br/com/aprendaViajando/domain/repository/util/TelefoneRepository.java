package br.com.aprendaViajando.domain.repository.util;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aprendaViajando.domain.model.pontoTuristico.PontoTuristico;
import br.com.aprendaViajando.domain.model.usuario.Usuario;
import br.com.aprendaViajando.domain.model.util.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long>{

	/**
	 * Busca por Telefones do usuario conforme o id de campo usuario_id e ordena por campo tipoTelefone
	 * 
	 * @param PontoTuristico pontoTuristico com id para busca no banco de dados
	 * 
	 * @return List com PontoTuristico encontrados ou null se não nenhum dado encontrado
	 */
	List<Telefone> findByPontoTuristicoIdOrderByTipoTelefoneAsc(PontoTuristico pontoTuristico);
	
	/**
	 * Busca por Telefones do usuarios conforme o id de campo usuario_id e ordena por campo tipoTelefone
	 * 
	 * @param Usuario usuario com id para busca no banco de dados
	 * 
	 * @return List com Usuario encontrados ou null se não nenhum dado encontrado
	 */
	List<Telefone> findByUsuarioIdOrderByTipoTelefoneAsc(Usuario usuario);
}

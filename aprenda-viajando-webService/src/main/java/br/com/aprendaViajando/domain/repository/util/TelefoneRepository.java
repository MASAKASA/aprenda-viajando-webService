package br.com.aprendaViajando.domain.repository.util;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aprendaViajando.domain.model.pontoTuristico.PontoTuristico;
import br.com.aprendaViajando.domain.model.usuario.Usuario;
import br.com.aprendaViajando.domain.model.util.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long>{

	List<Telefone> findByPontoTuristicoOrderByTipoTelefoneAcs(PontoTuristico pontoTuristico);
	
	List<Telefone> findByUsuarioOrderByTipoTelefoneAsc(Usuario usuario);
}

package br.com.aprendaViajando.domain.repository.pontoTuristico;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aprendaViajando.domain.model.pontoTuristico.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

}

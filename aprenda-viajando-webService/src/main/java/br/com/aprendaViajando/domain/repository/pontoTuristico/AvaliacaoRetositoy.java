package br.com.aprendaViajando.domain.repository.pontoTuristico;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aprendaViajando.domain.model.pontoTuristico.Avaliacao;

public interface AvaliacaoRetositoy extends JpaRepository<Avaliacao, Long>{

}

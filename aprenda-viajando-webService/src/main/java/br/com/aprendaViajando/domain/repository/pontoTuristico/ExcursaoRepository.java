package br.com.aprendaViajando.domain.repository.pontoTuristico;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aprendaViajando.domain.model.pontoTuristico.Excursao;

public interface ExcursaoRepository extends JpaRepository<Excursao, Long>{

}

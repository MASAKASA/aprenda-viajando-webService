package br.com.aprendaViajando.domain.repository.util;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aprendaViajando.domain.model.util.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long>{

}

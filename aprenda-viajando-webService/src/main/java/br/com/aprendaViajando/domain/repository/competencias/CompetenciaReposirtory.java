/**
 * 
 */
package br.com.aprendaViajando.domain.repository.competencias;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aprendaViajando.domain.model.competencias.Competencia;

/**
 * @author MARCIO
 *
 */
public interface CompetenciaReposirtory extends JpaRepository<Competencia, Long>{

}

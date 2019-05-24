package br.com.aprendaViajando.domain.repository.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aprendaViajando.domain.model.usuario.Login;

public interface LoginRepository extends JpaRepository<Login, Long>{

}

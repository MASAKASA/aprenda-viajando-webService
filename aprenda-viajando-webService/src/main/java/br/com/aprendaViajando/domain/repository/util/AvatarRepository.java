package br.com.aprendaViajando.domain.repository.util;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aprendaViajando.domain.model.util.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Long>{

}

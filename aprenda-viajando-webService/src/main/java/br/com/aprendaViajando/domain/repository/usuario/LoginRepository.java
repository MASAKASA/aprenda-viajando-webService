package br.com.aprendaViajando.domain.repository.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aprendaViajando.domain.model.usuario.Login;
import br.com.aprendaViajando.domain.model.usuario.Usuario;
import br.com.aprendaViajando.domain.model.usuario.enus.StatusLogin;

public interface LoginRepository extends JpaRepository<Login, Long>{

	/**
	 * Buscar login de Usuario conforme o parametro coincida com as parte dos campo login,
	 * senha e com status indicado
	 * 
	 * @param String login para busca no banco de dados
	 * 
	 * @param String senha para busca no banco de dados
	 * 
	 * @param StatusLogin statusLogin para busca no banco de dados
	 * 
	 * @return Usuario encontrado ou Funcionario null
	 */
	Usuario findByLoginAndSenhaAndStatusLogin(
			String login, String senha, StatusLogin statusLogin);
}

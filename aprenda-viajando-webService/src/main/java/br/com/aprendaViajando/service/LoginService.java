/**
 * 
 */
package br.com.aprendaViajando.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.aprendaViajando.domain.model.usuario.Usuario;
import br.com.aprendaViajando.domain.model.usuario.enus.StatusLogin;
import br.com.aprendaViajando.domain.repository.usuario.LoginRepository;

/**
 * @author MARCIO
 *
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class LoginService {

	@Autowired
	private LoginRepository repository;
	
	public Usuario findByLogin(String login, String senha, StatusLogin statusLogin) {
		return repository.findByLoginAndSenhaAndStatusLogin(login, senha, statusLogin);
	}
}

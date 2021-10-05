package br.com.aprendaViajando.service.servicesInterfaces.usuario;

import br.com.aprendaViajando.domain.model.usuario.exceptions.EmailJaCadastradoException;
import br.com.aprendaViajando.domain.model.usuario.exceptions.NomeJaCadastradoException;
import br.com.aprendaViajando.service.servicesInterfaces.ServiceInterface;

public interface UsuarioServiceInterface <O, S, L> extends ServiceInterface<O, S, L>{
	
	public O saveOrUpdate(O objeto)
			throws NomeJaCadastradoException, EmailJaCadastradoException;

	public O atualizarNome(S nomeAntigo, S nomeAtualizado) 
			throws NomeJaCadastradoException;

	public void atualizarEmail(S emailAntigo, S emailAtualizado)
			throws EmailJaCadastradoException;
}

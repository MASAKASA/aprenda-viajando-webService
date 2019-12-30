/**
 * 
 */
package br.com.aprendaViajando.domain.model.usuario.exceptions;

/**
 * @author SMARTSYS-MARCIO
 *
 */
public class NomeJaCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String mensagem;

	public NomeJaCadastradoException(String mensagem) {
		super(mensagem);
		this.mensagem = mensagem;
	}
}

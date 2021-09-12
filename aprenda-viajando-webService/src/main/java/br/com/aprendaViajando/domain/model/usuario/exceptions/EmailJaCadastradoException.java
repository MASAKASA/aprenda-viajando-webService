/**
 * 
 */
package br.com.aprendaViajando.domain.model.usuario.exceptions;

/**
 * @author SMARTSYS-MARCIO
 *
 */
public class EmailJaCadastradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String mensagem;

	public EmailJaCadastradoException(String mensagem) {
		super(mensagem);
		this.mensagem = mensagem;
	}
}

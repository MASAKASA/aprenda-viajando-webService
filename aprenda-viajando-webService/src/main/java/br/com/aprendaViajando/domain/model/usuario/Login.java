package br.com.aprendaViajando.domain.model.usuario;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.AbstractPersistable;

import br.com.aprendaViajando.domain.model.usuario.enus.StatusLogin;

@Entity
@Table(name="logins")
public class Login extends AbstractPersistable<Long> {
	
	@NotBlank //Informando que nao pode ter valor nulo nem vazio
	@Size(min = 3) //Tamanho minimo
	@Column(name="login_usuario", nullable = false, unique = true) //Nome da coluna e informando que nao pode ser vazio com o false
	private String login;
	
	@NotBlank //Informando que nao pode ter valor nulo nem vazio
	@Size(min = 3, max = 8) //Tamanho minimo e maximo
	@Column(name="senha_usuario", nullable=false) //Nome da coluna e informando que nao pode ser vazio com o false
	private String senha;
	
	@Size(min = 0)
	@Column(name = "acessos")
	private Long acesso;
	
	@NotBlank
	@Column(name = "status_login", nullable=false)
	@Enumerated(EnumType.STRING)
	private StatusLogin statusLogin;
	
	@NotBlank
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	@Override
	protected void setId(Long id) {
		super.setId(id);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public StatusLogin getStatusLogin() {
		return statusLogin;
	}

	public void setStatusLogin(StatusLogin statusLogin) {
		this.statusLogin = statusLogin;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}

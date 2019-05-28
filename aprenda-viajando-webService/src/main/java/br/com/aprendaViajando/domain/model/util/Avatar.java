package br.com.aprendaViajando.domain.model.util;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.AbstractPersistable;

import br.com.aprendaViajando.domain.model.pontoTuristico.PontoTuristico;
import br.com.aprendaViajando.domain.model.usuario.Usuario;

@Entity
@Table(name = "avatares")
public class Avatar extends AbstractPersistable<Long> {

	@Column(nullable = false)
	private String titulo;
	
	@Column(nullable = false)
	private String tipo;
	
	@Lob
	@Column(nullable = false)
	private byte[] avatar;
	
	@NotBlank
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "avatar_ponto_turistico_id")
	private PontoTuristico avatarPontoTuristico;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "ponto_turistico_id")
	private PontoTuristico pontoTuristico;
	
	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public PontoTuristico getAvatarPontoTuristico() {
		return avatarPontoTuristico;
	}

	public void setAvatarPontoTuristico(PontoTuristico avatarPontoTuristico) {
		this.avatarPontoTuristico = avatarPontoTuristico;
	}

	public PontoTuristico getPontoTuristico() {
		return pontoTuristico;
	}

	public void setPontoTuristico(PontoTuristico pontoTuristico) {
		this.pontoTuristico = pontoTuristico;
	}
}

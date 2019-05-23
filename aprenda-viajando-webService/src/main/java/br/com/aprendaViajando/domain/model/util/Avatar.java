package br.com.aprendaViajando.domain.model.util;

import javax.persistence.*;

import org.springframework.data.jpa.domain.AbstractPersistable;

import br.com.aprendaViajando.domain.model.pontoTuristico.PontoTuristico;

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

	public PontoTuristico getPontoTuristico() {
		return pontoTuristico;
	}

	public void setPontoTuristico(PontoTuristico pontoTuristico) {
		this.pontoTuristico = pontoTuristico;
	}
}

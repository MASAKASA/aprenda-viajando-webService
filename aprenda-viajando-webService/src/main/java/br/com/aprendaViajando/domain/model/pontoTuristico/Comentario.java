package br.com.aprendaViajando.domain.model.pontoTuristico;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.com.aprendaViajando.domain.model.usuario.Usuario;

@Entity
@Table(name = "comentarios")
public class Comentario extends AbstractPersistable<Long> {

	@NotBlank
	@Column(nullable = false, columnDefinition = "TEXT")
	private String comentario;
	
	@NotBlank
	@DateTimeFormat(iso = ISO.DATE_TIME, pattern = "yyyy-MM-dd'T'HH:mm:ss") 
	@Column(name = "data_comentario", nullable = false)
	private LocalDate dataComentario;
	
	@NotBlank
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "usuario_id", nullable = false) 
	private Usuario usuario;
	
	@NotBlank
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "ponto_turistico_id", nullable = false) 
	private PontoTuristico pontoTuristico;

	@Override
	protected void setId(Long id) {
		super.setId(id);
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public LocalDate getDataComentario() {
		return dataComentario;
	}

	public void setDataComentario(LocalDate dataComentario) {
		this.dataComentario = dataComentario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public PontoTuristico getPontoTuristico() {
		return pontoTuristico;
	}

	public void setPontoTuristico(PontoTuristico pontoTuristico) {
		this.pontoTuristico = pontoTuristico;
	}
}

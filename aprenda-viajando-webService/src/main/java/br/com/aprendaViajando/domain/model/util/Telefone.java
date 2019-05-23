package br.com.aprendaViajando.domain.model.util;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.AbstractPersistable;

import br.com.aprendaViajando.domain.model.pontoTuristico.PontoTuristico;
import br.com.aprendaViajando.domain.model.usuario.Usuario;
import br.com.aprendaViajando.domain.model.util.enuns.TipoTelefoneEnum;

@Entity
@Table(name="telefones")
public class Telefone  extends AbstractPersistable<Long> {
	
	@NotBlank
	private String ddd;
	
	@NotBlank
	private String numero;
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoTelefoneEnum tipoTelefone;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "ponto_turistico_id")
	private PontoTuristico pontoTuristico;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@Override
	protected void setId(Long id) {
		super.setId(id);
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public TipoTelefoneEnum getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(TipoTelefoneEnum tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public PontoTuristico getPontoTuristico() {
		return pontoTuristico;
	}

	public void setPontoTuristico(PontoTuristico pontoTuristico) {
		this.pontoTuristico = pontoTuristico;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}

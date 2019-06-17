package br.com.aprendaViajando.domain.model.pontoTuristico;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.AbstractPersistable;

import br.com.aprendaViajando.domain.model.usuario.Usuario;

@Entity
@Table(name = "avaliacoes")
public class Avaliacao extends AbstractPersistable<Long> {

	@Column(name="total_das_avaliacoes") 
	@Size(min = 0)
	private Integer totalDasAvaliacoes;
	
	@Column(name="total_dos_avaliadores") 
	@Size(min = 0)
	private Integer totalDosAvaliadores;
	
	@Column(name="media_avaliacao") 
	@Size(min = 0)
	private Integer mediaAvaliacao;
	
	@Column(name="avaliacao_atual") 
	@Size(min = 0)
	private Integer avaliacaoAtual;
	
	@Column(name="avaliacao_usuario") 
	@Size(min = 0)
	private Integer avaliacaoUsuario;
	
	@NotBlank
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "ponto_turistico_id")
	private PontoTuristico pontoTuristico;
	
	@NotBlank
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@Override
	protected void setId(Long id) {
		super.setId(id);
	}

	public Integer getTotalDasAvaliacoes() {
		return totalDasAvaliacoes;
	}

	public void setTotalDasAvaliacoes(Integer totalDasAvaliacoes) {
		this.totalDasAvaliacoes = totalDasAvaliacoes;
	}

	public Integer getTotalDosAvaliadores() {
		return totalDosAvaliadores;
	}

	public void setTotalDosAvaliadores(Integer totalDosAvaliadores) {
		this.totalDosAvaliadores = totalDosAvaliadores;
	}

	public Integer getMediaAvaliacao() {
		return mediaAvaliacao;
	}

	public void setMediaAvaliacao(Integer mediaAvaliacao) {
		this.mediaAvaliacao = mediaAvaliacao;
	}

	public Integer getAvaliacaoAtual() {
		return avaliacaoAtual;
	}

	public void setAvaliacaoAtual(Integer avaliacaoAtual) {
		this.avaliacaoAtual = avaliacaoAtual;
	}

	public Integer getAvaliacaoUsuario() {
		return avaliacaoUsuario;
	}

	public void setAvaliacaoUsuario(Integer avaliacaoUsuario) {
		this.avaliacaoUsuario = avaliacaoUsuario;
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

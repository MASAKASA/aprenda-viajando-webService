package br.com.aprendaViajando.domain.model.pontoTuristico;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "avaliacoes")
public class Avaliacao extends AbstractPersistable<Long> {

	@Column(name="total_avaliacoes") 
	@Size(min = 0)
	private Integer totalAvaliacoes;
	
	@Column(name="total_avaliadores") 
	@Size(min = 0)
	private Integer totalAvaliadores;
	
	@Column(name="media_avaliacao") 
	@Size(min = 0)
	private Integer mediaAvaliacao;
	
	@Column(name="avaliacao_atual") 
	@Size(min = 0)
	private Integer avaliacaoAtual;
	
	@NotBlank
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "ponto_turistico_id")
	private PontoTuristico pontoTuristico;

	@Override
	protected void setId(Long id) {
		super.setId(id);
	}

	public Integer getTotalAvaliacoes() {
		return totalAvaliacoes;
	}

	public void setTotalAvaliacoes(Integer totalAvaliacoes) {
		this.totalAvaliacoes = totalAvaliacoes;
	}

	public Integer getTotalAvaliadores() {
		return totalAvaliadores;
	}

	public void setTotalAvaliadores(Integer totalAvaliadores) {
		this.totalAvaliadores = totalAvaliadores;
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

	public PontoTuristico getPontoTuristico() {
		return pontoTuristico;
	}

	public void setPontoTuristico(PontoTuristico pontoTuristico) {
		this.pontoTuristico = pontoTuristico;
	}
}

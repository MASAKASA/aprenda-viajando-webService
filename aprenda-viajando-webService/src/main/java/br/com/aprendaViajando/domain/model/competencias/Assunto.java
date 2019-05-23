package br.com.aprendaViajando.domain.model.competencias;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "assuntos")
public class Assunto extends AbstractPersistable<Long> {

	@NotBlank
	@Column(nullable = false, length = 20) 
	@Size(max = 20)
	private String nome;
	
	@NotBlank
	@Column(nullable = false, unique = true)
	private String permalink;
	
	@NotBlank
	@Column(nullable = false, columnDefinition = "TEXT") 
	private String didatica;
	
	@NotBlank
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "competencia_id", nullable = false)
	private Competencia competencia;

	@Override
	protected void setId(Long id) {
		super.setId(id);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public String getDidatica() {
		return didatica;
	}

	public void setDidatica(String didatica) {
		this.didatica = didatica;
	}

	public Competencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}
}

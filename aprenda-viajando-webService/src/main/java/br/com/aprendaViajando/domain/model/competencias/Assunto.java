package br.com.aprendaViajando.domain.model.competencias;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "assuntos")
public class Assunto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "id_assunto")
	private Long id;

	@NotBlank
	@Column(nullable = false, unique = true, length = 20) 
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

	public Assunto() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assunto other = (Assunto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Assunto [id=" + id + ", nome=" + nome + ", permalink=" + permalink + ", didatica=" + didatica
				+ ", competencia=" + competencia + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

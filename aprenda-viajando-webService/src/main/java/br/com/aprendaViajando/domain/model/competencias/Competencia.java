package br.com.aprendaViajando.domain.model.competencias;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.aprendaViajando.domain.model.pontoTuristico.PontoTuristico;

@Entity
@Table(name = "competencias")
public class Competencia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "id_competencia")
	private Long id;

	@NotBlank
	@Column(nullable = false, unique = true, length = 20) 
	@Size(max = 20)
	private String nome;
	
	@NotBlank
	@Column(nullable = false, unique = true)
	private String permalink;
	
	@NotBlank
	@OneToMany(mappedBy = "competencia", cascade = CascadeType.ALL)
	private List<Assunto> listaAssuntos;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name = "pontos_turisticos_competencias",	
		joinColumns = @JoinColumn(name = "competencia_id"),
		inverseJoinColumns = @JoinColumn(name = "ponto_turistico_id")
	)
	private List<PontoTuristico> listaPontoTuristico;

	public Competencia() {
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
		Competencia other = (Competencia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Competencia [id=" + id + ", nome=" + nome + ", permalink=" + permalink + ", listaAssuntos="
				+ listaAssuntos + ", listaPontoTuristico=" + listaPontoTuristico + "]";
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

	public List<Assunto> getListaAssuntos() {
		return listaAssuntos;
	}

	public void setListaAssuntos(List<Assunto> listaAssuntos) {
		this.listaAssuntos = listaAssuntos;
	}

	public List<PontoTuristico> getListaPontoTuristico() {
		return listaPontoTuristico;
	}

	public void setListaPontoTuristico(List<PontoTuristico> listaPontoTuristico) {
		this.listaPontoTuristico = listaPontoTuristico;
	}
}

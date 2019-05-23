package br.com.aprendaViajando.domain.model.competencias;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.AbstractPersistable;

import br.com.aprendaViajando.domain.model.pontoTuristico.PontoTuristico;

@Entity
@Table(name = "competencias")
public class Competencia extends AbstractPersistable<Long> {

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
	
	@ManyToMany
	@JoinTable(
		name = "pontos_turisticos_competencias",	
		joinColumns = @JoinColumn(name = "competencia_id"),
		inverseJoinColumns = @JoinColumn(name = "ponto_turistico_id")
	)
	private List<PontoTuristico> listaPontoTuristico;
	
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

package br.com.aprendaViajando.domain.model.pontoTuristico;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.com.aprendaViajando.domain.model.pontoTuristico.enuns.StatusExcursao;
import br.com.aprendaViajando.domain.model.usuario.Usuario;

@Entity
@Table(name = "excursoes")
public class Excursao extends AbstractPersistable<Long> {

	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	@NotBlank
	@Column(nullable = false, unique = true)
	private String permalink;
	
	@DateTimeFormat(iso = ISO.DATE_TIME, pattern = "yyyy-MM-dd'T'HH:mm:ss") 
	@Column(name = "data_excursao", nullable = false)
	private LocalDate dataExcursao;
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusExcursao statusExcursao;

	@ManyToMany
	@JoinTable(
		name = "excursoes_coordenadores",	
		joinColumns = @JoinColumn(name = "excursao_id"),
		inverseJoinColumns = @JoinColumn(name = "coordenador_id")
	)
	private List<Usuario> listaCoordenadores;
	
	@ManyToMany
	@JoinTable(
		name = "excursoes_participantes",	
		joinColumns = @JoinColumn(name = "excursao_id"),
		inverseJoinColumns = @JoinColumn(name = "usuario_id")
	)
	private List<Usuario> listaParticipantes;
	
	@ManyToMany
	@JoinTable(
		name = "pontos_turisticos_excursoes",	
		joinColumns = @JoinColumn(name = "excursao_id"),
		inverseJoinColumns = @JoinColumn(name = "ponto_turistico_id")
	)
	private List<PontoTuristico> listPontoTuristico;

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

	public LocalDate getDataExcursao() {
		return dataExcursao;
	}

	public void setDataExcursao(LocalDate dataExcursao) {
		this.dataExcursao = dataExcursao;
	}

	public StatusExcursao getStatusExcursao() {
		return statusExcursao;
	}

	public void setStatusExcursao(StatusExcursao statusExcursao) {
		this.statusExcursao = statusExcursao;
	}

	public List<Usuario> getListaCoordenadores() {
		return listaCoordenadores;
	}

	public void setListaCoordenadores(List<Usuario> listaCoordenadores) {
		this.listaCoordenadores = listaCoordenadores;
	}

	public List<Usuario> getListaParticipantes() {
		return listaParticipantes;
	}

	public void setListaParticipantes(List<Usuario> listaParticipantes) {
		this.listaParticipantes = listaParticipantes;
	}

	public List<PontoTuristico> getListPontoTuristico() {
		return listPontoTuristico;
	}

	public void setListPontoTuristico(List<PontoTuristico> listPontoTuristico) {
		this.listPontoTuristico = listPontoTuristico;
	}
}

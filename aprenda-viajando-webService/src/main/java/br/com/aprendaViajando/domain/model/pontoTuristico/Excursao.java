package br.com.aprendaViajando.domain.model.pontoTuristico;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.aprendaViajando.domain.model.pontoTuristico.enuns.StatusExcursao;
import br.com.aprendaViajando.domain.model.usuario.Usuario;

@Entity
@Table(name = "excursoes")
public class Excursao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "id_excursao")
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	@NotBlank
	@Column(nullable = false, unique = true)
	private String permalink;
	
	@DateTimeFormat(iso = ISO.DATE_TIME, pattern = "yyyy-MM-dd'T'HH:mm:ss") 
	@Column(name = "data_excursao", nullable = false)
	private LocalDateTime dataExcursao;
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusExcursao statusExcursao;

	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name = "excursoes_coordenadores",	
		joinColumns = @JoinColumn(name = "excursao_id"),
		inverseJoinColumns = @JoinColumn(name = "coordenador_id")
	)
	private List<Usuario> listaCoordenadores;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name = "excursoes_participantes",	
		joinColumns = @JoinColumn(name = "excursao_id"),
		inverseJoinColumns = @JoinColumn(name = "usuario_id")
	)
	private List<Usuario> listaParticipantes;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name = "pontos_turisticos_excursoes",	
		joinColumns = @JoinColumn(name = "excursao_id"),
		inverseJoinColumns = @JoinColumn(name = "ponto_turistico_id")
	)
	private List<PontoTuristico> listPontoTuristico;

	public Excursao() {
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
		Excursao other = (Excursao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Excursao [id=" + id + ", nome=" + nome + ", permalink=" + permalink + ", dataExcursao=" + dataExcursao
				+ ", statusExcursao=" + statusExcursao + ", listaCoordenadores=" + listaCoordenadores
				+ ", listaParticipantes=" + listaParticipantes + ", listPontoTuristico=" + listPontoTuristico + "]";
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

	public LocalDateTime getDataExcursao() {
		return dataExcursao;
	}

	public void setDataExcursao(LocalDateTime dataExcursao) {
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

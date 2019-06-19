package br.com.aprendaViajando.domain.model.pontoTuristico;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.AbstractPersistable;

import br.com.aprendaViajando.domain.model.competencias.Competencia;
import br.com.aprendaViajando.domain.model.util.Avatar;
import br.com.aprendaViajando.domain.model.util.Endereco;
import br.com.aprendaViajando.domain.model.util.Telefone;

@Entity
@Table(name = "pontos_turisticos")
public class PontoTuristico extends AbstractPersistable<Long> {
	
	@NotBlank
	@Column(nullable = false, unique = true)
	private String nome;
	
	@NotBlank
	@Column(nullable = false, unique = true)
	private String permalink;
	
	@Email
	private String email;
	
	@NotBlank
	@Column(nullable = false)
	private String horarioFuncionamento;
	
	@NotBlank
	@Column(nullable = false, columnDefinition = "TEXT")
	private String historia;
	
	@NotBlank
	@Size(min = 0)
	@Column(name = "faixa_etaria_minima")
	private Integer faixaEtariaMinima;
	
	@NotBlank
	@DecimalMin("0.00")
	@Column(name="saldo_ingresso")
	private BigDecimal ingresso;	
	
	@NotBlank
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "avatar_id")
	private Avatar avatarPrincipal;
	
	@OneToMany(mappedBy = "pontoTuristico", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Avatar> listaAvatar;
	
	@OneToMany(mappedBy = "pontoTuristico", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Telefone> listaTelefone;
	
	@OneToMany(mappedBy = "pontoTuristico", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Comentario> listaComentarios;
	
	@ManyToMany
	@JoinTable(
		name = "pontos_turisticos_competencias",	
		joinColumns = @JoinColumn(name = "ponto_turistico_id"),
		inverseJoinColumns = @JoinColumn(name = "competencia_id")
	)
	private List<Competencia> listaCompetencias;
	
	@ManyToMany
	@JoinTable(
		name = "pontos_turisticos_excursoes",	
		joinColumns = @JoinColumn(name = "ponto_turistico_id"),
		inverseJoinColumns = @JoinColumn(name = "excursao_id")
	)
	private List<Excursao>	listaExcursoes;
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHorarioFuncionamento() {
		return horarioFuncionamento;
	}

	public void setHorarioFuncionamento(String horarioFuncionamento) {
		this.horarioFuncionamento = horarioFuncionamento;
	}

	public String getHistoria() {
		return historia;
	}

	public void setHistoria(String historia) {
		this.historia = historia;
	}

	public Integer getFaixaEtariaMinima() {
		return faixaEtariaMinima;
	}

	public void setFaixaEtariaMinima(Integer faixaEtariaMinima) {
		this.faixaEtariaMinima = faixaEtariaMinima;
	}

	public BigDecimal getIngresso() {
		return ingresso;
	}

	public void setIngresso(BigDecimal ingresso) {
		this.ingresso = ingresso;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Avatar getAvatarPrincipal() {
		return avatarPrincipal;
	}

	public void setAvatarPrincipal(Avatar avatarPrincipal) {
		this.avatarPrincipal = avatarPrincipal;
	}

	public List<Avatar> getListaAvatar() {
		return listaAvatar;
	}

	public void setListaAvatar(List<Avatar> listaAvatar) {
		this.listaAvatar = listaAvatar;
	}

	public List<Telefone> getListaTelefone() {
		return listaTelefone;
	}

	public void setListaTelefone(List<Telefone> listaTelefone) {
		this.listaTelefone = listaTelefone;
	}

	public List<Comentario> getListaComentarios() {
		return listaComentarios;
	}

	public void setListaComentarios(List<Comentario> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}

	public List<Competencia> getListaCompetencias() {
		return listaCompetencias;
	}

	public void setListaCompetencias(List<Competencia> listaCompetencias) {
		this.listaCompetencias = listaCompetencias;
	}

	public List<Excursao> getListaExcursoes() {
		return listaExcursoes;
	}

	public void setListaExcursoes(List<Excursao> listaExcursoes) {
		this.listaExcursoes = listaExcursoes;
	}
}

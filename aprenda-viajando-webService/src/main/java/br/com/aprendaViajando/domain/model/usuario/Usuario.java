package br.com.aprendaViajando.domain.model.usuario;

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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.AbstractPersistable;

import br.com.aprendaViajando.domain.model.pontoTuristico.Comentario;
import br.com.aprendaViajando.domain.model.pontoTuristico.Excursao;
import br.com.aprendaViajando.domain.model.util.Avatar;
import br.com.aprendaViajando.domain.model.util.Endereco;
import br.com.aprendaViajando.domain.model.util.Telefone;

@Entity
@Table(name = "usuarios")
public class Usuario extends AbstractPersistable<Long> {
	
	@NotBlank
	@Column(nullable = false, unique = true)
	private String nome;
	
	@Email
	private String email;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;
	
	@NotBlank
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "avatar_id")
	private Avatar avatar;
	
	@NotBlank
	@OneToMany(mappedBy = "usuario", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Telefone> listaTelefone;
	
	@OneToMany(mappedBy = "usuario", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Comentario> listaComenterio;
	
	@ManyToMany
	@JoinTable(
		name = "excursoes_coordenadores",	
		joinColumns = @JoinColumn(name = "coordenador_id"),
		inverseJoinColumns = @JoinColumn(name = "excursao_id")
	)
	private List<Excursao> listaCoordenacoes;
	
	@ManyToMany
	@JoinTable(
		name = "excursoes_participantes",	
		joinColumns = @JoinColumn(name = "usuario_id"),
		inverseJoinColumns = @JoinColumn(name = "excursao_id")
	)
	private List<Excursao> listaExcursoes;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public List<Telefone> getListaTelefone() {
		return listaTelefone;
	}

	public void setListaTelefone(List<Telefone> listaTelefone) {
		this.listaTelefone = listaTelefone;
	}

	public List<Comentario> getListaComenterio() {
		return listaComenterio;
	}

	public void setListaComenterio(List<Comentario> listaComenterio) {
		this.listaComenterio = listaComenterio;
	}

	public List<Excursao> getListaCoordenacoes() {
		return listaCoordenacoes;
	}

	public void setListaCoordenacoes(List<Excursao> listaCoordenacoes) {
		this.listaCoordenacoes = listaCoordenacoes;
	}

	public List<Excursao> getListaExcursoes() {
		return listaExcursoes;
	}

	public void setListaExcursoes(List<Excursao> listaExcursoes) {
		this.listaExcursoes = listaExcursoes;
	}
}

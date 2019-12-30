package br.com.aprendaViajando.domain.model.usuario;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.aprendaViajando.domain.model.pontoTuristico.Comentario;
import br.com.aprendaViajando.domain.model.pontoTuristico.Excursao;
import br.com.aprendaViajando.domain.model.util.Avatar;
import br.com.aprendaViajando.domain.model.util.Endereco;
import br.com.aprendaViajando.domain.model.util.Telefone;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id;
	
	@NotBlank//(message = "Nome não pode estar nulo ou branco!")
	@Column(nullable = false, unique = true)
	private String nome;
	
	@Email
	@Column(nullable = false, unique = true)
	private String email;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "avatar_id")
	private Avatar avatar;
	
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

	public Usuario() {
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", endereco=" + endereco + ", avatar="
				+ avatar + ", listaTelefone=" + listaTelefone + ", listaComenterio=" + listaComenterio
				+ ", listaCoordenacoes=" + listaCoordenacoes + ", listaExcursoes=" + listaExcursoes + "]";
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

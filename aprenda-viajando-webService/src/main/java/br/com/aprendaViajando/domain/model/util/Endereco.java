package br.com.aprendaViajando.domain.model.util;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.AbstractPersistable;

import br.com.aprendaViajando.domain.model.util.enuns.EstadoEnum;

@Entity
@Table(name = "enderecos")
public class Endereco  extends AbstractPersistable<Long> {

	@NotBlank
	private String logradouro;
	
	@NotBlank
	private String numero;
	
	@NotBlank
	private String bairro;
	
	@NotBlank
	private String cidade;
	
	@NotBlank
	private String cep;
	
	private String complemento;
	
	@NotBlank
	@Column(name="ponto_de_referencia")
	private String pontoReferencia;
	
	@NotBlank
	@Column(name="latitude")
	private String latitude;
	
	@NotBlank
	@Column(name="longitude")
	private String longitude;
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;

	@Override
	protected void setId(Long id) {
		super.setId(id);
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getPontoReferencia() {
		return pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}
}

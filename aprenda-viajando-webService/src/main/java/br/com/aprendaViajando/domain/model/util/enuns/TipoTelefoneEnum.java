package br.com.aprendaViajando.domain.model.util.enuns;

public enum TipoTelefoneEnum {

	FAX("Fax"),
	CONTATO("Contato"),
	COMERCIAL("Comercial"),
	RESIDENCIAL("Residencial"),
	CELULAR("Celular");
	
	private String tipoTelefone;

	private TipoTelefoneEnum(String tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public String getTipoTelefone() {
		return tipoTelefone;
	}
}

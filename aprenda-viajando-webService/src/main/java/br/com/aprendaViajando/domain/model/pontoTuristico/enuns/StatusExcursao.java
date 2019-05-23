package br.com.aprendaViajando.domain.model.pontoTuristico.enuns;

public enum StatusExcursao {

	ATIVO("Ativo"),
	INATIVO("Inativo");
	
	private String status;
	
	private StatusExcursao(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}

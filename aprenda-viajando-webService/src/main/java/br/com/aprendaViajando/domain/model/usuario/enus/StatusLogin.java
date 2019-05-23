package br.com.aprendaViajando.domain.model.usuario.enus;

public enum StatusLogin {

	ATIVO("Ativo"),
	INATIVO("Inativo");
	
	private String status;
	
	private StatusLogin(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}

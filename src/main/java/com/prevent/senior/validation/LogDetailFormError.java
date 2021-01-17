package com.prevent.senior.validation;

public class LogDetailFormError {
	
	private String campo;
	private String erro;
	
	public LogDetailFormError(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

}

package br.com.algaworks.algafood.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel","Mensagem incompreensível"),
	ENTIDADE_NAO_ECONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso","Entidade em uso"),
	DADOS_INVALIDOS("/dados-invalidos", "Um ou mais campos estão inválidos! Faça o "
			+ "preenchimento correto e tente novamente.");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title){
		this.uri = "https://algafood.com.br" + path;
		this.title = title;
	}
	
}

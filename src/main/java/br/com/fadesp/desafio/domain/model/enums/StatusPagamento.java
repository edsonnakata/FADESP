package br.com.fadesp.desafio.domain.model.enums;


public enum StatusPagamento {
	
	PENDENTE_PROCESSAMENTO("Pendente de Processamento"),
	PROCESSADO_SUCESSO("Processado com Sucesso"),
	PROCESSADO_FALHA("Processado com Falha");
	
	private final String descricao;

	private StatusPagamento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}

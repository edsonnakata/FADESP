package br.com.fadesp.desafio.domain.model.enums;


public enum MetodoPagamento {
	
	BOLETO("boleto"),	
	PIX("pix"),
	CREDITO("cartao_credito"), 
	DEBITO("cartao_debito");
	
	private final String descricao;

	private MetodoPagamento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}

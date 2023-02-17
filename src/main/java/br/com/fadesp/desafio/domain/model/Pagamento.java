package br.com.fadesp.desafio.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.fadesp.desafio.domain.model.enums.MetodoPagamento;
import br.com.fadesp.desafio.domain.model.enums.StatusPagamento;
import lombok.Data;

@Entity
@Data
public class Pagamento {
	
	@Id
	@Column(name = "pagamento_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(value = "codigo")
	private Integer pagamentoId;
	
	@NotBlank
	private String documento;
	
	@Column(name = "numero_cartao")
	private Integer numeroCartao;
	
	@NotNull
	@Column(name = "valor_pagamento")
	private BigDecimal valorPagamento;
	
	@JsonProperty(access = Access.READ_ONLY)
	@Column(name = "status_pagamento")
	private String statusPagamento;
	
	@Column(name = "metodo_pagamento")
	private String metodoPagamento;
	
	@PrePersist
	private void prePersist() {
		this.statusPagamento = StatusPagamento.PENDENTE_PROCESSAMENTO.getDescricao();
	}
}

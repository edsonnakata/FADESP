package br.com.fadesp.desafio.api.exceptionhandler;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class Problema {
	private Integer status;
	private OffsetDateTime dataHora;
	private String titulo;
}

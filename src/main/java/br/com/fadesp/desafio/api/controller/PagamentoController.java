package br.com.fadesp.desafio.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fadesp.desafio.domain.form.BuscaForm;
import br.com.fadesp.desafio.domain.model.Pagamento;
import br.com.fadesp.desafio.domain.service.PagamentoService;
import br.com.fadesp.desafio.dto.AtualizaPagamentoDTO;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
	
	@Autowired
	private PagamentoService service;
	
	@GetMapping
	public List<Pagamento> listar(BuscaForm form) {
		return service.findAll(form);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pagamento receber(@RequestBody @Valid Pagamento pagamento) {
		return service.salvar(pagamento);
	}
	
	@PutMapping("/{pagamentoId}")
	public ResponseEntity<Pagamento> atualizarStatus(@PathVariable Integer pagamentoId
			, @RequestBody AtualizaPagamentoDTO dto){
				
		if(service.notExistsById(pagamentoId)) {
			return ResponseEntity.notFound().build();
		}
		Pagamento pagamento = service.atualizaPagamento(dto, pagamentoId);
		return ResponseEntity.ok(pagamento);
	}
	
	@DeleteMapping("/{pagamentoId}")
	@ResponseStatus(HttpStatus.OK)
	public void removerPagamento(@PathVariable Integer pagamentoId) {
		service.remover(pagamentoId);
	}
	
}

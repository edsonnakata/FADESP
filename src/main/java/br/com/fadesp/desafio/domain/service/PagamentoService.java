package br.com.fadesp.desafio.domain.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fadesp.desafio.domain.exception.NegocioException;
import br.com.fadesp.desafio.domain.form.BuscaForm;
import br.com.fadesp.desafio.domain.model.Pagamento;
import br.com.fadesp.desafio.domain.model.enums.MetodoPagamento;
import br.com.fadesp.desafio.domain.model.enums.StatusPagamento;
import br.com.fadesp.desafio.domain.repository.PagamentoRepository;
import br.com.fadesp.desafio.dto.AtualizaPagamentoDTO;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository repository;
	
	public List<Pagamento> findAll(BuscaForm form) {
		return repository.findAll(form.toSpec());
	}

	public Pagamento salvar(Pagamento pagamento) {
		if(!isPagamentoCartao(pagamento.getMetodoPagamento())) {
			pagamento.setNumeroCartao(null);
		}else {
			if(pagamento.getNumeroCartao() == null) {
				throw new NegocioException("Pagamento feito por cartão precisa ter o número do cartão associado.");
			}
		}
				
		return repository.save(pagamento);
	}

	public boolean notExistsById(Integer pagamentoId) {
		return !existsById(pagamentoId);
	}

	public Pagamento atualizaPagamento(AtualizaPagamentoDTO dto, Integer pagamentoId) {
		Pagamento pagamento = repository.findById(pagamentoId).get();
		atualizaStatusPagamento(pagamento, dto.getStatusPagamento());
		return repository.save(pagamento);
	}
	

	public void remover(Integer pagamentoId) {
		if(notExistsById(pagamentoId)) {
			throw new NegocioException("Pagamento Inexistente.");
		}
		Pagamento pagamento = repository.findById(pagamentoId).get();
		if(!isPagamentoPendente(pagamento.getStatusPagamento())) {
			throw new NegocioException("Somente pagamentos pendentes podem ser removidos.");
		}
		repository.delete(pagamento);
	}
	
	private boolean existsById(Integer pagamentoId) {
		return repository.existsById(pagamentoId);
	}

	private void atualizaStatusPagamento(Pagamento pagamento, String statusPagamento) {
		String status = pagamento.getStatusPagamento();
		if(StatusPagamento.PROCESSADO_SUCESSO.getDescricao().equals(status)){
			throw new NegocioException("Não é possível atualizar o status de pagamentos " + StatusPagamento.PROCESSADO_SUCESSO.getDescricao() +".");
		}
		
		if(StatusPagamento.PROCESSADO_FALHA.getDescricao().equals(status) && 
				!isPagamentoPendente(statusPagamento)) {
			throw new NegocioException("Pagamentos com status "+ StatusPagamento.PROCESSADO_FALHA.getDescricao() +" só poderão ser atualizadas para "
					+ StatusPagamento.PENDENTE_PROCESSAMENTO.getDescricao() + ".");
		}
		
		pagamento.setStatusPagamento(statusPagamento);
	}

	private boolean isPagamentoPendente(String statusPagamento) {
		return StatusPagamento.PENDENTE_PROCESSAMENTO.getDescricao().equals(statusPagamento);
	}
	
	private boolean isPagamentoCartao(String metodo) {
		return MetodoPagamento.CREDITO.getDescricao().equals(metodo) || MetodoPagamento.DEBITO.getDescricao().equals(metodo);
	}


}

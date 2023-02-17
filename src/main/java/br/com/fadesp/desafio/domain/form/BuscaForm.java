package br.com.fadesp.desafio.domain.form;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import br.com.fadesp.desafio.domain.model.Pagamento;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuscaForm {
	private String documento;
	private Long codigo;
	private String statusPagamento;
	
	public Specification<Pagamento> toSpec() {
		return (root, query, builder) -> {
			List<Predicate> predicados = new ArrayList<>();
			if(StringUtils.hasText(documento)) {
				Path<Object> campoDocumento = root.get("documento");
				Predicate predicadoDocumento = builder.equal(campoDocumento, documento);
				predicados.add(predicadoDocumento);
			}
			if(StringUtils.hasText(statusPagamento)) {
				Path<Object> campoStatus = root.get("statusPagamento");
				Predicate predicadoStatus = builder.equal(campoStatus, statusPagamento);
				predicados.add(predicadoStatus);
			}
			if(codigo != null) {
				Path<Object> campoCodigo = root.get("pagamentoId");
				Predicate predicadoCodigo = builder.equal(campoCodigo, codigo);
				predicados.add(predicadoCodigo);
			}
			return builder.and(predicados.toArray(new Predicate[0]));
		};
	}
}

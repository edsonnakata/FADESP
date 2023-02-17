package br.com.fadesp.desafio.domain.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.fadesp.desafio.domain.model.Pagamento;

@Repository
public interface PagamentoRepository extends PagingAndSortingRepository<Pagamento, Integer>, JpaSpecificationExecutor<Pagamento> {
	
	Collection<Pagamento> findAll();
}

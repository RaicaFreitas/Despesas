package despesas_1v.demo.repository;

import despesas_1v.demo.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Integer> {
    List<FormaPagamento> findByAtivoTrue();
    boolean existsByNome(String nome);
}
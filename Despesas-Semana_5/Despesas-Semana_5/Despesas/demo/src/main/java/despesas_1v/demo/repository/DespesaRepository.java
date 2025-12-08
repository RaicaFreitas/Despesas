package despesas_1v.demo.repository;

import despesas_1v.demo.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Integer> {

    List<Despesa> findByDataDespesaBetween(LocalDate dataInicio, LocalDate dataFim);

    List<Despesa> findByCategoriaIdCategoria(Integer idCategoria);

    @Query("SELECT SUM(d.valor) FROM Despesa d WHERE d.dataDespesa BETWEEN :dataInicio AND :dataFim")
    BigDecimal calcularTotalPorPeriodo(@Param("dataInicio") LocalDate dataInicio,
                                       @Param("dataFim") LocalDate dataFim);
}
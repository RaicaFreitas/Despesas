package com.financeiro.expense.repository;

import com.financeiro.expense.model.Despesa;
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

    @Query("SELECT d FROM Despesa d WHERE YEAR(d.dataDespesa) = :ano AND MONTH(d.dataDespesa) = :mes")
    List<Despesa> findByAnoEMes(@Param("ano") int ano, @Param("mes") int mes);
}
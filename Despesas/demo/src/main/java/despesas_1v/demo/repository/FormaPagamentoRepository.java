package com.financeiro.expense.repository;

import com.financeiro.expense.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Integer> {
    List<FormaPagamento> findByAtivoTrue();
    boolean existsByNome(String nome);
}
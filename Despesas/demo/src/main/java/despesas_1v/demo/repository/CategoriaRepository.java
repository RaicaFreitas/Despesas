package com.financeiro.expense.repository;

import com.financeiro.expense.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    List<Categoria> findByAtivoTrue();
    boolean existsByNome(String nome);
}
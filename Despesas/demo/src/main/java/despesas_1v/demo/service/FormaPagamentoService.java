package com.financeiro.expense.service;

import com.financeiro.expense.exception.ResourceNotFoundException;
import com.financeiro.expense.model.FormaPagamento;
import com.financeiro.expense.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public List<FormaPagamento> listarTodas() {
        return formaPagamentoRepository.findAll();
    }

    public List<FormaPagamento> listarAtivas() {
        return formaPagamentoRepository.findByAtivoTrue();
    }

    public FormaPagamento buscarPorId(Integer id) {
        return formaPagamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Forma de pagamento não encontrada com o ID: " + id));
    }

    public FormaPagamento criar(FormaPagamento formaPagamento) {
        if (formaPagamentoRepository.existsByNome(formaPagamento.getNome())) {
            throw new IllegalArgumentException("Já existe uma forma de pagamento com este nome");
        }
        return formaPagamentoRepository.save(formaPagamento);
    }

    public FormaPagamento atualizar(Integer id, FormaPagamento formaPagamentoAtualizada) {
        FormaPagamento formaPagamento = buscarPorId(id);

        if (!formaPagamento.getNome().equals(formaPagamentoAtualizada.getNome()) &&
                formaPagamentoRepository.existsByNome(formaPagamentoAtualizada.getNome())) {
            throw new IllegalArgumentException("Já existe uma forma de pagamento com este nome");
        }

        formaPagamento.setNome(formaPagamentoAtualizada.getNome());
        formaPagamento.setDescricao(formaPagamentoAtualizada.getDescricao());
        formaPagamento.setAtivo(formaPagamentoAtualizada.getAtivo());

        return formaPagamentoRepository.save(formaPagamento);
    }

    public void deletar(Integer id) {
        FormaPagamento formaPagamento = buscarPorId(id);
        formaPagamentoRepository.delete(formaPagamento);
    }
}
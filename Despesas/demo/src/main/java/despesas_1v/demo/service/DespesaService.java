package com.financeiro.expense.service;

import com.financeiro.expense.exception.ResourceNotFoundException;
import com.financeiro.expense.model.Despesa;
import com.financeiro.expense.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    public List<Despesa> listarTodas() {
        return despesaRepository.findAll();
    }

    public Despesa buscarPorId(Integer id) {
        return despesaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Despesa não encontrada com o ID: " + id));
    }

    public List<Despesa> listarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return despesaRepository.findByDataDespesaBetween(dataInicio, dataFim);
    }

    public List<Despesa> listarPorMesEAno(int mes, int ano) {
        return despesaRepository.findByAnoEMes(ano, mes);
    }

    public List<Despesa> listarPorCategoria(Integer idCategoria) {
        return despesaRepository.findByCategoriaIdCategoria(idCategoria);
    }

    public BigDecimal calcularTotalPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        BigDecimal total = despesaRepository.calcularTotalPorPeriodo(dataInicio, dataFim);
        return total != null ? total : BigDecimal.ZERO;
    }

    public Despesa criar(Despesa despesa) {
        // Valida se categoria existe
        categoriaService.buscarPorId(despesa.getCategoria().getIdCategoria());

        // Valida se forma de pagamento existe
        formaPagamentoService.buscarPorId(despesa.getFormaPagamento().getIdFormaPagamento());

        return despesaRepository.save(despesa);
    }

    public Despesa atualizar(Integer id, Despesa despesaAtualizada) {
        Despesa despesa = buscarPorId(id);

        // Valida se categoria existe
        categoriaService.buscarPorId(despesaAtualizada.getCategoria().getIdCategoria());

        // Valida se forma de pagamento existe
        formaPagamentoService.buscarPorId(despesaAtualizada.getFormaPagamento().getIdFormaPagamento());

        despesa.setDescricao(despesaAtualizada.getDescricao());
        despesa.setValor(despesaAtualizada.getValor());
        despesa.setDataDespesa(despesaAtualizada.getDataDespesa());
        despesa.setObservacao(despesaAtualizada.getObservacao());
        despesa.setCategoria(despesaAtualizada.getCategoria());
        despesa.setFormaPagamento(despesaAtualizada.getFormaPagamento());

        return despesaRepository.save(despesa);
    }

    public void deletar(Integer id) {
        Despesa despesa = buscarPorId(id);
        despesaRepository.delete(despesa);
    }
}
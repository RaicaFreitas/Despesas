package despesas_1v.demo.controller;

import despesas_1v.demo.model.Despesa;
import despesas_1v.demo.service.DespesaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/despesas")
@Tag(name = "Despesas", description = "Endpoints para gerenciar despesas")
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    @GetMapping
    @Operation(summary = "Listar todas as despesas")
    public ResponseEntity<List<Despesa>> listarTodas() {
        return ResponseEntity.ok(despesaService.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar despesa por ID")
    public ResponseEntity<Despesa> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(despesaService.buscarPorId(id));
    }

    @GetMapping("/periodo")
    @Operation(summary = "Listar despesas por período")
    public ResponseEntity<List<Despesa>> listarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        return ResponseEntity.ok(despesaService.listarPorPeriodo(dataInicio, dataFim));
    }

    @GetMapping("/mes-ano")
    @Operation(summary = "Listar despesas por mês e ano")
    public ResponseEntity<List<Despesa>> listarPorMesEAno(
            @RequestParam int mes,
            @RequestParam int ano) {
        return ResponseEntity.ok(despesaService.listarPorMesEAno(mes, ano));
    }

    @GetMapping("/categoria/{idCategoria}")
    @Operation(summary = "Listar despesas por categoria")
    public ResponseEntity<List<Despesa>> listarPorCategoria(@PathVariable Integer idCategoria) {
        return ResponseEntity.ok(despesaService.listarPorCategoria(idCategoria));
    }

    @GetMapping("/total-periodo")
    @Operation(summary = "Calcular total de despesas por período")
    public ResponseEntity<Map<String, Object>> calcularTotalPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {

        BigDecimal total = despesaService.calcularTotalPorPeriodo(dataInicio, dataFim);

        Map<String, Object> response = new HashMap<>();
        response.put("dataInicio", dataInicio);
        response.put("dataFim", dataFim);
        response.put("total", total);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Criar nova despesa")
    public ResponseEntity<Despesa> criar(@Valid @RequestBody Despesa despesa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(despesaService.criar(despesa));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar despesa existente")
    public ResponseEntity<Despesa> atualizar(@PathVariable Integer id,
                                             @Valid @RequestBody Despesa despesa) {
        return ResponseEntity.ok(despesaService.atualizar(id, despesa));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar despesa")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        despesaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
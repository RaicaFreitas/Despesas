package despesas_1v.demo.controller;

import despesas_1v.demo.model.Despesa;
import despesas_1v.demo.service.DespesaService;
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
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    @GetMapping
    public ResponseEntity<List<Despesa>> listarTodas() {
        return ResponseEntity.ok(despesaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Despesa> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(despesaService.buscarPorId(id));
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<Despesa>> listarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        return ResponseEntity.ok(despesaService.listarPorPeriodo(dataInicio, dataFim));
    }

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<Despesa>> listarPorCategoria(@PathVariable Integer idCategoria) {
        return ResponseEntity.ok(despesaService.listarPorCategoria(idCategoria));
    }

    @GetMapping("/total-periodo")
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
    public ResponseEntity<Despesa> criar(@Valid @RequestBody Despesa despesa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(despesaService.criar(despesa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Despesa> atualizar(@PathVariable Integer id,
                                             @Valid @RequestBody Despesa despesa) {
        return ResponseEntity.ok(despesaService.atualizar(id, despesa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        despesaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
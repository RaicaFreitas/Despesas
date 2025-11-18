package despesas_1v.demo.controller;

import despesas_1v.demo.model.FormaPagamento;
import despesas_1v.demo.service.FormaPagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/formas-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @GetMapping
    public ResponseEntity<List<FormaPagamento>> listarTodas() {
        return ResponseEntity.ok(formaPagamentoService.listarTodas());
    }

    @GetMapping("/ativas")
    public ResponseEntity<List<FormaPagamento>> listarAtivas() {
        return ResponseEntity.ok(formaPagamentoService.listarAtivas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamento> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(formaPagamentoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<FormaPagamento> criar(@Valid @RequestBody FormaPagamento formaPagamento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(formaPagamentoService.criar(formaPagamento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormaPagamento> atualizar(@PathVariable Integer id,
                                                    @Valid @RequestBody FormaPagamento formaPagamento) {
        return ResponseEntity.ok(formaPagamentoService.atualizar(id, formaPagamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        formaPagamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
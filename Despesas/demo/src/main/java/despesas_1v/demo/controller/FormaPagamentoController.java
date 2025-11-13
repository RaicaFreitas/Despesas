package despesas_1v.demo.controller;

import despesas_1v.demo.model.FormaPagamento;
import despesas_1v.demo.service.FormaPagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/formas-pagamento")
@Tag(name = "Formas de Pagamento", description = "Endpoints para gerenciar formas de pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @GetMapping
    @Operation(summary = "Listar todas as formas de pagamento")
    public ResponseEntity<List<FormaPagamento>> listarTodas() {
        return ResponseEntity.ok(formaPagamentoService.listarTodas());
    }

    @GetMapping("/ativas")
    @Operation(summary = "Listar apenas formas de pagamento ativas")
    public ResponseEntity<List<FormaPagamento>> listarAtivas() {
        return ResponseEntity.ok(formaPagamentoService.listarAtivas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar forma de pagamento por ID")
    public ResponseEntity<FormaPagamento> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(formaPagamentoService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Criar nova forma de pagamento")
    public ResponseEntity<FormaPagamento> criar(@Valid @RequestBody FormaPagamento formaPagamento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(formaPagamentoService.criar(formaPagamento));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar forma de pagamento existente")
    public ResponseEntity<FormaPagamento> atualizar(@PathVariable Integer id,
                                                    @Valid @RequestBody FormaPagamento formaPagamento) {
        return ResponseEntity.ok(formaPagamentoService.atualizar(id, formaPagamento));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar forma de pagamento")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        formaPagamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
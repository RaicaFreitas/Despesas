package despesas_1v.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "despesa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_despesa")
    private Integer idDespesa;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 200, message = "Descrição deve ter no máximo 200 caracteres")
    @Column(nullable = false, length = 200)
    private String descricao;

    @NotNull(message = "Valor é obrigatório")
    @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @NotNull(message = "Data da despesa é obrigatória")
    @Column(name = "data_despesa", nullable = false)
    private LocalDate dataDespesa;

    @Column(columnDefinition = "TEXT")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    @NotNull(message = "Categoria é obrigatória")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_forma_pagamento", nullable = false)
    @NotNull(message = "Forma de pagamento é obrigatória")
    private FormaPagamento formaPagamento;

    @CreationTimestamp
    @Column(name = "data_cadastro", updatable = false)
    private LocalDateTime dataCadastro;

    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
}
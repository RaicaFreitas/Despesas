package despesas_1v.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 50, message = "Nome deve ter no máximo 50 caracteres")
    @Column(nullable = false, unique = true, length = 50)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Size(max = 50)
    @Column(length = 50)
    private String icone;

    @Size(max = 7)
    @Column(length = 7)
    private String cor;

    @Column(nullable = false)
    private Boolean ativo = true;

    // ========== CONSTRUTORES ==========

    public Categoria() {
    }

    public Categoria(Integer idCategoria, String nome, String descricao, String icone, String cor, Boolean ativo) {
        this.idCategoria = idCategoria;
        this.nome = nome;
        this.descricao = descricao;
        this.icone = icone;
        this.cor = cor;
        this.ativo = ativo;
    }

    // ========== GETTERS E SETTERS ==========

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
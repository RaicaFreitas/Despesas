package despesas_1v.demo.service;

import despesas_1v.demo.exception.ResourceNotFoundException;
import despesas_1v.demo.model.Categoria;
import despesas_1v.demo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public List<Categoria> listarAtivas() {
        return categoriaRepository.findByAtivoTrue();
    }

    public Categoria buscarPorId(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com ID: " + id));
    }

    public Categoria criar(Categoria categoria) {
        if (categoriaRepository.existsByNome(categoria.getNome())) {
            throw new IllegalArgumentException("Já existe uma categoria com este nome");
        }
        return categoriaRepository.save(categoria);
    }

    public Categoria atualizar(Integer id, Categoria categoriaAtualizada) {
        Categoria categoria = buscarPorId(id);

        if (!categoria.getNome().equals(categoriaAtualizada.getNome()) &&
                categoriaRepository.existsByNome(categoriaAtualizada.getNome())) {
            throw new IllegalArgumentException("Já existe uma categoria com este nome");
        }

        categoria.setNome(categoriaAtualizada.getNome());
        categoria.setDescricao(categoriaAtualizada.getDescricao());
        categoria.setAtivo(categoriaAtualizada.getAtivo());

        return categoriaRepository.save(categoria);
    }

    public void deletar(Integer id) {
        Categoria categoria = buscarPorId(id);
        categoriaRepository.delete(categoria);
    }
}
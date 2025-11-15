package despesas_1v.demo.repository;

import despesas_1v.demo.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    List<Categoria> findByAtivoTrue();
    boolean existsByNome(String nome);
}
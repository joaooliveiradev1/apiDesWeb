package com.example.demo.Repository;

import com.example.demo.Model.Categoria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaRepository {
    private final List<Categoria> categorias = new ArrayList<>();
    private Long nextId = 1L;

    public CategoriaRepository() {}

    public List<Categoria> findaAll(){
        return categorias;
    }

    public Optional<Categoria> findById(Long id){
        return categorias.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public Categoria save(Categoria categoria){
        categoria.setId(nextId++);
        categorias.add(categoria);
        return categoria;
    }

    public void delete(Long id){
        categorias.removeIf(c -> c.getId().equals(id));
    }

    public Categoria update(Categoria categoria){
        return categorias.stream()
                .filter(c -> c.getId().equals(categoria.getId()))
                .findFirst()
                .map(c -> {
                    c.setNome(categoria.getNome());
                    c.setDescricao(categoria.getDescricao());
                    return c;
                })
                .orElseThrow(() -> new RuntimeException("Categoria nao encontrada"));
    }

}

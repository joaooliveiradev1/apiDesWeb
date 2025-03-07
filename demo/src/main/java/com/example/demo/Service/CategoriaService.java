package com.example.demo.Service;

import com.example.demo.DTO.request.RequestDTO;
import com.example.demo.DTO.response.ResponseDTO;
import com.example.demo.Model.Categoria;
import com.example.demo.Repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<ResponseDTO> listarCategorias(){

        List<Categoria> lista = categoriaRepository.findaAll();

        return lista.stream()
                .map(c -> new ResponseDTO(c.getId(), c.getNome(), c.getDescricao()))
                .collect(Collectors.toList());
    }
    public ResponseDTO buscarPorId(Long id){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Categoria não encontrado"));
        return new ResponseDTO(categoria.getId(), categoria.getNome(), categoria.getDescricao());
    }

    public  ResponseDTO criarCategoria(RequestDTO categoriaRequestDTO){
        Categoria novaCategoria = new Categoria(null,
                categoriaRequestDTO.getNome(),
                categoriaRequestDTO.getDescricao());
        Categoria categoriaSalvo = categoriaRepository.save(novaCategoria);
        return new ResponseDTO(categoriaSalvo.getId(),categoriaRequestDTO.getNome(),categoriaRequestDTO.getDescricao());
    }

    public ResponseDTO atualizarCategoria(Long id, ResponseDTO categoriaResponseDTO){
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrado"));

        categoriaExistente.setNome(categoriaResponseDTO.getNome());
        categoriaExistente.setDescricao(categoriaResponseDTO.getDescricao());

        Categoria categoriaAtualizada = categoriaRepository.update(categoriaExistente);

        return new ResponseDTO(categoriaAtualizada
                .getId(),categoriaAtualizada.getNome(),
                categoriaAtualizada.getDescricao());
    }

    public void excluirCategoria(Long id){
        categoriaRepository.delete(id);
    }

}
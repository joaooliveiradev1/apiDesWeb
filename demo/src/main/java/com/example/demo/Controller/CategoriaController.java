package com.example.demo.Controller;

import com.example.demo.DTO.request.RequestDTO;
import com.example.demo.DTO.response.ResponseDTO;
import com.example.demo.Service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseDTO>> listarProdutos(){

        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> criarProduto(@RequestBody RequestDTO categoriaRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.criarCategoria(categoriaRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> atualizarProduto(@PathVariable Long id, @RequestBody ResponseDTO categoriaResponseDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.atualizarCategoria(id, categoriaResponseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> excluirProduto(@PathVariable Long id){
        categoriaService.excluirCategoria(id);
        return ResponseEntity.noContent().build();
    }
}


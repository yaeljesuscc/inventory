package com.api.inventory.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.inventory.models.dto.CategoriaDTO;
import com.api.inventory.models.entities.Categoria;
import com.api.inventory.services.CategoriaService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/categoria")
    public ResponseEntity<?> getCategorias() {
        List<Categoria> categorias = categoriaService.getCategorias();

        if (categorias.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<CategoriaDTO> categoriaDTO = categorias.stream()
            .map(categoria -> CategoriaDTO.builder()
                    .idCategoria(categoria.getIdCategoria())
                    .nombreCategoria(categoria.getNombreCategoria())
                    .descripcionCategoria(categoria.getDescripcionCategoria())
                    .build())
            .collect(Collectors.toList());

        return new ResponseEntity<>(categoriaDTO, HttpStatus.OK);
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<?> getCategoria(@PathVariable int id) {
        Categoria categoria = categoriaService.getByIdCategoria(id);

        if (categoria == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CategoriaDTO categoriaDTO = CategoriaDTO.builder()
            .idCategoria(categoria.getIdCategoria())
            .nombreCategoria(categoria.getNombreCategoria())
            .descripcionCategoria(categoria.getDescripcionCategoria())
            .build();

        return new ResponseEntity<>(categoriaDTO, HttpStatus.OK);
    }

    @PostMapping("/categoria")
    public ResponseEntity<?> saveCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        try {
            Categoria categoriaSave = categoriaService.saveCategoria(categoriaDTO);
            CategoriaDTO savedCategoriaDTO = CategoriaDTO.builder()
                .idCategoria(categoriaSave.getIdCategoria())
                .nombreCategoria(categoriaSave.getNombreCategoria())
                .descripcionCategoria(categoriaSave.getDescripcionCategoria())
                .build();

            return new ResponseEntity<>(savedCategoriaDTO, HttpStatus.CREATED); 
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("/categoria/{id}")
    public ResponseEntity<?> updateCategoria(@RequestBody CategoriaDTO categoriaDTO, @PathVariable int id) {
        try {
            if (categoriaService.existByIdC(id)) {
                categoriaDTO.setIdCategoria(id);
                Categoria categoriaUpdate = categoriaService.saveCategoria(categoriaDTO);
                CategoriaDTO updatedCategoriaDTO = CategoriaDTO.builder()
                    .idCategoria(categoriaUpdate.getIdCategoria())
                    .nombreCategoria(categoriaUpdate.getNombreCategoria())
                    .descripcionCategoria(categoriaUpdate.getDescripcionCategoria())
                    .build();

                return new ResponseEntity<>(updatedCategoriaDTO, HttpStatus.CREATED); 
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("/categoria/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable int id) {
        try {
            Categoria categoriaDelete = categoriaService.getByIdCategoria(id);
            if (categoriaDelete == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            categoriaService.deleteCategoria(categoriaDelete);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
}

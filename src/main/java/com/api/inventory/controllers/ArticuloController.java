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

import com.api.inventory.models.dto.ArticuloDTO;
import com.api.inventory.models.dto.CategoriaDTO;
import com.api.inventory.models.entities.Articulo;
import com.api.inventory.services.ArticuloService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    @GetMapping("/articulos")
    public ResponseEntity<?> getArticulos() {
        List<Articulo> articulos = articuloService.getArticulos();

        if (articulos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<ArticuloDTO> articuloDTO = articulos.stream()
            .map(articulo -> ArticuloDTO.builder()
                    .idArticulo(articulo.getIdArticulo())
                    .nombreArticulo(articulo.getNombreArticulo())
                    .descripcionArticulo(articulo.getDescripcionArticulo())
                    .precioArticulo(articulo.getPrecioArticulo())
                    .stockArticulo(articulo.getStockArticulo())
                    .categoriaDTO(CategoriaDTO.builder()
                        .idCategoria(articulo.getCategoria().getIdCategoria())
                        .nombreCategoria(articulo.getCategoria().getNombreCategoria())
                        .descripcionCategoria(articulo.getCategoria().getDescripcionCategoria())
                        .build())
                    .build())
            .collect(Collectors.toList());

        return new ResponseEntity<>(articuloDTO, HttpStatus.OK);
    }

    @GetMapping("/articulo/{id}")
    public ResponseEntity<?> getArticulo(@PathVariable int id) {
        Articulo articulo = articuloService.getByIdArticulo(id);

        if (articulo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ArticuloDTO articuloDTO = ArticuloDTO.builder()
            .idArticulo(articulo.getIdArticulo())
            .nombreArticulo(articulo.getNombreArticulo())
            .descripcionArticulo(articulo.getDescripcionArticulo())
            .precioArticulo(articulo.getPrecioArticulo())
            .stockArticulo(articulo.getStockArticulo())
            .categoriaDTO(CategoriaDTO.builder()
                .idCategoria(articulo.getCategoria().getIdCategoria())
                .nombreCategoria(articulo.getCategoria().getNombreCategoria())
                .descripcionCategoria(articulo.getCategoria().getDescripcionCategoria())
                .build())
            .build();

        return new ResponseEntity<>(articuloDTO, HttpStatus.OK);
    }

    @PostMapping("/articulo")
    public ResponseEntity<?> saveArticulo(@RequestBody ArticuloDTO articuloDTO) {
        try {
            Articulo articuloSave = articuloService.saveArticulo(articuloDTO);
            ArticuloDTO savedArticuloDTO = ArticuloDTO.builder()
                .idArticulo(articuloSave.getIdArticulo())
                .nombreArticulo(articuloSave.getNombreArticulo())
                .descripcionArticulo(articuloSave.getDescripcionArticulo())
                .precioArticulo(articuloSave.getPrecioArticulo())
                .stockArticulo(articuloSave.getStockArticulo())
                .categoriaDTO(CategoriaDTO.builder()
                    .idCategoria(articuloSave.getCategoria().getIdCategoria())
                    .nombreCategoria(articuloSave.getCategoria().getNombreCategoria())
                    .descripcionCategoria(articuloSave.getCategoria().getDescripcionCategoria())
                    .build())
                .build();

            return new ResponseEntity<>(savedArticuloDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/articulo/{id}")
    public ResponseEntity<?> updateArticulo(@PathVariable int id, @RequestBody ArticuloDTO articuloDTO) {
        try {
            if (articuloService.existByIdA(id)) {
                articuloDTO.setIdArticulo(id);
                Articulo articuloUpdate = articuloService.saveArticulo(articuloDTO);

                ArticuloDTO updatedArticuloDTO = ArticuloDTO.builder()
                    .idArticulo(articuloUpdate.getIdArticulo())
                    .nombreArticulo(articuloUpdate.getNombreArticulo())
                    .descripcionArticulo(articuloUpdate.getDescripcionArticulo())
                    .precioArticulo(articuloUpdate.getPrecioArticulo())
                    .stockArticulo(articuloUpdate.getStockArticulo())
                    .categoriaDTO(CategoriaDTO.builder()
                        .idCategoria(articuloUpdate.getCategoria().getIdCategoria())
                        .nombreCategoria(articuloUpdate.getCategoria().getNombreCategoria())
                        .descripcionCategoria(articuloUpdate.getCategoria().getDescripcionCategoria())
                        .build())
                    .build();

                return new ResponseEntity<>(updatedArticuloDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("/articulo/{id}")
    public ResponseEntity<?> deleteArticulo(@PathVariable int id) {
        try {
            Articulo articulo = articuloService.getByIdArticulo(id);
            if (articulo == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            articuloService.deleteArticulo(articulo);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
}

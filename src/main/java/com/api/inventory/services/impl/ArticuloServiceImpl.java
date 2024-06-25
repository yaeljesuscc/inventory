package com.api.inventory.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.inventory.models.dto.ArticuloDTO;
import com.api.inventory.models.entities.Articulo;
import com.api.inventory.models.entities.Categoria;
import com.api.inventory.models.repositories.IArticuloRepository;
import com.api.inventory.services.ArticuloService;


@Service
public class ArticuloServiceImpl implements ArticuloService{

    @Autowired
    private IArticuloRepository articuloRepository;

    @Transactional
    @Override
    public Articulo saveArticulo(ArticuloDTO articuloDTO) {
        Articulo articulo = Articulo.builder()
            .idArticulo(articuloDTO.getIdArticulo())
            .nombreArticulo(articuloDTO.getNombreArticulo())
            .descripcionArticulo(articuloDTO.getDescripcionArticulo())
            .precioArticulo(articuloDTO.getPrecioArticulo())
            .stockArticulo(articuloDTO.getStockArticulo())
            .categoria(Categoria.builder()
                .idCategoria(articuloDTO.getCategoriaDTO().getIdCategoria())
                .nombreCategoria(articuloDTO.getCategoriaDTO().getNombreCategoria())
                .descripcionCategoria(articuloDTO.getCategoriaDTO().getDescripcionCategoria())
                .build())
            .build();
        return articuloRepository.save(articulo);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Articulo> getArticulos() {
        return (List<Articulo>) articuloRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Articulo getByIdArticulo(int id) {
        return articuloRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void deleteArticulo(Articulo articulo) {
        articuloRepository.delete(articulo);
    }
    
    @Override public boolean existByIdA(Integer id) {
        return articuloRepository.existsById(id);
    }
}

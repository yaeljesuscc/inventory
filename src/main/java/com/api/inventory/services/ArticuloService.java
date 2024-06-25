package com.api.inventory.services;

import java.util.List;

import com.api.inventory.models.dto.ArticuloDTO;
import com.api.inventory.models.entities.Articulo;

public interface ArticuloService {
    
    Articulo saveArticulo(ArticuloDTO categoriaDTO);

    List<Articulo> getArticulos();

    Articulo getByIdArticulo(int id);

    void deleteArticulo(Articulo articulo);

    boolean existByIdA(Integer id);
}


package com.api.inventory.services;

import java.util.List;

import com.api.inventory.models.dto.CategoriaDTO;
import com.api.inventory.models.entities.Categoria;

public interface CategoriaService {

    Categoria saveCategoria(CategoriaDTO categoriaDTO);

    List<Categoria> getCategorias();

    Categoria getByIdCategoria(int id);

    void deleteCategoria(Categoria categoria);

    boolean existByIdC(Integer id);
}

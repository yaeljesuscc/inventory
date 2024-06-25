package com.api.inventory.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.inventory.models.dto.CategoriaDTO;
import com.api.inventory.models.entities.Categoria;
import com.api.inventory.models.repositories.ICategoriaRepository;
import com.api.inventory.services.CategoriaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private ICategoriaRepository categoriaRepository;

    @Transactional
    @Override
    public Categoria saveCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = Categoria.builder()
            .idCategoria(categoriaDTO.getIdCategoria())
            .nombreCategoria(categoriaDTO.getNombreCategoria())
            .descripcionCategoria(categoriaDTO.getDescripcionCategoria())
            .build();
        return categoriaRepository.save(categoria);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Categoria> getCategorias() {
        return (List<Categoria>) categoriaRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Categoria getByIdCategoria(int id) {
        return categoriaRepository.findById(id).orElse(null);
    }
    
    @Transactional
    @Override
    public void deleteCategoria(Categoria categoria) {
        categoriaRepository.delete(categoria);
    }

    @Override
    public boolean existByIdC(Integer id) {
        return categoriaRepository.existsById(id);
    }
}

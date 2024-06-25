package com.api.inventory.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.inventory.models.entities.Categoria;

@Repository
public interface ICategoriaRepository extends CrudRepository<Categoria, Integer>{
    
}

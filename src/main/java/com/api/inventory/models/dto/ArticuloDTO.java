package com.api.inventory.models.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class ArticuloDTO implements Serializable{
    
    private int idArticulo;

    private String nombreArticulo;

    private String descripcionArticulo;
    
    private int stockArticulo;

    private double precioArticulo;

    private CategoriaDTO categoriaDTO;
}

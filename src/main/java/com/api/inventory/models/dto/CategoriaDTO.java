package com.api.inventory.models.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder

public class CategoriaDTO implements Serializable{
    private int idCategoria;

    private String nombreCategoria;

    private String descripcionCategoria;
}

package com.api.inventory.models.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable{

    @Id
    @Column(name = "idCategoria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategoria;

    @NotBlank(message = "El nombre de la categoria es requerido")
    @Size(max = 50, message = "El nombre de la categoria no puede tener mas de 50 caracteres")
    @Column(name = "nombreCategoria", length = 50)
    private String nombreCategoria;

    @NotBlank(message = "La descripcion de la categoria es requerida")
    @Size(max = 255, message = "La descripcion de la categoria no puede tener mas de 255 caracteres")
    @Column(name = "descripcionCategoria", length = 255)
    private String descripcionCategoria;

}

package com.api.inventory.models.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "articulo")
public class Articulo implements Serializable{

    @Id
    @Column(name = "idArticulo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idArticulo;

    @NotBlank(message = "El nombre del articulo es requerido")
    @Size(max = 50, message = "El nombre del articulo no puede tener mas de 50 caracteres")
    @Column(name = "nombreArticulo", length = 50, nullable = false)
    private String nombreArticulo;

    @NotBlank(message = "La descripcion del articulo es requerida")
    @Size(max = 255, message = "La descripcion del articulo no puede tener mas de 255 caracteres")
    @Column(name = "descripcionArticulo", length = 250, nullable = false) 
    private String descripcionArticulo;

    @Column(name = "stockArticulo", nullable = false)
    private int stockArticulo;

    @Column(name = "precioArticulo", nullable = false)
    private double precioArticulo;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;
}

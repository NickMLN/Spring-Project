package br.senac.sp.guiarestaurante.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;

@Data
@Entity
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double nota;
    private String comnetario;
    private Calendar dataVisita;
    @ManyToOne
    private Restaurante restaurante;
    @ManyToOne
    private Usuario usuario;
}

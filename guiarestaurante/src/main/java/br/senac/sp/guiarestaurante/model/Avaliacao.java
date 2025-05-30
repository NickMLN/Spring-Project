package br.senac.sp.guiarestaurante.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Calendar dataVisita;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Restaurante restaurante;
    @ManyToOne
    private Usuario usuario;
}

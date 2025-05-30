package br.senac.sp.guiarestaurante.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private  String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
}

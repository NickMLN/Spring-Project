package br.senac.sp.guiarestaurante.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cep;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String atracoes;
    private String formasPagamento;
    private String horario;
    private String site;
    private String telefone;
    private boolean delivery;
    private boolean acessibilidade;
    private boolean driveThru;
    private boolean estacionamento;
    private boolean espacoPet;
    private boolean espacoKids;
    @ManyToOne
    private TipoRestaurante tipo;
    @OneToMany(mappedBy = "restaurante")
    private List<Avaliacao> avaliacoes;
}

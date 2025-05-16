package br.senac.sp.guiarestaurante.controller;

import br.senac.sp.guiarestaurante.model.TipoRestaurante;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TipoRestauranteController {
    @GetMapping("/teste")
    public TipoRestaurante getRestaurante() {
        TipoRestaurante tipo = new TipoRestaurante();
        tipo.setId(1L);
        tipo.setNome("Fast Food");
        tipo.setDescricacao("Restaurante de Comida Rápida");
        return tipo;
    }
}

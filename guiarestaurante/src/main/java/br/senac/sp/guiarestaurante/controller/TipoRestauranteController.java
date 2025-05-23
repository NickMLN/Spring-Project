package br.senac.sp.guiarestaurante.controller;

import br.senac.sp.guiarestaurante.model.TipoRestaurante;
import br.senac.sp.guiarestaurante.repository.TipoRestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/tiporestaurante")
public class TipoRestauranteController {
    @Autowired
    private TipoRestauranteRepository repository;

    @PostMapping
    public ResponseEntity<Object> criarTipo(@RequestBody TipoRestaurante tipo) {
        try {
            //Salvar no Banco
            repository.save(tipo);
            //Retornar a Resposta
            return ResponseEntity.created(URI.create("/tiporestaurante/" + tipo.getId())).body(tipo);
        } catch (Exception e) {
            e.printStackTrace();
            String erro = e.getMessage();
            return new ResponseEntity<Object>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public TipoRestaurante getRestaurante() {
        TipoRestaurante tipo = new TipoRestaurante();
        tipo.setId(1L);
        tipo.setNome("Fast Food");
        tipo.setDescricao("Restaurante de Comida Rápida");
        return tipo;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoRestaurante> buscarPorId(@PathVariable Long id) {
        Optional<TipoRestaurante> tipo = repository.findById(id);
        if (tipo.isPresent()) {
            return ResponseEntity.ok(tipo.get());
        } else  {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> alterarTipo(@RequestBody TipoRestaurante tipo, @PathVariable("id") Long id) {
        try {
            //Salvar no Banco
            repository.save(tipo);
            //Retornar a Resposta
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            String erro = e.getMessage();
            return new ResponseEntity<Object>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Iterable<TipoRestaurante>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }
}

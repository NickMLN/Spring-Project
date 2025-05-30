package br.senac.sp.guiarestaurante.controller;

import br.senac.sp.guiarestaurante.model.Restaurante;
import br.senac.sp.guiarestaurante.model.TipoRestaurante;
import br.senac.sp.guiarestaurante.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {
    @Autowired
    private RestauranteRepository repository;

    @PostMapping
    public ResponseEntity<Object> criarRestaurante(@RequestBody Restaurante rest) {
        try {
            repository.save(rest);
            return ResponseEntity.created(URI.create("/restaurante/" + rest.getId())).body(rest);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscarPorId(@PathVariable Long id) {
        Optional<Restaurante> rest = repository.findById(id);
        if (rest.isPresent()) {
            return ResponseEntity.ok(rest.get());
        } else  {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> alterarRestaurante(@RequestBody Restaurante rest, @PathVariable("id") Long id) {
        try {
            //Salvar no Banco
            repository.save(rest);
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
    public ResponseEntity<Iterable<Restaurante>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }
}

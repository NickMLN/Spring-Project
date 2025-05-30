package br.senac.sp.guiarestaurante.controller;

import br.senac.sp.guiarestaurante.model.Avaliacao;
import br.senac.sp.guiarestaurante.model.Restaurante;
import br.senac.sp.guiarestaurante.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoRepository repository;

    @PostMapping
    public ResponseEntity<Object> criarRestaurante(@RequestBody Avaliacao ava) {
        try {
            repository.save(ava);
            return ResponseEntity.created(URI.create("/restaurante/" + ava.getId())).body(ava);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscarPorId(@PathVariable Long id) {
        Optional<Avaliacao> ava = repository.findById(id);
        if (ava.isPresent()) {
            return ResponseEntity.ok(ava.get());
        } else  {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> alterarAvaliacao(@RequestBody Avaliacao ava, @PathVariable("id") Long id) {
        try {
            //Salvar no Banco
            repository.save(ava);
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
    public ResponseEntity<Iterable<Avaliacao>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }
}

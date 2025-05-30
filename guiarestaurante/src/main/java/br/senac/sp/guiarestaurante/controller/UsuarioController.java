package br.senac.sp.guiarestaurante.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senac.sp.guiarestaurante.model.TipoRestaurante;
import br.senac.sp.guiarestaurante.model.Usuario;
import br.senac.sp.guiarestaurante.repository.TipoRestauranteRepository;
import br.senac.sp.guiarestaurante.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioRepository repository;

	@PostMapping
	public ResponseEntity<Object> criarUsuario(@RequestBody Usuario usuario) {
		try {
			// salvar no banco
			repository.save(usuario);
			// retorna a resposta
			return ResponseEntity.created(URI.create("/usuario/" + usuario.getId())).body(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			String erro = e.getMessage();
			return new ResponseEntity<Object>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> alterarUsuario(@RequestBody Usuario usuario, @PathVariable("id") Long id) {
		try {
			// salvar no banco
			repository.save(usuario);
			// retorna a resposta
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			String erro = e.getMessage();
			return new ResponseEntity<Object>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<Iterable<Usuario>> listarTodos() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable("id") Long id) {
		Optional<Usuario> tipo = repository.findById(id);
		if (tipo.isPresent()) {
			return ResponseEntity.ok(tipo.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}

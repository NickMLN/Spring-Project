package br.senac.sp.guiarestaurante.repository;

import org.springframework.data.repository.CrudRepository;

import br.senac.sp.guiarestaurante.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	public Usuario findByEmailAndSenha(String email, String senha);
}